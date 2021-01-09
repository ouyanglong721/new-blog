package com.oylong.newblog.controller;

import com.oylong.newblog.entity.Notice;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "用于通知公告的接口")
@RestController
@RequestMapping("/notices")
public class NoticeController {
    @Resource
    NoticeService noticeService;


    @ApiOperation("获取通知公告")
    @GetMapping
    public Result getNotices(@RequestParam("page") int page, @RequestParam("limit") int limit){
        return  noticeService.selectNotices(page, limit);
    }

    @ApiOperation("添加公告")
    @PostMapping
    public Result insertNotice(@RequestBody @Valid Notice notice){
        return  noticeService.insertNotice(notice);
    }

    @ApiOperation("更新公告")
    @PutMapping
    public Result updateNotice(@RequestBody @Valid Notice notice){
        return  noticeService.updateNotice(notice);
    }

    @ApiOperation("删除公告")
    @DeleteMapping("/{id}")
    public Result deleteNotice(@PathVariable("id") Long id){
        return  noticeService.deleteNotice(id);
    }

    @ApiOperation("根据id获取公告")
    @GetMapping("/{id}")
    public Result selectNoticeById(@PathVariable("id") Long id){
        return noticeService.selectNoticeById(id);
    }
}
