package com.rongpan.centerctrl.demos.web.controller;

import com.rongpan.centerctrl.demos.web.config.GlobalConfig;
import com.rongpan.centerctrl.service.TcpClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping()
@RestController
@CrossOrigin
@Slf4j
public class PingController {
    @Autowired
    TcpClientService tcpClientService;

    @GetMapping("/ping/four")
    public Object pingfour() {
        tcpClientService.send("FF9100002325FF", GlobalConfig.pingIp);
        return "success";
    }
    @GetMapping("/ping/leftright")
    public Object pingleftright() {
        tcpClientService.send("FFC500002325FF", GlobalConfig.pingIp);
        return "success";
    }
    @GetMapping("/ping/topbottom")
    public Object pingtopbottom() {
        tcpClientService.send("FFC600002325FF", GlobalConfig.pingIp);
        return "success";
    }
    @GetMapping("/ping/three")
    public Object pingthree() {
        tcpClientService.send("FFC700002325FF", GlobalConfig.pingIp);
        return "success";
    }

}
