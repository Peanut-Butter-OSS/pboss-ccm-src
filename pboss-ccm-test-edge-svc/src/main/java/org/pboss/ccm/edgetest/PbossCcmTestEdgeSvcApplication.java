package org.pboss.ccm.edgetest;

import org.pboss.ccm.edgetest.filters.pre.SimpleFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class PbossCcmTestEdgeSvcApplication {

  public static void main(String[] args) {
    SpringApplication.run(PbossCcmTestEdgeSvcApplication.class, args);
  }

  @Bean
  public SimpleFilter simpleFilter() {
    return new SimpleFilter();
  }

}
