package com.redhat.helloworld.test;

import com.redhat.helloworld.util.Consts;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;

/**
 * @author littleredhat
 */
public class GenerateWalletTest {

    public static void main(String[] args) throws Exception {
        // To create a new wallet file
        // String password, File destinationDirectory, boolean useFullScrypt Consts.PASSWORD
        String fileName = WalletUtils.generateNewWalletFile("ACCOUNT3", new File(Consts.DIRECTORY), true);
        System.out.println(fileName);// 文件钱包名称

        // load the credentials
/*        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);// Consts.PATH 钱包路径
        System.out.println(credentials.getAddress());// 钱包地址*/
    }
}
