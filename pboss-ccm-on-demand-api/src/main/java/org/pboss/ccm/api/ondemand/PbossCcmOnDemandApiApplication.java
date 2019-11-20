package org.pboss.ccm.api.ondemand;

import org.pboss.ccm.api.ondemand.filters.RequestPostFilter;
import org.pboss.ccm.api.ondemand.filters.RequestPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class PbossCcmOnDemandApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(PbossCcmOnDemandApiApplication.class, args);
  }

  @Bean
  public RequestPreFilter requestPreFilter() {
    return new RequestPreFilter();
  }

  @Bean
  public RequestPostFilter requestPostFilter() {
    return new RequestPostFilter();
  }

}
