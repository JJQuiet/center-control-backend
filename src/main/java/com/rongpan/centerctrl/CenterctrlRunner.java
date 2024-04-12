package com.rongpan.centerctrl;

import com.rongpan.centerctrl.demos.web.config.GlobalConfig;
import com.rongpan.centerctrl.demos.web.dao.*;
import com.rongpan.centerctrl.demos.web.entity.VideocmdEntity;
import com.rongpan.centerctrl.demos.web.entity.VideoinpEntity;
import com.rongpan.centerctrl.service.TcpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CenterctrlRunner implements org.springframework.boot.ApplicationRunner {
    @Autowired
    TcpClientService tcpClientService;

    @Autowired
    VideocmdRepository videocmdRepository;

    @Autowired
    VicecmdRepository vicecmdRepository;

    @Autowired
    ViceoutRepository viceoutRepository;

    @Autowired
    VideoinpRepository videoinpRepository;


    @Autowired
    VicesoundcmdRepository vicesoundcmdRepository;


    public void run(ApplicationArguments args){
        List<VideoinpEntity> videoInpArr = videoinpRepository.findAll();
        if(videoInpArr.size() < 8)
        {
            videoinpRepository.deleteAll();
            for (int i = 0; i < 8; i++) {
                videoinpRepository.save(new VideoinpEntity(null,"视频输入"+(i+1),"视频输入"+(i+1),""+(i+1),""));
            }
        }
        //数据初始化
        GlobalConfig.videocmdRep = videocmdRepository;
        GlobalConfig.viceoutRep = viceoutRepository;
        GlobalConfig.vicecmdRep = vicecmdRepository;
        GlobalConfig.vicesoundcmdRep = vicesoundcmdRepository;

        GlobalConfig.tcpClient = tcpClientService;

        tcpClientService.tcpClientRun(GlobalConfig.centerIp,GlobalConfig.centerPort);
        tcpClientService.tcpClientRun(GlobalConfig.pingIp,GlobalConfig.pingPort);
        GlobalConfig.isRunnerSuccess = true;

    }
}
