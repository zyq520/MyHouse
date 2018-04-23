package com.house.utils;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 
 * <p>Title:分页插件</p>
 * <p>Description:分页插件</p>
 * <p>Copyright:Copyright (c) 2016</p>
 * <p>Company:易泓咨询管理公司 </p>
 * <p>Date:2016年10月13日 上午11:00:46</p>
 * <p>Modify:2016年10月13日 上午11:00:46 </p>
 * <p>Bug:</p>
 * 
 * @author xuliting
 * @version 1.1
 */
@Configuration
public class MyBatisConfiguration {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "false");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
