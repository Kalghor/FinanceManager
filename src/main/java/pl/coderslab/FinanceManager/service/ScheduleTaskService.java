package pl.coderslab.FinanceManager.service;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import pl.coderslab.FinanceManager.domain.convarter.CronExpressionConverter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleTaskService {

    // Task Scheduler
    TaskScheduler scheduler;

    // A map for keeping scheduled tasks
    Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public ScheduleTaskService(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }


    public void addTaskToScheduler(int id, Runnable task, LocalDate date, boolean monthly, LocalDate stopDate) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger(CronExpressionConverter.LocalDateTimeToCron(date, monthly, stopDate), TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(id, scheduledTask);
    }

    public void addTaskToScheduler(int id, Runnable task) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger("0 * * * * *", TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(id, scheduledTask);
    }

    // Remove scheduled task
    public void removeTaskFromScheduler(int id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(id, null);
        }
    }

    // A context refresh event listener
    @EventListener({ ContextRefreshedEvent.class })
    void contextRefreshedEvent() {
        // Get all tasks from DB and reschedule them in case of context restarted
    }
}
