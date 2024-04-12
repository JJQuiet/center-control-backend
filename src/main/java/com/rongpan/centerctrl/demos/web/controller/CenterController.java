package com.rongpan.centerctrl.demos.web.controller;

import cn.hutool.core.util.StrUtil;
import com.rongpan.centerctrl.demos.web.config.GlobalConfig;
import com.rongpan.centerctrl.demos.web.dao.*;
import com.rongpan.centerctrl.demos.web.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping()
@RestController
@CrossOrigin
@Slf4j
public class CenterController {
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

    @GetMapping("/video/out/list")
    public Object videooutlist() {
        return videocmdRepository.findAll();
    }
    @GetMapping("/video/inp/list")
    public Object videoinplist() {
        return videoinpRepository.findAll();
    }
    @GetMapping("/video/in/changealiasname")
    public Object videoinchangealiasname(@RequestParam Long id,@RequestParam String aliasname) {
        VideoinpEntity entity = videoinpRepository.getById(id);
        entity.setAliasname(aliasname);
        return videoinpRepository.save(entity);
    }
    @GetMapping("/video/out/changealiasname")
    public Object videooutchangealiasname(@RequestParam Long id,@RequestParam String aliasname) {
        VideocmdEntity entity = videocmdRepository.getById(id);
        entity.setAliasname(aliasname);
        return videocmdRepository.save(entity);
    }
    @GetMapping("/voice/in/changealiasname")
    public Object voiceinchangealiasname(@RequestParam Long id,@RequestParam String aliasname) {
        VicecmdEntity entity = vicecmdRepository.getById(id);
        entity.setAliasname(aliasname);
        return vicecmdRepository.save(entity);
    }
    @GetMapping("/voice/out/changealiasname")
    public Object voiceoutchangealiasname(@RequestParam Long id,@RequestParam String aliasname) {
        ViceoutEntity entity = viceoutRepository.getById(id);
        entity.setAliasname(aliasname);
        return viceoutRepository.save(entity);
    }
    @GetMapping("/vice/inp/list")
    public Object viceinplist() {
        return vicecmdRepository.findAll();
    }
    @GetMapping("/vice/out/list")
    public Object viceooutlist() {
        return viceoutRepository.findAll();
    }
    @GetMapping("/vice/sound/list")
    public Object vicesoundlist() {
        return vicesoundcmdRepository.findAll();
    }
    @GetMapping("/video/out/save")
    public Object videooutsave(@RequestParam Long id,@RequestParam String aliasname,@RequestParam String cmdname,@RequestParam String cmddata) {
        VideocmdEntity entity = videocmdRepository.getById(id);
        entity.setAliasname(aliasname);

        entity.setCmddata(cmddata);
        entity.setCmdname(cmdname);
        String[] cmdArr = cmddata.split("&");
        for (String s : cmdArr) {
            if(StrUtil.isEmpty(s) == false)
            GlobalConfig.tcpClient.sendCenterCtl(s, GlobalConfig.centerIp);
        }
        return videocmdRepository.save(entity);
    }
    /**
     *  视频输入修改
     * @param id
     * @param aliasname
     * @param watermark 发水印前，先发/video/inp/watermark/open
     * @return
     */
    @GetMapping("/video/inp/save")
    public Object videoinpsave(@RequestParam Long id,@RequestParam String aliasname,@RequestParam String watermark) {
        VideoinpEntity entity = videoinpRepository.getById(id);
            entity.setAliasname(aliasname);
        if(entity.getWatermark().equals(watermark) == false)
        {
            entity.setWatermark(watermark);
            GlobalConfig.tcpClient.sendCenterCtl(StrUtil.format("USER/I/{}:<<{}>>%;",entity.getInputtype(),watermark), GlobalConfig.centerIp);
        }
        return videoinpRepository.save(entity);

    }
    @GetMapping("/vice/inp/save")
    public Object viceinpsave(@RequestParam Long id,@RequestParam String aliasname,String cmddata,String cancelcmd) {
        VicecmdEntity entity = vicecmdRepository.getById(id);
        entity.setAliasname(aliasname);
        entity.setCmddata(cmddata);
        if(StrUtil.isEmpty(cancelcmd))
        {
            //说明是增加
            GlobalConfig.tcpClient.sendCenterCtl(cmddata, GlobalConfig.centerIp);
        }
        else
        {
            //说明是删除
            GlobalConfig.tcpClient.sendCenterCtl(cancelcmd, GlobalConfig.centerIp);
        }
        return vicecmdRepository.save(entity);
    }
    @GetMapping("/vice/out/save")
    public Object viceooutsave(@RequestParam Long id,@RequestParam String aliasname) {
        ViceoutEntity entity = viceoutRepository.getById(id);
        entity.setAliasname(aliasname);
        return viceoutRepository.save(entity);
    }
    @GetMapping("/vice/sound/save")
    public Object vicesoundsave(@RequestParam Long id,@RequestParam String aliasname,@RequestParam String cmddata) {
        VicesoundEntity entity = vicesoundcmdRepository.getById(id);
        entity.setAliasname(aliasname);
        entity.setCmddata(cmddata);
//        String[] cmdArr = cmddata.split("&");
//        for (String s : cmdArr) {
//            if(StrUtil.isEmpty(s) == false)
            GlobalConfig.tcpClient.sendCenterCtl(cmddata, GlobalConfig.centerIp);
//        }
        return vicesoundcmdRepository.save(entity);
    }
    @GetMapping("/video/inp/watermark/open")
    public Object viceoinpWatermarkOpen(@RequestParam String inputtype) {
        GlobalConfig.tcpClient.sendCenterCtl(StrUtil.format("USER/I/{}:0901%;",inputtype), GlobalConfig.centerIp);
        return "success";
    }
}
