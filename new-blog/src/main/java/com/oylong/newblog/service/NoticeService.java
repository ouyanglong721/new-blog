package com.oylong.newblog.service;

import com.oylong.newblog.entity.Notice;
import com.oylong.newblog.entity.Result;

public interface NoticeService {
    Result insertNotice(Notice notice);
    Result updateNotice(Notice notice);
    Result deleteNotice(Long id);
    Result selectNotices(int page, int limit);

    Result selectNoticeById(Long id);
}
