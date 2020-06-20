package com.oylong.newblog.service.impl;

import com.oylong.newblog.constant.ResultCode;
import com.oylong.newblog.dao.SettingMapper;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.entity.Setting;
import com.oylong.newblog.exception.CustomException;
import com.oylong.newblog.service.SettingService;
import com.oylong.newblog.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SettingServiceImpl implements SettingService {
    @Resource
    SettingMapper settingMapper;

    @Override
    public Result updateSetting(Setting setting){
        Setting setting1 = settingMapper.selectById(setting.getId());
        if(setting1 == null){
            throw new CustomException(ResultCode.PARAM_NOT_VALID);
        }
        settingMapper.updateById(setting);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result getSetting() {
        Setting setting = settingMapper.selectById(0);
        Result result = ResultUtil.buildSuccessResult();
        result.setData(setting);
        return result;
    }

    @Override
    public Boolean commentEnabled() {
        Setting setting = settingMapper.selectById(0);
        return setting.getCommentEnabled();
    }
}
