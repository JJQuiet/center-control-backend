package com.rongpan.centerctrl.demos.web.netty;
import cn.hutool.core.util.HexUtil;
import com.rongpan.centerctrl.demos.web.config.GlobalConfig;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ModbusDecode extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        int len = in.readableBytes();       //这里得到可读取的字节长度
        in.markReaderIndex();               //包头做标记位，后面可以重新回到数据包头开始读数据
        //有数据时开始读数据包
        if (len > 0) {
            byte[] src = new byte[len];
            in.readBytes(src);          //把数据读到字节数组中(读取完之后指针会到最后一个数据)
            in.resetReaderIndex();      //重置当前指针到标记位(包头),用于重新读取接收的数据，直至接收完完整数据包
            if(len>1) {
                //判断包头是否为约定的字节
//                if((src[0] & 0x000000ff) != 0xAB || (src[1] & 0x000000ff) != 0xCD) {
//                    log.warn("无法识别的包头，转换为16进制的包头为：{}", StringUtil.byteToHexString(src));
//                    // 包头不对，直接断开连接
//                    channelHandlerContext.close();
//                }
                //字节转换为16进制，判断最后两个字节是否为结尾字节,0x000000ff等于十进制的255
//                if((src[len-2] & 0x000000ff) == 0xEF && (src[len-1] & 0x000000ff) == 0xGG) {
                in.skipBytes(in.readableBytes());//标记已读取完毕
                //收到了尾部字节，完成接收
//                String hexStr = HexUtil.encodeHexStr(src);
//                //CRC 验证hexStr
//                out.add(hexStr);

                String srcStr = GlobalConfig.bytesToAscii(src);
                out.add(srcStr);

//              }
            }
            //非结尾字节，继续接收,当数据包的长度不够时直接return，netty在缓冲区有数据时会一直调用decode方法，所以我们只需要等待下一个数据包传输过来一起解析(解决半包问题)
            return;
        }else {
            // 发空数据包过来的家伙直接断开连接
            channelHandlerContext.close();
        }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("TCP连接异常！，信息：{}",cause.getMessage(),cause);
//        ctx.close();
    }
}
