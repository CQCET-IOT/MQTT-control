package com.onenet.controller;

import com.onenet.config.Config;
import com.onenet.utils.HttpSendCenter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MqttController {
    Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @RequestMapping("/")
    public String login(Map<String, Object> map){
        map.put("msg","随时随地控制您的设备");
        return "login";
    }
    @RequestMapping("command")
    public String hello(Map<String, Object> map, HttpServletRequest request){
        //提交的数据
        String deviceid = request.getParameter("deviceid");
        String apiKey = request.getParameter("apikey");
        String command = request.getParameter("command");
        logger.info("deviceid = " + deviceid+" apiKey = " + apiKey+ " command = " + command);
        String url = Config.getDomainName() + "/cmds?device_id=" + deviceid;
        JSONObject re = HttpSendCenter.postStr(apiKey, url, command);
        logger.info("return info = " + re.toString());
        map.put("msg", re.toString());
        return "login";
    }

}
