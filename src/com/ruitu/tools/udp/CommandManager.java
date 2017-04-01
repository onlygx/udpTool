package com.ruitu.tools.udp;

import com.ruitu.tools.model.CommandModel;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.net.*;

/**
 * Created by gaoxiang on 2017/4/1.
 */
public class CommandManager {

    private static DatagramSocket socket;
    private static InetAddress hostAddress ;
    private final static Integer PORT = 8302;

    /**
     * 发送方地址
     */
    private static String sendAddress;

    /**
     * 发送方ip地址
     */
    private static Integer[] sendIp;

    /**
     * 接收方地址
     */
    private static String receiveAddress;

    /**
     * 接收方IP地址
     */
    private static Integer[] receiveIp;

    /**
     * 初始化
     * @param send 发送方地址
     * @param ip 发送方ip
     * @throws SocketException 创建连接失败
     * @throws UnknownHostException 绑定主机失败
     */
    public static void init(String send,Integer[] ip) throws SocketException, UnknownHostException {
        sendAddress = send;
        sendIp = ip;
        //创建连接
        socket = new DatagramSocket(PORT);
        //发送给局域网所有设备
        hostAddress = InetAddress.getByName("255.255.255.255");
        //开启接收处理线程
        new Thread(new ReceiveThread(socket)).start();
    }

    /**
     * 呼叫
     */
    public static Boolean conn(String receive) throws IOException {
        //初始化接收人地址
        receiveAddress = receive;
        //获取byte数组
        CommandModel commandModel = new CommandModel(sendAddress,sendIp,receiveAddress,receiveIp);
        byte[] bytes = commandModel.getConnectionBytes();

        //初始化发送包
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length,hostAddress,PORT);
        //发送数据包
        socket.send(dp);
        return true;
    }

    /**
     * 挂断通话
     */
    public static Boolean off() throws IOException {
        //获取byte数组
        CommandModel commandModel = new CommandModel(sendAddress,sendIp,receiveAddress,receiveIp);
        byte[] bytes = commandModel.getDisConnectionBytes();

        //初始化发送包
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length,hostAddress,PORT);
        //发送数据包
        socket.send(dp);
        return true;
    }




    //------------------------ GetTer and SetTer -------------------------------

    public static String getSendAddress() {
        return sendAddress;
    }

    public static void setSendAddress(String sendAddress) {
        CommandManager.sendAddress = sendAddress;
    }

    public static Integer[] getSendIp() {
        return sendIp;
    }

    public static void setSendIp(Integer[] sendIp) {
        CommandManager.sendIp = sendIp;
    }

    public static String getReceiveAddress() {
        return receiveAddress;
    }

    public static void setReceiveAddress(String receiveAddress) {
        CommandManager.receiveAddress = receiveAddress;
    }

    public static Integer[] getReceiveIp() {
        return receiveIp;
    }

    public static void setReceiveIp(Integer[] receiveIp) {
        CommandManager.receiveIp = receiveIp;
    }
}
