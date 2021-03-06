package com.g4pas.index;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.http.config.EnableIntegrationGraphController;

//import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec;

/**
 * Application class that initiates the loading of the Spring Application
 */

@SpringBootApplication
@EnableIntegration
@EnableIntegrationGraphController(path = "/integration")
public class Application {
    static ConfigurableApplicationContext run;


    public static void main(String... args) {
        run = new SpringApplicationBuilder(Application.class).bannerMode(Banner.Mode.OFF)
                                                             .run(args);
        run.registerShutdownHook();
    }
}
