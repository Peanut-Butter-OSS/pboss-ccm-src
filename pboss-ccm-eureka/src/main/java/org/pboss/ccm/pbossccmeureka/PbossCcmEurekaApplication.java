package org.pboss.ccm.pbossccmeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PbossCcmEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PbossCcmEurekaApplication.class, args);
	}

}
