package hello;

import org.quartz.SchedulerException;
import org.quartz.spi.InstanceIdGenerator;
import org.springframework.boot.system.ApplicationPid;

import java.util.UUID;

public class PidBasedInstanceIdGenerator implements InstanceIdGenerator {

    @Override
    public String generateInstanceId() throws SchedulerException {
        ApplicationPid pid = new ApplicationPid();
        return UUID.nameUUIDFromBytes(pid.toString().getBytes()).toString();
    }
}
