package com.newer.jay.demo.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.autoconfigure.DdlApplicationRunner;
import com.baomidou.mybatisplus.extension.ddl.IDdl;

@Configuration
public class RuntimeConfig {
    
    @Bean
    public DdlApplicationRunner ddlApplicationRunner(List<IDdl> ddlList) {
        // 创建自定义的 DdlApplicationRunner 来覆盖默认的
        DdlApplicationRunner ddlApplicationRunner = new DdlApplicationRunner(ddlList);
        return ddlApplicationRunner;
    }
}
