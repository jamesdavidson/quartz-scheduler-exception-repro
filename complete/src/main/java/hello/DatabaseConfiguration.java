package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public MysqlDataSource dataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL("jdbc:mysql://"
                  +System.getenv("MYSQL_HOSTNAME")+"/"
                  +System.getenv("MYSQL_DATABASE")
                  +"?useSSL=true"
                  +"&cachePrepStmts=true"
                  +"&prepStmtCacheSize=250"
                  +"&prepStmtCacheSqlLimit=2048"
                  +"&useServerPrepStmts=true");
        ds.setUser(System.getenv("MYSQL_USERNAME"));
        ds.setPassword(System.getenv("MYSQL_PASSWORD"));
        return ds;
    }
}
