package com.pgm.email.task;

import com.pgm.email.service.EmailService;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TasksScheduler {
    
    @Autowired
    EmailService emailService;

    @Scheduled(cron = "0 0 10 * * ?")
    @SchedulerLock(name = "TaskScheduler_scheduledTask", 
      lockAtLeastFor = "PT1M", lockAtMostFor = "PT5M")
    public void scheduledTask() {
        String emailSearch = "carl@gbtec.es";
        emailService.updateListEmailStateToSpam(emailSearch);
    }
    

}
