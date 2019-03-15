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
        config.setJdbcUrl("jdbc:mysql://"+System.getenv("MYSQL_HOSTNAME")+":3306/"+System.getenv("MYSQL_DATABASE"));
        config.setUsername(System.getenv("MYSQL_USERNAME"));
        config.setPassword(System.getenv("MYSQL_PASSWORD"));
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("parseInfoCacheFactory", "hello.PerConnectionLRUFactory");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true"); // false fixes problem ?!
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
