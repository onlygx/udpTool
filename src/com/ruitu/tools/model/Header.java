package com.ruitu.tools.model;

import com.ruitu.tools.util.ByteUtils;

/**
 * Created by gaoxiang
 */
public class Header {

    /**
     * 协议包头 默认 XXXCID
     */
    private String headBody;

    /**
     * 协议命令 默认150
     */
    private Integer command;

    /**
     * 命令类型 1，发送 2 接收回执
     */
    private Integer type;

    /**
     * 操作命令，具体类型
     * 1，呼叫
     * 2，占线应答
     * 4，呼叫应答
     * 6，开始通话
     * 7，通话数据1
     * 8，通话数据2
     * 10，远程开锁
     * 30，通话结束
     */
    private Integer order;

    //--------------------------------------
    public Header(){}

    public Header(Integer type, Integer order){
        this.headBody = "XXXCID";
        this.command = 150;
        this.type = type;
        this.order = order;
    }

    /**
     * 获取当前对象转成byte[] 的值
     * @return bytes
     */
    public byte[] getByteHeader(){
        byte[] header = ByteUtils.fromString(headBody,6);
        byte[] control = ByteUtils.fromInts(new Integer[]{command,type,order},3);
        return ByteUtils.byteMerger(header,control);
    }
    //-------------------------------------

    public String getHeadBody() {
        return headBody;
    }

    public void setHeadBody(String headBody) {
        this.headBody = headBody;
    }

    public Integer getCommand() {
        return command;
    }

    public void setCommand(Integer command) {
        this.command = command;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
