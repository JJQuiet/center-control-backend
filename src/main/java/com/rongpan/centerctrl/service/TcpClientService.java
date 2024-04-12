package com.rongpan.centerctrl.service;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import com.rongpan.centerctrl.demos.web.netty.BootNettyClientChannel;
import com.rongpan.centerctrl.demos.web.netty.BootNettyClientChannelCache;
import com.rongpan.centerctrl.demos.web.netty.BootNettyClientThread;
import com.rongpan.centerctrl.demos.web.unit.StrUnit;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class TcpClientService {
    //    public static volatile Map<String,Date> sendOverTimeMap = new ConcurrentHashMap<>();
    @Async
    public void tcpClientRun(String host,int port) {
        String address = host;
        BootNettyClientThread thread = new BootNettyClientThread(port, address);
        thread.start();
    }
    @Async
    public void tcpClientRun(String host) {
        tcpClientRun(host,50600);
    }
    public boolean getIsConnect(String host){
        if(BootNettyClientChannelCache.channelMapCache.size() > 0)
        {
            for (Map.Entry<String, BootNettyClientChannel> entry : BootNettyClientChannelCache.channelMapCache.entrySet()) {
                BootNettyClientChannel bootNettyChannel = entry.getValue();
                if(bootNettyChannel != null && bootNettyChannel.getChannel().isOpen()){
                    SocketAddress address = bootNettyChannel.getChannel().remoteAddress();
                    String hostName = ((InetSocketAddress) address).getHostName();
                    if(hostName.equals(host))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void sendCenterCtl(String hexStr,String host){
//        hexStr = StrUnit.toASCII(hexStr);
//        hexStr = CharsetUtil.convert(hexStr, CharsetUtil.CHARSET_UTF_8, CharsetUtil.ISO);
//        byte[] asciiBytes = hexStr.getBytes(StandardCharsets.US_ASCII);
        byte[] asciiBytes = null;
        try {
            asciiBytes = hexStr.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            log.error("发送的ascii 转码失败");
        }
        if(BootNettyClientChannelCache.channelMapCache.size() > 0){
            for (Map.Entry<String, BootNettyClientChannel> entry : BootNettyClientChannelCache.channelMapCache.entrySet()) {
                BootNettyClientChannel bootNettyChannel = entry.getValue();
                if(bootNettyChannel != null && bootNettyChannel.getChannel().isOpen()){
                    //等待返回，超时时间为10s
                    SocketAddress address = bootNettyChannel.getChannel().remoteAddress();
                    String hostName = ( ((InetSocketAddress) address).getAddress()).getHostAddress();
                    if(host.equals(hostName))
                    {
                        log.info("tcp发送消息 host = "+host+",data = "+hexStr);
                        if(asciiBytes != null)
                        bootNettyChannel.getChannel().writeAndFlush(Unpooled.buffer().writeBytes(asciiBytes));
//                        bootNettyChannel.getChannel().writeAndFlush(hexStr);
                        break;
                    }
                }
            }
        }
    }

    public void send(String hexStr,String host){
//        if(BootNettyClientChannelCache.channelMapCache.size() > 0){
//            for (Map.Entry<String, BootNettyClientChannel> entry : BootNettyClientChannelCache.channelMapCache.entrySet()) {
//                BootNettyClientChannel bootNettyChannel = entry.getValue();
//                if(bootNettyChannel != null && bootNettyChannel.getChannel().isOpen()){
//                    //等待返回，超时时间为10s
//                    SocketAddress address = bootNettyChannel.getChannel().remoteAddress();
//                    String hostName = ( ((InetSocketAddress) address).getAddress()).getHostAddress();
//                    if(host.equals(hostName))
//                    {
//                        //55AA0000010100D90000000000002800000028002564697370303B3131392564697370313B3133372564697370323B3132312564697370333B31303000000D0A
//                        //55aa0000010100d90000000000002800000028002564697370303b3131392564697370313b3133372564697370323b3132312564697370333b31303000000d0a
//                        log.info(host+" modbus send msg: "+hexStr);
//                        bootNettyChannel.getChannel().writeAndFlush(Unpooled.buffer().writeBytes(HexUtil.decodeHex(hexStr)));
////                      sendOverTimeMap.put(hostName,DateUtil.offsetSecond(DateUtil.date(), 10));
//                        break;
//                    }
//                }
//            }
//        }
        byte[] asciiBytes = hexStr.getBytes(StandardCharsets.US_ASCII);
        if(BootNettyClientChannelCache.channelMapCache.size() > 0){
            for (Map.Entry<String, BootNettyClientChannel> entry : BootNettyClientChannelCache.channelMapCache.entrySet()) {
                BootNettyClientChannel bootNettyChannel = entry.getValue();
                if(bootNettyChannel != null && bootNettyChannel.getChannel().isOpen()){
                    //等待返回，超时时间为10s
                    SocketAddress address = bootNettyChannel.getChannel().remoteAddress();
                    String hostName = ( ((InetSocketAddress) address).getAddress()).getHostAddress();
                    if(host.equals(hostName))
                    {
                        bootNettyChannel.getChannel().writeAndFlush(Unpooled.buffer().writeBytes(asciiBytes));
                        break;
                    }
                }
            }
        }
    }

}

