package com.ruitu.tools.model;

import com.ruitu.tools.util.ByteUtils;

/**
 * Created by gaoxiang
 */
public class CommandModel {

    /**
     * 请求头
     */
    private Header header;

    /**
     * 发送方地址
     */
    private String sendAddress;

    /**
     * 发送方ip地址
     */
    private Integer[] sendIp;

    /**
     * 接收方地址
     */
    private String receiveAddress;

    /**
     * 接收方IP地址
     */
    private Integer[] receiveIp;

    // --------------------------------- Construction ----------------------------
    public CommandModel(){
        this.header = new Header(1,1);
    }

    //此构造一般用于呼叫
    public CommandModel(String sendAddress, Integer[] sendIp, String receiveAddress, Integer[] receiveIp){
        this.header = new Header(1,1);
        this.sendAddress = sendAddress;
        this.sendIp = sendIp;
        this.receiveAddress = receiveAddress;
        this.receiveIp = receiveIp;
    }


    // -------------------------------- Method -----------------------

    /**
     * 获取呼叫用的byte数组
     * @return bytes
     */
    public byte[] getConnectionBytes(){
        Header header = new Header(1,1);
        byte[] headerBytes = header.getByteHeader();
        byte[] sendBytes = ByteUtils.fromString(sendAddress,20);
        byte[] sendIpBytes = ByteUtils.fromInts(sendIp,4);
        byte[] receiveBytes = ByteUtils.fromString(receiveAddress,20);

        byte[] manager = ByteUtils.byteMerger(headerBytes,sendBytes);
        manager = ByteUtils.byteMerger(manager,sendIpBytes);
        manager = ByteUtils.byteMerger(manager,receiveBytes);
        return manager;
    }

    /**
     * 获取挂断用的byte数组
     * @return bytes
     */
    public byte[] getDisConnectionBytes(){
        Header header = new Header(1,30);
        byte[] headerBytes = header.getByteHeader();
        byte[] sendBytes = ByteUtils.fromString(sendAddress,20);
        byte[] sendIpBytes = ByteUtils.fromInts(sendIp,4);
        byte[] receiveBytes = ByteUtils.fromString(receiveAddress,20);
        byte[] receiveIpBytes = ByteUtils.fromInts(receiveIp,4);

        byte[] manager = ByteUtils.byteMerger(headerBytes,sendBytes);
        manager = ByteUtils.byteMerger(manager,sendIpBytes);
        manager = ByteUtils.byteMerger(manager,receiveBytes);
        manager = ByteUtils.byteMerger(manager,receiveIpBytes);
        return manager;
    }




    // -------------------------------- GetTer and SetTer -------------------------

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public Integer[] getSendIp() {
        return sendIp;
    }

    public void setSendIp(Integer[] sendIp) {
        this.sendIp = sendIp;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public Integer[] getReceiveIp() {
        return receiveIp;
    }

    public void setReceiveIp(Integer[] receiveIp) {
        this.receiveIp = receiveIp;
    }
}
