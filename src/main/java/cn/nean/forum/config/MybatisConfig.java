package cn.nean.forum.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.nean.forum.mapper")
public class MybatisConfig {
}
