package com.oylong.newblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oylong.newblog.dao.NoticeMapper;
import com.oylong.newblog.entity.Notice;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.NoticeService;
import com.oylong.newblog.utils.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    @Override
    public Result insertNotice(Notice notice) {
        noticeMapper.insert(notice);
        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result updateNotice(Notice notice) {
        Notice nt = noticeMapper.selectById(notice.getId());
        if(nt == null){
            return ResultUtil.buildUnSuccessResult("通知不存在");
        }

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id", notice.getId());

        if(notice.getStartTime() == null || notice.getEndTime() == null){
            updateWrapper.set("start_time", null);
            updateWrapper.set("end_time", null);
        }

        noticeMapper.update(notice, updateWrapper);

        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result deleteNotice(Long id) {
        Notice nt = noticeMapper.selectById(id);
        if(nt == null){
            return ResultUtil.buildUnSuccessResult("通知不存在");
        }
        noticeMapper.deleteById(id);

        return ResultUtil.buildSuccessResult();
    }

    @Override
    public Result selectNotices(int page, int limit) {
        IPage<Notice> iPage = new Page<>(page, limit);
        IPage<Notice> p = noticeMapper.selectPage(iPage, null);
        Map<String, Object> map = new HashMap<>();
        map.put("total", p.getTotal());
        map.put("list", p.getRecords());
        Result result = ResultUtil.buildSuccessResult();
        result.setData(map);
        return result;
    }

    @Override
    public Result selectNoticeById(Long id) {
        Notice nt = noticeMapper.selectById(id);
        if(nt == null){
            return ResultUtil.buildUnSuccessResult("此通知不存在");
        }
        Result result = ResultUtil.buildSuccessResult();
        result.setData(nt);
        return result;
    }
}
