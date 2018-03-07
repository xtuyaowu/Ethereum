package com.redhat.helloworld.contract;

import com.redhat.helloworld.util.Consts;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.io.File;
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

        ////////// 正式调用合约 //////////
        // 一、创建钱包用户 + 用户加入合约
        // 1、创建钱包用户
        // String password, File destinationDirectory, boolean useFullScrypt Consts.PASSWORD
/*        String fileName = WalletUtils.generateNewWalletFile("ACCOUNT3", new File(Consts.DIRECTORY), true);
        System.out.println(fileName);// 文件钱包名称*/

        // 2、用户加入合约
/*        CompletableFuture<TransactionReceipt> transactionReceiptAsync = loadContract.addUser("0xDf32D54e24f9A3df2483e1E9489937D159EC33C1").sendAsync();
        System.out.println("waiting..."); // 马上返回
        System.out.println("set : " + transactionReceiptAsync.get().getTransactionHash());*/


        // 二、上传简历
        CompletableFuture<TransactionReceipt> transactionResumeUploadTaskReceiptAsync = loadContract.resumeUploadTask("0xDf32D54e24f9A3df2483e1E9489937D159EC33C1", 3, "task3", 300).sendAsync();
        System.out.println("waiting..."); // 马上返回
        System.out.println("set : " + transactionResumeUploadTaskReceiptAsync.get().getTransactionHash());


        //三、 转币
        CompletableFuture<TransactionReceipt> transactionPayReceiptAsync = loadContract.pay("0xDf32D54e24f9A3df2483e1E9489937D159EC33C1").sendAsync();
        System.out.println("waiting..."); // 马上返回
        System.out.println("set : " + transactionPayReceiptAsync.get().getTransactionHash());

        //四、 查询用户代币余额
        CompletableFuture<Uint256> resultAsync = loadContract.queryUserBalanceOf("0xDf32D54e24f9A3df2483e1E9489937D159EC33C1").sendAsync();
        System.out.println("waiting..."); // 马上返回
        System.out.println("get : " + resultAsync.get().getValue().intValue());

    }
}
