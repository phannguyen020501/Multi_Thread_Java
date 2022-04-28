package thread.threadpool4;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static final int NUM_OF_THREAD = 2;
    public static final int INITIAL_DELAY = 1; //second
    public static final int DELAY = 3; //second

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(NUM_OF_THREAD);
        //newScheduledThreadPool(int corePoolSize): tương tự newCachedThreadPool() nhưng sẽ có
        //thời gian delay giữa các thread

        for(int i = 1; i<=5; i++){
            Runnable worker = new WorkerThread("" + i);
            service.scheduleWithFixedDelay(worker, INITIAL_DELAY,DELAY, TimeUnit.SECONDS);
        }

        //waits for termination for 10 second only
        service.awaitTermination(10, TimeUnit.SECONDS);

        service.shutdown();

        //wait until all threads finished
        while(!service.isTerminated()){

        }

        System.out.println("finished all threads");
    }
}
