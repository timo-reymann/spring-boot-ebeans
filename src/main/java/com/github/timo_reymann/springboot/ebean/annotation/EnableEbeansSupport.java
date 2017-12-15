package com.github.timo_reymann.springboot.ebean.annotation;

import com.github.timo_reymann.springboot.ebean.autoconfiguration.EbeanAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Enable ebeans support for spring boot application
 *
 * @author Timo Reymann
 * @since 14.12.17
 */
@Import(EbeanAutoConfiguration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableEbeansSupport {
}
