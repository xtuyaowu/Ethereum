package com.redhat.helloworld.contract;

import com.redhat.helloworld.util.Consts;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.CompletableFuture;

/**
 * @author littleredhat
 * <p>
 * 部署合约方式：
 * 一、wallet 部署
 * 二、geth 部署
 * 三、web3j 部署
 */
public class HelloWorldMain {

    public static void main(String[] args) throws Exception {
        // 获取凭证
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
        System.out.println("getCredentialsAddress : " + credentials.getAddress());

        // defaults to http://localhost:8545/
        // Web3j web3 = Web3j.build(new HttpService("http://xxxxxxx.westus.cloudapp.azure.com:8545/"));
        Web3j web3j = Web3j.build(new HttpService());

        // 部署合约
        /*
        HelloWorldContract contract = HelloWorldContract.deploy(web3j, credentials,
                 Consts.GAS_PRICE, Consts.GAS_LIMIT).send();
         System.out.println("getContractAddress : " + contract.getContractAddress());
        */

        // 加载合约 Consts.HELLOWORLD_ADDR
        HelloWorldContract loadContract = HelloWorldContract.load(Consts.pb0dAddr, web3j, credentials,
                Consts.GAS_PRICE, Consts.GAS_LIMIT);
        System.out.println("getContractAddress : " + loadContract.getContractAddress());

        ////////// 同步请求方式 //////////
        // set
/*        TransactionReceipt transactionReceipt = loadContract.set(30000).send();
        System.out.println("waiting..."); // 进入阻塞
        System.out.println("set : " + transactionReceipt.getTransactionHash());*/

        // get
/*        Uint256 result = loadContract.get().send();
        System.out.println("waiting..."); // 进入阻塞
        System.out.println("get : " + result.getValue().intValue());*/


        ////////// 异步请求方式 //////////
        // set
/*        CompletableFuture<TransactionReceipt> transactionReceiptAsync = loadContract.set(10000).sendAsync();
        System.out.println("waiting..."); // 马上返回
        System.out.println("set : " + transactionReceiptAsync.get().getTransactionHash());*/

        // get
        /*
        CompletableFuture<Uint256> resultAsync = loadContract.get().sendAsync();
        System.out.println("waiting..."); // 马上返回
        System.out.println("get : " + resultAsync.get().getValue().intValue());
        */

 /*       TransactionReceipt transactionReceipt = loadContract.addUser("0xF2D9841560838d92e42670153e0a5aea90176f43").send();
        System.out.println("waiting..."); // 进入阻塞
        System.out.println("set : " + transactionReceipt.getTransactionHash());*/

        CompletableFuture<TransactionReceipt> transactionReceiptAsync = loadContract.addUser("0x775597a534bd52550B23af446c93ea3b755B6c07").sendAsync();
        System.out.println("waiting..."); // 马上返回
        System.out.println("set : " + transactionReceiptAsync.get().getTransactionHash());
    }
}
