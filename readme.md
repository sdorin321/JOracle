# JOracle

This is a very basic example of an Java Oracle that sets information into a smart contract

## Installation

+ Install Solidity Compiler

    ```
    sudo add-apt-repository ppa:ethereum/ethereum
    sudo apt-get update
    sudo apt-get install solc
    ```
    
+ Download [web3j(v3.4.0)](https://github.com/web3j/web3j/releases) 

## Usage

+ Compile the contract to generate *abi* and *bin* files
    ```
    solc ApiBase.sol --bin --abi --optimize -o build/ 
    ```
+ Generate the java class coresponding to the contract by using the web3j downloaded tool:

    ```
    ~/web3j-3.4.0/bin/web3j solidity generate '~/pathToBinFile/ApiBase.bin' '~/pathToAbiFile/ApiBase.abi' -p packageName -o '~/pathToJavaClassesFolder'
    ```

## History


## Credits


## License

