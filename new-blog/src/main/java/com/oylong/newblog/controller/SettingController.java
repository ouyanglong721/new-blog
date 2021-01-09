package com.oylong.newblog.controller;

import com.oylong.newblog.entity.Result;
import com.oylong.newblog.entity.Setting;
import com.oylong.newblog.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/settings")
@Api(tags = "系统设置相关的接口")
public class SettingController {
    @Resource
    SettingService settingService;

    @ApiOperation("更新设置")
    @PutMapping
    public Result updateSetting(@Valid @RequestBody Setting setting){
        return settingService.updateSetting(setting);
    }

    @ApiOperation("获取当前设置")
    @GetMapping
    public Result getSetting(){
        return settingService.getSetting();
    }
}
