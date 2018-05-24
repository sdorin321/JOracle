package ro.iss.JOracle;

import java.math.BigDecimal;
import java.util.Arrays;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import rx.Observable;
import rx.Subscription;

public class JOracle
{

	public static void main(String[] args) throws Exception {

		// json file for address src/build/contracts/RONtoUSDOracle.json contractAddress

		System.out.println("contract address::" + args[0]);
		System.out.println("private key::" + args[1]);

		// define RPC connection
		Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
		System.out.println("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());

		// load main account based on private key
		// test net default
		// ea8e2b8383d8373ad2a4508446248c8963b0b22dcbb602309cddf567878264c4 - acc[0]
		Credentials credentials = Credentials.create(args[1]);
		System.out.println("[ETH-INFO] Credentials: " + credentials.getAddress());

		// load the contract using the credentials
		RONtoUSDOracle contract = RONtoUSDOracle.load(args[0], web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
		System.out.println("[ETH-INFO] Loading contract: " + contract.getContractAddress());

		// instantiate the monitored event
		Event CALLBACKGETRONTOUSD_EVENT = new Event("CallbackGetRONtoUSD", Arrays.<TypeReference<?>>asList(), Arrays.<TypeReference<?>>asList());
		;
		// instantiate an EthFilter on the contract and add the event signature
		EthFilter ethFilter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, contract.getContractAddress().substring(2));
		// filter the contract log for the REQUESTIDEVENT_EVENT signature
		ethFilter.addSingleTopic(EventEncoder.encode(CALLBACKGETRONTOUSD_EVENT));

		// set an observable for with the filter
		Observable<RONtoUSDOracle.CallbackGetRONtoUSDEventResponse> callbackEvent = contract.callbackGetRONtoUSDEventObservable(ethFilter);
		// start monitoring
		Subscription subscription = callbackEvent.subscribe(log -> {
			String httpResponse = "";

			try {
				// get the currency rate
				httpResponse = HttpHelper.get("http://data.fixer.io/api/latest?access_key=e4f3910a7cd92c94fbea2c69400d0a20&symbols=RON");
				System.out.println("[GET-Request-INFO] Request response: " + httpResponse);

				// process the response
				JsonElement jelement = new JsonParser().parse(httpResponse);
				JsonObject jobject = jelement.getAsJsonObject();
				jobject = jobject.getAsJsonObject("rates");
				String result = String.valueOf(jobject.get("RON").getAsDouble());

				System.out.println("[GET-Request-INFO] Rate: " + result);

				// set values rate and requestId to contract ApiBase method
				org.web3j.protocol.core.RemoteCall<TransactionReceipt> txReceipt = contract.setRONToUSDRate(BigDecimal.valueOf(Double.parseDouble(result) * 10000).toBigInteger());
				TransactionReceipt tx = null;
				try {
					tx = txReceipt.send();
				}
				catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("[ETH-INFO] Executed __internal_hook " + tx.getTransactionHash());
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}, Throwable::printStackTrace);

		// In case you need to test the monitor for few minutes, uncomment next lines
		// TimeUnit.MINUTES.sleep(2);
		// subscription.unsubscribe();

		System.out.println("TEST FINISHED");

	}

}
