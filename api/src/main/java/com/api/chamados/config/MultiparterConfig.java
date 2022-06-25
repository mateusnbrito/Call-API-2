package com.api.chamados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.support.MultipartFilter;

public class MultiparterConfig {
  @Bean
  public MultipartFilter multipartFilter(){
    MultipartFilter multipartFilter = new MultipartFilter();
    multipartFilter.setMultipartResolverBeanName("multipartResolver");
    return multipartFilter;
  }
}
