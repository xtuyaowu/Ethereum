package com.redhat.helloworld.contract;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author littleredhat
 */
public interface HelloWorldInterface {

    // get
    public RemoteCall<Uint256> get();

    // set
    public RemoteCall<TransactionReceipt> set(int x);



    // 账户加入合约
    public RemoteCall<TransactionReceipt> addUser(String userAddress);

    // 上传简历
    public RemoteCall<TransactionReceipt> resumeUploadTask(String userAddress, int _taskId, String _describe, int rewardAmount);

    // 转币
    public RemoteCall<TransactionReceipt> pay(String userAddress);

    // 查询用户代币余额
    public RemoteCall<Uint256> queryUserBalanceOf(String userAddress);
}
