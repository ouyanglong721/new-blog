package com.oylong.newblog.controller;

import com.oylong.newblog.entity.Notice;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.NoticeService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "用于通知公告的接口")
@RestController
@RequestMapping("/notices")
public class NoticeController {
    @Resource
    NoticeService noticeService;


    @GetMapping
    public Result getNotices(@RequestParam("page") int page, @RequestParam("limit") int limit){
        return  noticeService.selectNotices(page, limit);
    }

    @PostMapping
    public Result insertNotice(@RequestBody Notice notice){
        return  noticeService.insertNotice(notice);
    }

    @PutMapping
    public Result updateNotice(@RequestBody @Valid Notice notice){
        return  noticeService.updateNotice(notice);
    }

    @DeleteMapping("/{id}")
    public Result deleteNotice(@PathVariable("id") Long id){
        return  noticeService.deleteNotice(id);
    }

    @GetMapping("/{id}")
    public Result selectNoticeById(@PathVariable("id") Long id){
        return noticeService.selectNoticeById(id);
    }
}
