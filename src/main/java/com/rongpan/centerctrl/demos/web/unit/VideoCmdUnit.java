package com.rongpan.centerctrl.demos.web.unit;

import cn.hutool.cron.timingwheel.SystemTimer;
import cn.hutool.cron.timingwheel.TimerTask;
import com.rongpan.centerctrl.demos.web.config.GlobalConfig;
import com.rongpan.centerctrl.demos.web.entity.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class VideoCmdUnit {
    public static void checkInit(){
        List<VideocmdEntity> arr =  GlobalConfig.videocmdRep.findAll();
        List<VicecmdEntity> vicearr =  GlobalConfig.vicecmdRep.findAll();
        List<ViceoutEntity> viceoutarr =  GlobalConfig.viceoutRep.findAll();
        List<VicesoundEntity> vicesoundarr = GlobalConfig.vicesoundcmdRep.findAll();

        if(arr.size() < 4  || vicearr.size() <13 || viceoutarr.size() < 13 || vicesoundarr.size() <14)
        {
            //加载场景9，默认场景
            log.info("没有数据库，通过加载场景9重置中控，并初始化数据库");
            GlobalConfig.tcpClient.sendCenterCtl("Recall9.", GlobalConfig.centerIp);

            SystemTimer systemTimer = new SystemTimer();
            systemTimer.start();
            systemTimer.addTask(new TimerTask(() -> VideoCmdUnit.init(), 8000));
        }

    }
    public static void init(){
        GlobalConfig.videocmdRep.deleteAll();
        GlobalConfig.vicecmdRep.deleteAll();
        GlobalConfig.viceoutRep.deleteAll();
        GlobalConfig.vicesoundcmdRep.deleteAll();

        List<VideocmdEntity> arr =  GlobalConfig.videocmdRep.findAll();
        List<VicecmdEntity> vicearr =  GlobalConfig.vicecmdRep.findAll();
        List<ViceoutEntity> viceoutarr =  GlobalConfig.viceoutRep.findAll();
        List<VicesoundEntity> vicesoundarr = GlobalConfig.vicesoundcmdRep.findAll();
        try {
                arr.add(new VideocmdEntity(null,"视频输出1","视频输出1","1","full","1V1."));
                arr.add(new VideocmdEntity(null,"视频输出2","视频输出2","2","full","2V2."));
                arr.add(new VideocmdEntity(null,"视频输出3","视频输出3","3","full","3V3."));
                arr.add(new VideocmdEntity(null,"视频输出4","视频输出4","4","full","4V4."));
                for (VideocmdEntity videocmdEntity : arr) {
                    GlobalConfig.videocmdRep.save(videocmdEntity);
//                    GlobalConfig.tcpClient.sendCenterCtl(videocmdEntity.getCmddata(), GlobalConfig.centerIp);
                }
            } catch (Exception e) {
            }

            try {
                //Recall9.
                //  2$A4.(取消选中输出2输入4)
                vicearr.add(new VicecmdEntity(null,"视频输入1","视频输入1","1","1A1."));
                vicearr.add(new VicecmdEntity(null,"视频输入2","视频输入2","2","2A2."));
                vicearr.add(new VicecmdEntity(null,"视频输入3","视频输入3","3","3A3."));
                vicearr.add(new VicecmdEntity(null,"视频输入4","视频输入4","4","4A4."));
                vicearr.add(new VicecmdEntity(null,"输入无线MIC","无线MIC","9","9A5."));
//                vicearr.add(new VicecmdEntity(null,"输入外接mic","外接mic","10","10A5."));
                vicearr.add(new VicecmdEntity(null,"输入CH1","CH1","11","11A6,5."));
                vicearr.add(new VicecmdEntity(null,"输入CH2","CH2","12","12A7."));
                vicearr.add(new VicecmdEntity(null,"输入CH3","CH3","13","13A8."));
                vicearr.add(new VicecmdEntity(null,"输入CH4","CH4","14","14A9."));
                vicearr.add(new VicecmdEntity(null,"输入CH5","CH5","15","15A10."));
                vicearr.add(new VicecmdEntity(null,"输入CH6","CH6","16","16A11."));
                vicearr.add(new VicecmdEntity(null,"输入CH7","CH7","17","17A12."));
                vicearr.add(new VicecmdEntity(null,"输入CH8","CH8","18","18A13."));
                for (VicecmdEntity vicecmdEntity : vicearr) {
                    GlobalConfig.vicecmdRep.save(vicecmdEntity);
//                    GlobalConfig.tcpClient.sendCenterCtl(vicecmdEntity.getCmddata(), GlobalConfig.centerIp);
                }
            } catch (Exception e) {
            }
        try {
            //Recall9.
            //1$A1.
            vicesoundarr.add(new VicesoundEntity(null,"视频输入1","视频输入1","1","SetInAudioDspGain/1:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"视频输入2","视频输入2","2","SetInAudioDspGain/2:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"视频输入3","视频输入3","3","SetInAudioDspGain/3:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"视频输入4","视频输入4","4","SetInAudioDspGain/4:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"输入无线MIC","无线MIC","9","SetInAudioDspGain/9:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"输出功放","功放","5","SetOutAudioDspGain/5:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"输入CH1","CH1","11","SetInAudioDspGain/11:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"输入CH2","CH2","12","SetInAudioDspGain/12:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"输入CH3","CH3","13","SetInAudioDspGain/13:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"输入CH4","CH4","14","SetInAudioDspGain/14:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"输入CH5","CH5","15","SetInAudioDspGain/15:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"输入CH6","CH6","16","SetInAudioDspGain/16:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"输入CH7","CH7","17","SetInAudioDspGain/17:0;"));
            vicesoundarr.add(new VicesoundEntity(null,"输入CH8","CH8","18","SetInAudioDspGain/18:0;"));
            for (VicesoundEntity vicesoundEntity : vicesoundarr) {
                GlobalConfig.vicesoundcmdRep.save(vicesoundEntity);
//                GlobalConfig.tcpClient.sendCenterCtl(vicesoundEntity.getCmddata(), GlobalConfig.centerIp);
            }
        } catch (Exception e) {
        }
            viceoutarr.add(new ViceoutEntity(null,"视频输出1","视频输出1","1"));
            viceoutarr.add(new ViceoutEntity(null,"视频输出2","视频输出2","2"));
            viceoutarr.add(new ViceoutEntity(null,"视频输出3","视频输出3","3"));
            viceoutarr.add(new ViceoutEntity(null,"视频输出4","视频输出4","4"));
            viceoutarr.add(new ViceoutEntity(null,"输出功放","功放","5"));
            viceoutarr.add(new ViceoutEntity(null,"输出CH1","CH1","6"));
            viceoutarr.add(new ViceoutEntity(null,"输出CH2","CH2","7"));
            viceoutarr.add(new ViceoutEntity(null,"输出CH3","CH3","8"));
            viceoutarr.add(new ViceoutEntity(null,"输出CH4","CH4","9"));
            viceoutarr.add(new ViceoutEntity(null,"输出CH5","CH5","10"));
            viceoutarr.add(new ViceoutEntity(null,"输出CH6","CH6","11"));
            viceoutarr.add(new ViceoutEntity(null,"输出CH7","CH7","12"));
            viceoutarr.add(new ViceoutEntity(null,"输出CH8","CH8","13"));
            for (ViceoutEntity viceoutEntity : viceoutarr) {
                GlobalConfig.viceoutRep.save(viceoutEntity);
            }
    }


}
