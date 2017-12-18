package com.github.timo_reymann.springboot.ebean.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Timo Reymann
 * @since 14.12.17
 */
@Configuration
@ConfigurationProperties("ebean")
@Data
public class EbeanConfig {


    /**
     * Native ilike expression for supported dbms
     */
    private boolean setExpressionNativeIlike = true;

    /**
     * Enable auto tune
     */
    private boolean enableAutoTune = true;

    /**
     * Generate tables scripts
     */
    private boolean ddlGenerate = false;

    /**
     * Run table scripts
     */
    private boolean ddlRun = false;
}
