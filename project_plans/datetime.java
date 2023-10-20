package project_plans;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class datetime {

    public static void main(String[] args) {
     
        Date scheduledDate = new Date(System.currentTimeMillis() + 20000); // 5 seconds from now

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        long delay = scheduledDate.getTime() - System.currentTimeMillis();

        executor.schedule(new EventTask(), delay, TimeUnit.MILLISECONDS);
    }

    static class EventTask implements Runnable {
        @Override
        public void run() {
            System.out.println("event triggered");
        }
    }
}

