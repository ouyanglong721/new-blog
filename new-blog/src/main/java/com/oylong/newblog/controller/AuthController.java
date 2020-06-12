package com.oylong.newblog.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.oylong.newblog.annotation.AdminPermission;
import com.oylong.newblog.entity.Result;
import com.oylong.newblog.service.UserService;
import com.oylong.newblog.utils.CaptchaCodeUtil;
import com.oylong.newblog.utils.RedisUtil;
import com.oylong.newblog.utils.ResultUtil;
import com.oylong.newblog.utils.TokenUtil;
import com.oylong.newblog.vo.LoginUserVo;
import com.oylong.newblog.vo.UpdatePasswodVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = "授权相关的接口")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    UserService userService;


    @Resource
    RedisUtil redisUtil;

    @ApiOperation("登入接口")
    @AdminPermission(validate = false)
    @PostMapping("/login")
    public Result login(@RequestBody LoginUserVo loginUser) {
        String captcha = loginUser.getCaptcha();
        String cid = loginUser.getCid();

        String redisCaptcha = (String) redisUtil.get("captcha:" + cid);
        if(redisCaptcha.isEmpty() || !myIsEmpty(redisCaptcha, captcha)){
            redisUtil.del("captcha:" + cid);
            return ResultUtil.buildUnSuccessResult("验证码错误");
        }
        redisUtil.del("captcha:" + cid);
        return userService.login(loginUser.getUsername(), loginUser.getPassword());
    }


    @ApiOperation("注销")
    @DeleteMapping("/logout")
    public Result logout(@RequestHeader("token")String token){
        return  userService.logout(token);
    }

    @ApiOperation("获取验证码")
    @AdminPermission(validate = false)
    @GetMapping("/captcha")
    public Result getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //生成4位随机验证码
        String code= new CaptchaCodeUtil().randomStr(4);
        //指定验证码长宽
        CaptchaCodeUtil vCode = new CaptchaCodeUtil(116,36,4,10,code);


        //生成图片,并显示(html调用的显示)
        String cid = IdUtil.simpleUUID();
        redisUtil.set("captcha:"+cid, code, 90 + RandomUtil.randomInt(-30,0));

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(vCode.getBuffImg(), "png", stream);
        String base64 = Base64.encode(stream.toByteArray());

        Result result = ResultUtil.buildSuccessResult("获取验证码成功");
        Map res = new HashMap<String, Object>();
        res.put("image", base64);
        res.put("cid", cid);
        result.setData(res);
        return result;
    }


    @AdminPermission(validate = false)
    @ApiOperation("找回密码,发送邮箱")
    @GetMapping("/password")
    public Result forgetPassword(@RequestParam("username") String username) {
        //发送邮箱验证码
        return userService.sendFindPassWordEmail(username);
    }

    @AdminPermission(validate = false)
    @ApiOperation("找回密码,验证验证码")
    @PutMapping("/password")
    public Result updatePassword(@RequestBody UpdatePasswodVo vo){
        log.info("找回密码");
        return userService.findPassword(vo.getUsername(), vo.getPassword(),vo.getCaptcha());
    }

    @AdminPermission(validate = false)
    @ApiOperation("验证token是否有效")
    @GetMapping("/token")
    public Result isTokenAvalable(@RequestParam("token") String token){
        if(TokenUtil.isTokenAvalible(token)) {
            return ResultUtil.buildSuccessResult();
        }
        return ResultUtil.buildUnSuccessResult();
    }

    private  boolean myIsEmpty(String str1, String str2){
        if(str1 == null || str2 == null){
            return false;
        }
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        return StringUtils.equals(str1, str2);
    }


}
