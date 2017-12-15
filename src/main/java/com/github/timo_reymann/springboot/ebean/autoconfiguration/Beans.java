package com.github.timo_reymann.springboot.ebean.autoconfiguration;

import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.CurrentUserProvider;
import io.ebean.config.ServerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Collection of common beans
 *
 * @author Timo Reymann
 * @since 14.12.17
 */
@Configuration
public class Beans {
    @Bean
    @ConditionalOnMissingBean(io.ebean.config.CurrentUserProvider.class)
    public CurrentUserProvider currentUserProvider() {
        return () -> System.getProperty("user.name");
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Primary
    public EbeanServer ebeanServer(ServerConfig defaultEbeanServerConfig) {
        return EbeanServerFactory.create(defaultEbeanServerConfig);
    }
}
