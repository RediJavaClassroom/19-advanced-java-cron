# 17-advanced-java-authorization-2-user-types

## Where are we at?

* Authentication, Authorization
----
## Agenda for today
* Cron Jobs
* deletion of old urls
----
To enable support for scheduling tasks and the
@Scheduled annotation in Spring, we can use the Java enable-style
annotation:

```
@Configuration
@EnableScheduling
public class SpringConfig {
...
}
```

Schedule a Task at Fixed Delay

```
@Scheduled(fixedDelay = 1000)
public void scheduleFixedDelayTask() {
    System.out.println(
      "Fixed delay task - " 
      + System.currentTimeMillis() / 1000);
}
```
The duration between the end of the last
execution and the start of the next execution
is fixed. The task always waits until the
previous one is finished.

This option should be used when
it’s mandatory that the previous execution
is completed before running again.

Schedule a Task at a Fixed Rate

```
@Scheduled(fixedRate = 1000)
public void scheduleFixedRateTask() {
    System.out.println(
      "Fixed rate task - " + System.currentTimeMillis() / 1000);
}
```
This option should be used when each execution of the task is independent.

Note that scheduled tasks don't run in parallel by default. So even if we used fixedRate, the next task won't be invoked until the previous one is done.
If we want to support parallel behavior in scheduled tasks, we need to add the @Async annotation:

```
@EnableAsync
public class ScheduledFixedRateExample {
    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println(
          "Fixed rate task async - " + System.currentTimeMillis() / 1000);
        Thread.sleep(2000);
    }
}
```
Now this asynchronous task will be invoked each second, even if the previous task isn't done.

Schedule a Task With Initial Delay

```
@Scheduled(fixedDelay = 1000, initialDelay = 1000)
public void scheduleFixedRateWithInitialDelayTask() {
 
    long now = System.currentTimeMillis() / 1000;
    System.out.println(
      "Fixed rate task with one second initial delay - " + now);
}
```

Schedule a Task Using Cron Expressions

```
@Scheduled(cron = "0 15 10 15 * ?")
public void scheduleTaskUsingCronExpression() {
 
    long now = System.currentTimeMillis() / 1000;
    System.out.println(
      "schedule tasks using cron jobs - " + now);
}

@Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris")

```
Parameterizing the Schedule
```
@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
```
Run tasks in Parallel

Spring uses a local single-threaded scheduler to run the tasks. 
As a result, even if we have multiple @Scheduled methods, they each need to wait for the thread to 
complete executing a previous task

```
@Bean
public TaskScheduler  taskScheduler() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(5);
    threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
    return threadPoolTaskScheduler;
}

spring.task.scheduling.pool.size=5
```

```
ThreadPoolTaskScheduler
```