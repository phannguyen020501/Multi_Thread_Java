package thread.threadpool4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {
    public static final int NUM_OF_THREAD = 5;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREAD);

        //newFixedThreadPool: trong threadpool sẽ dc cố định thread. nếu 1 nhiệm vụ
        //mới dc đưa vào mà các thread dc  đều đang bận rộn thì nhiệm vụ sẽ dc gửi vào
        //blocking queue và sau đó nếu có 1 nhiệm vụ đã thực hiện xong thì nhiệm vụ đang ở trong
        //queue dc push ra và xử lý tiếp
        for(int i = 1; i <=10; i++){
            Runnable worker = new WorkerThread("" + i);
            executorService.execute(worker);
        }

        executorService.shutdown();
        //wait until all threads are finished
        while(!executorService.isTerminated()){

        }

        System.out.println("finished all task");
    }
}
