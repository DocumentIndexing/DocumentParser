package com.g4pas.index.file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author James Fawke
 */
@ConfigurationProperties(prefix = "file")
@Profile("file")
@Component
public class FileConfiguration {
}
