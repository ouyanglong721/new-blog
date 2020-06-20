package com.oylong.newblog.service;

import com.oylong.newblog.entity.Result;
import com.oylong.newblog.entity.Setting;

public interface SettingService {
    Result updateSetting(Setting setting);

    Result getSetting();

    Boolean commentEnabled();
}
