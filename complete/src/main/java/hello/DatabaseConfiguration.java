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
        config.setJdbcUrl("jdbc:mysql://"
                  +System.getenv("MYSQL_HOSTNAME")+"/"
                  +System.getenv("MYSQL_DATABASE")
                  +"?useSSL=true"
                  +"&cachePrepStmts=true"
                  +"&prepStmtCacheSize=250"
                  +"&prepStmtCacheSqlLimit=2048"
                  +"&useServerPrepStmts=true");
        config.setUsername(System.getenv("MYSQL_USERNAME"));
        config.setPassword(System.getenv("MYSQL_PASSWORD"));
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
