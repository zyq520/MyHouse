package com.house.controller;

import com.house.model.Article;
import com.house.service.ArticleService;
import com.house.utils.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhuangyq
 * @create 2018-04-23 上午 9:47
 **/
@Controller
public class BaseController {
    public static final Logger LOG = Logger.getLogger(BaseController.class);
    @Autowired
    private ArticleService articleService;
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        Article a= articleService.getById(1L);
        return JsonUtil.toString(a);
    }
}
