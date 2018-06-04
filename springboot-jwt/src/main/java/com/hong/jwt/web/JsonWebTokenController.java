package com.hong.jwt.web;

import com.alibaba.fastjson.JSONObject;
import com.hong.jwt.domain.AccessToken;
import com.hong.jwt.domain.LoginInfo;
import com.hong.jwt.neum.UserInfoEnum;
import com.hong.jwt.utils.JwtHelper;
import com.hong.jwt.utils.MyMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hong on 2017/6/11.
 */
@RestController
@RequestMapping("/oauth")
public class JsonWebTokenController {

    @Autowired
    private JwtHelper jwtHelper;

    @RequestMapping(value = "/token")
    public JSONObject getAccessToken(HttpServletResponse response, LoginInfo loginInfo, Device device) {
        JSONObject resultMsg = new JSONObject();
        try {
            if (loginInfo.getClientId() == null) {
                resultMsg.put("errcode", 500);
                resultMsg.put("msg", "not is ClientId!");
                return resultMsg;
            }

            //验证码校验在后面章节添加

            //验证用户名密码
            //1.验证用户是否存在.
            if (!UserInfoEnum.TEST.getUsername().equals(loginInfo.getUsername()) &&
                    !UserInfoEnum.ADMIN.getUsername().equals(loginInfo.getUsername()) &&
                    !UserInfoEnum.ZHANGSAN.getUsername().equals(loginInfo.getUsername())) {

                resultMsg.put("result_code", 500);
                resultMsg.put("msg", "no user!");
                return resultMsg;
            }
            //2.用户存在,验证密码.
            else {
                // 获取用户登录md5加密后的密码.
                String loginMD5Password = MyMD5Util.MD5(loginInfo.getPassword());

                // 获取匹配的密码.
                String password = null;
                switch (loginInfo.getUsername()) {
                    case "admin":
                        password = UserInfoEnum.ADMIN.getPassword();
                        break;
                    case "test":
                        password = UserInfoEnum.TEST.getPassword();
                        break;
                    case "zhangsan":
                        password = UserInfoEnum.ZHANGSAN.getPassword();
                        break;
                }
                String userMD5Password = MyMD5Util.MD5(password);
                if (!loginMD5Password.equals(userMD5Password)) {
                    resultMsg.put("result_code", 500);
                    resultMsg.put("msg", "password error!");
                    return resultMsg;
                }
            }

            //拼装accessToken
            String accessToken = jwtHelper.createJWT(loginInfo.getUsername(), device);

            //返回accessToken
            AccessToken accessTokenEntity = new AccessToken();
            accessTokenEntity.setAccessToken(accessToken);
            accessTokenEntity.setExpiresIn(jwtHelper.getExpiration());
            accessTokenEntity.setTokenType("bearer");

            resultMsg.put("result_code", 200);
            resultMsg.put("msg", "success");
            resultMsg.put("token", accessTokenEntity);

            //设置cookie
            Cookie cookie =new Cookie("token",accessToken);
            cookie.setHttpOnly(true);
            //注：Domain为设置Cookie的有效域，Path限制有效路径
            //1、必须是1-9、a-z、A-Z、. 、- （注意是-不是_）这几个字符组成
            //2、必须是数字或字母开头
            //3、必须是数字或字母结尾
            //cookie.setDomain("jwt");
            response.addCookie(cookie);
            return resultMsg;
        } catch (Exception ex) {
            System.out.println(ex);
            resultMsg.put("result_code", 500);
            resultMsg.put("msg", "other error!");
            return resultMsg;
        }
    }
}
