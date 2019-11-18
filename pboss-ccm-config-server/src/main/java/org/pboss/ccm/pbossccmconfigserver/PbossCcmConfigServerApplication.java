package org.pboss.ccm.pbossccmconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class PbossCcmConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PbossCcmConfigServerApplication.class, args);
	}

}
