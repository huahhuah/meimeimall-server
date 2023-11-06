package cn.tedu.meimall.basic;

import lombok.Data;

import java.io.Serializable;

@Data

public class TransferObject<T extends Serializable> {
    private String sender;
    private long sendTime;
    private String receiver;
    private T data;

    public static <T extends Serializable> TransferObject<T> build(
            String sender, long sendTime, String receiver, T data) {
        TransferObject<T> transferObject = new TransferObject<>();
        transferObject.sender = sender;
        transferObject.sendTime = sendTime;
        transferObject.receiver = receiver;
        transferObject.data = data;
        return transferObject;
    }
}
