package com.github.timo_reymann.springboot.ebean.autoconfiguration;

import com.github.timo_reymann.springboot.ebean.config.EbeanConfig;
import io.ebean.config.AutoTuneConfig;
import io.ebean.config.AutoTuneMode;
import io.ebean.config.CurrentUserProvider;
import io.ebean.config.ServerConfig;
import io.ebean.spring.txn.SpringJdbcTransactionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Timo Reymann
 * @since 14.12.17
 */
@EnableTransactionManagement
@Slf4j
@ComponentScan("com.github.timo_reymann.springboot.ebean")
public class EbeanAutoConfiguration {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Value("${spring.datasource.name:db}")
    private String name;

    @Autowired
    private EbeanConfig ebeanConfig;

    @Autowired
    private CurrentUserProvider currentUserProvider;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Bean
    @Primary
    public ServerConfig defaultEbeanServerConfig() {
        ServerConfig config = new ServerConfig();

        logSetting("name", name);
        config.setName(name);
        config.setDataSource(dataSource);

        config.setExternalTransactionManager(new SpringJdbcTransactionManager());

        config.setDefaultServer(true);
        config.setRegister(true);

        logSetting("expressionsNativeILike", ebeanConfig.isSetExpressionNativeIlike());
        config.setExpressionNativeIlike(ebeanConfig.isSetExpressionNativeIlike());

        logSetting("currentUserProvider", currentUserProvider.getClass().getName());
        config.setCurrentUserProvider(currentUserProvider);

        logSetting("ddlGenerate", ebeanConfig.isDdlGenerate());
        config.setDdlGenerate(ebeanConfig.isDdlGenerate());

        logSetting("ddlRun", ebeanConfig.isDdlRun());
        config.setDdlRun(ebeanConfig.isDdlRun());

        List<String> packages = new ArrayList<>();
        packages.addAll(Arrays.asList(ebeanConfig.getEntityPackages()));
        packages.addAll(Arrays.asList(ebeanConfig.getQueryBeanPackages()));

        if (packages.size() > 0) {
            logSetting("packages", String.join(", ", packages));
            config.setPackages(packages);
        }

        if (ebeanConfig.isEnableAutoTune()) {
            logSetting("auto tune", ebeanConfig.isEnableAutoTune());
            AutoTuneConfig autoTuneConfig = new AutoTuneConfig();
            autoTuneConfig.setMode(AutoTuneMode.DEFAULT_ON);
            autoTuneConfig.setProfiling(true);
            config.setAutoTuneConfig(autoTuneConfig);
        }

        config.setDbUuid(ServerConfig.DbUuid.AUTO_VARCHAR);

        return config;
    }

    private void logSetting(String key, Object value) {
        log.info("Set " + key + " to " + value);
    }
}
