package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public HikariDataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://quartz.cluster-c4b3f88cfb54.ap-southeast-1.rds.amazonaws.com:3306/quartz");
        config.setUsername("quartz");
        config.setPassword("quartz");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true"); // false fixes problem ?!
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
