package com.oylong.newblog.controller;

import com.oylong.newblog.entity.Result;
import com.oylong.newblog.entity.Setting;
import com.oylong.newblog.service.SettingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/settings")
public class SettingController {
    @Resource
    SettingService settingService;

    @PutMapping
    public Result updateSetting(@Valid @RequestBody Setting setting){
        return settingService.updateSetting(setting);
    }

    @GetMapping
    public Result getSetting(){
        return settingService.getSetting();
    }
}
