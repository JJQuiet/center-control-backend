package com.rongpan.centerctrl.demos.web.config;

import com.rongpan.centerctrl.demos.web.dao.*;
import com.rongpan.centerctrl.service.TcpClientService;
import org.springframework.beans.factory.annotation.Autowired;

public class GlobalConfig {
    public static boolean isRunnerSuccess = false;
    public static String centerIp = "192.168.0.178";
    public static int centerPort = 4001;

    public static String pingIp = "192.168.0.111";
    public static int pingPort = 38001;

    public static VideocmdRepository videocmdRep;

    public static VicecmdRepository vicecmdRep;

    public static ViceoutRepository viceoutRep;

//    public static VideoinpRepository videoinpRep;

    public static VicesoundcmdRepository vicesoundcmdRep;

    public static TcpClientService tcpClient;

    public static String bytesToAscii(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            // 检查字节是否在ASCII范围内（0-127）
            if (b >= 0 && b <= 127) {
                // 将字节转换为字符并追加到StringBuilder
                sb.append((char) b);
            } else {
                // 对于非ASCII字节，可以选择替换为其他字符或忽略
                sb.append('?'); // 例如，用问号替换
            }
        }
        return sb.toString();
    }
}
