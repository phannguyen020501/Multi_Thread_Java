package thread.threadpool4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //single: threadpool chỉ có 1 thread và các task sẽ dc xử lý 1 cách tuần tự

        for(int i = 1; i<=10; i++){
            Runnable worker = new WorkerThread(""+ i);
            executorService.execute(worker);
        }

        executorService.shutdown();

        //wait until all threads are finish
        while(!executorService.isTerminated()){
        }

        System.out.println("finished all threads");
    }
}
