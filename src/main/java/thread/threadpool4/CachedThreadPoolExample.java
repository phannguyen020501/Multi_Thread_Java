package thread.threadpool4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        //newCachedThreadPool: threadPool có nhiều thread và các nhiệm vụ sẽ dc xử lý 1 cách song
        // song. các thread cũ sau khi xử lý xong sẽ dc sử dụng lại cho nhiệm vụ mới. mạc định
        //không dc sử dụng trong 60s thì thread đó sẽ bị tắt

        for(int i = 1; i <= 10; i++){
            Runnable worker = new WorkerThread("" + i);
            executorService.execute(worker);
            Thread.sleep(400);
        }

        executorService.shutdown();

        while(!executorService.isTerminated()){

        }

        System.out.println("finished all threads");
    }

}
