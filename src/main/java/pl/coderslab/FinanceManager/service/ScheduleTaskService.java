package pl.coderslab.FinanceManager.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import pl.coderslab.FinanceManager.domain.convarter.CronExpressionConverter;
import pl.coderslab.FinanceManager.domain.model.Category;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScheduleTaskService {

    // Task Scheduler
    TaskScheduler scheduler;
    private final CategoryService categoryService;

    // A map for keeping scheduled tasks
    Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public ScheduleTaskService(TaskScheduler scheduler, CategoryService categoryService) {
        this.scheduler = scheduler;
        this.categoryService = categoryService;
    }


    public void addTaskToScheduler(int id, Runnable task, LocalDate date, boolean monthly, LocalDate stopDate) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger(CronExpressionConverter.LocalDateTimeToCron(date, monthly, stopDate), TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(id, scheduledTask);
    }

    public void addTaskToScheduler(int id, Runnable task) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger("0 * * * * *", TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(id, scheduledTask);
    }

    public void removeTasksFromScheduler(List<Category> list){
        String ids = list.stream().map(Category::getId).map(Object::toString).sorted().collect(Collectors.joining(", "));
        for (Category c : list){
            removeTaskFromScheduler(c.getId().intValue());
        }
//        log.debug("Ids before deleting tasks: " + ids);
        System.out.println("Ids before deleting tasks: " + ids);
        showScheduledJobs("Ids after removing tasks: ");
    }

    // Remove scheduled task
    public void removeTaskFromScheduler(int id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        showScheduledJobs("Ids before deleting tasks: ");
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
//            jobsMap.put(id, null);
            jobsMap.remove(id);
        }
        showScheduledJobs("Ids after removing tasks: ");
    }

    // A context refresh event listener
    @EventListener({ ContextRefreshedEvent.class })
    void contextRefreshedEvent() {
        // Get all tasks from DB and reschedule them in case of context restarted
    }

    public void showScheduledJobs(String message){
        String ids = jobsMap.keySet().stream().map(Object::toString).sorted().collect(Collectors.joining(", "));
//        log.debug(message + ids);
        System.out.println(message + ids);
    }
}
