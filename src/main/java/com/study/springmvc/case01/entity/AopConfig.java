package com.study.springmvc.case01.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration           //標註此類別為配置檔
public class AopConfig {
  @Bean
  public GetcodesAspect getcodesAspect() {
	  return new GetcodesAspect();
  }
}
