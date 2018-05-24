package classes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import rx.Observable;
import rx.functions.Func1;

/**
 * <p>
 * Auto generated code.
 * <p>
 * <strong>Do not modify!</strong>
 * <p>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j
 * command line tools</a>, or the
 * org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen
 * module</a> to update.
 *
 * <p>
 * Generated with web3j version 3.4.0.
 */
public class RONtoUSDOracle extends Contract {
	private static final String BINARY = "6060604052341561000f57600080fd5b60008054600160a060020a033316600160a060020a031990911617905561019b8061003b6000396000f3006060604052600436106100615763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166317613d0a81146100665780631a9879671461007b57806366d1090a146100915780638da5cb5b146100b6575b600080fd5b341561007157600080fd5b6100796100f2565b005b341561008657600080fd5b610079600435610120565b341561009c57600080fd5b6100a461014d565b60405190815260200160405180910390f35b34156100c157600080fd5b6100c9610153565b60405173ffffffffffffffffffffffffffffffffffffffff909116815260200160405180910390f35b7fb512c03202fad013e86b96648e49f48a3f1a2894ca72a7e97701104497854da060405160405180910390a1565b6000543373ffffffffffffffffffffffffffffffffffffffff90811691161461014857600080fd5b600155565b60015490565b60005473ffffffffffffffffffffffffffffffffffffffff16815600a165627a7a72305820e1492c66df347b318110d733da11a0a20397a4cb6c40b6a8a0481f53bddc2e510029";

	public static final String FUNC_UPDATERONTOUSD = "updateRONtoUSD";

	public static final String FUNC_SETRONTOUSDRATE = "setRONToUSDRate";

	public static final String FUNC_GETRONTOUSDRATE = "getRonToUSDRate";

	public static final String FUNC_OWNER = "owner";

	public static final Event CALLBACKGETRONTOUSD_EVENT = new Event("CallbackGetRONtoUSD",
			Arrays.<TypeReference<?>>asList(), Arrays.<TypeReference<?>>asList());;

	protected RONtoUSDOracle(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	protected RONtoUSDOracle(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public RemoteCall<TransactionReceipt> updateRONtoUSD() {
		final Function function = new Function(FUNC_UPDATERONTOUSD, Arrays.<Type>asList(),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<TransactionReceipt> setRONToUSDRate(BigInteger rate) {
		final Function function = new Function(FUNC_SETRONTOUSDRATE,
				Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate)),
				Collections.<TypeReference<?>>emptyList());
		return executeRemoteCallTransaction(function);
	}

	public RemoteCall<BigInteger> getRonToUSDRate() {
		final Function function = new Function(FUNC_GETRONTOUSDRATE, Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeRemoteCallSingleValueReturn(function, BigInteger.class);
	}

	public RemoteCall<String> owner() {
		final Function function = new Function(FUNC_OWNER, Arrays.<Type>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {
				}));
		return executeRemoteCallSingleValueReturn(function, String.class);
	}

	public static RemoteCall<RONtoUSDOracle> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		return deployRemoteCall(RONtoUSDOracle.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
	}

	public static RemoteCall<RONtoUSDOracle> deploy(Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		return deployRemoteCall(RONtoUSDOracle.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
	}

	public List<CallbackGetRONtoUSDEventResponse> getCallbackGetRONtoUSDEvents(TransactionReceipt transactionReceipt) {
		List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CALLBACKGETRONTOUSD_EVENT,
				transactionReceipt);
		ArrayList<CallbackGetRONtoUSDEventResponse> responses = new ArrayList<CallbackGetRONtoUSDEventResponse>(
				valueList.size());
		for (Contract.EventValuesWithLog eventValues : valueList) {
			CallbackGetRONtoUSDEventResponse typedResponse = new CallbackGetRONtoUSDEventResponse();
			typedResponse.log = eventValues.getLog();
			responses.add(typedResponse);
		}
		return responses;
	}

	public Observable<CallbackGetRONtoUSDEventResponse> callbackGetRONtoUSDEventObservable(EthFilter filter) {
		return web3j.ethLogObservable(filter).map(new Func1<Log, CallbackGetRONtoUSDEventResponse>() {
			@Override
			public CallbackGetRONtoUSDEventResponse call(Log log) {
				Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CALLBACKGETRONTOUSD_EVENT, log);
				CallbackGetRONtoUSDEventResponse typedResponse = new CallbackGetRONtoUSDEventResponse();
				typedResponse.log = log;
				return typedResponse;
			}
		});
	}

	public Observable<CallbackGetRONtoUSDEventResponse> callbackGetRONtoUSDEventObservable(
			DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
		EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
		filter.addSingleTopic(EventEncoder.encode(CALLBACKGETRONTOUSD_EVENT));
		return callbackGetRONtoUSDEventObservable(filter);
	}

	public static RONtoUSDOracle load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		return new RONtoUSDOracle(contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	public static RONtoUSDOracle load(String contractAddress, Web3j web3j, TransactionManager transactionManager,
			BigInteger gasPrice, BigInteger gasLimit) {
		return new RONtoUSDOracle(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
	}

	public static class CallbackGetRONtoUSDEventResponse {
		public Log log;
	}
}
