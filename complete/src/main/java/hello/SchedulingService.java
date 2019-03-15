package hello;

import javax.inject.Inject;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@Service
public class SchedulingService {

    @Inject
    private DataSource dataSource;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        Properties p = new Properties();
        p.setProperty("org.quartz.scheduler.instanceName", "ClusteredScheduler");
        p.setProperty("org.quartz.scheduler.instanceId", "AUTO");
        p.setProperty("org.quartz.scheduler.instanceIdGenerator.class", "hello.PidBasedInstanceIdGenerator");
        p.setProperty("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        p.setProperty("org.quartz.threadPool.threadCount", "25");
        p.setProperty("org.quartz.jobStore.misfireThreshold", "60000");
        p.setProperty("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        p.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        p.setProperty("org.quartz.jobStore.useProperties", "false");
        p.setProperty("org.quartz.jobStore.tablePrefix", "QRTZ_");
        p.setProperty("org.quartz.jobStore.isClustered", "true");
        p.setProperty("org.quartz.jobStore.clusterCheckinInterval", "20000");
        scheduler.setQuartzProperties(p);
        scheduler.setDataSource(dataSource);
        return scheduler;
    }
}
