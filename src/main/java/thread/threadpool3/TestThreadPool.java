package thread.threadpool3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
    public static void main(String[] args) {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 500;
        TimeUnit unit = TimeUnit.SECONDS;

        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maxPoolSize, keepAliveTime, unit, workQueue, handler);
        //corePoolSize: số lượng thread tối thiểu trong threadpool, khi số lượng thread
        //              ít hơn corePoolSize: thread mới dc thêm vào khi giá trị thread = corePoolSize
        //maximumPoolSize: số lượng tối đa thread có trong pool
        //keepAliveTime: số thread > corePoolSize: thì keepAliveTime thời gian tối đa mà 1 thread "nhàn rỗi"
        //              chờ nhiệm vụ, khi hết thời gian chưa chờ, thread đó sẽ bị hủy
        //Unit: đơn vị thời gian của keepTimeAlive
        //workQueue: hàng đợi để chứa nhiệm vụ mà thread sẽ lấy chúng
        //handler: hành động khi 1   request (task) bị từ chối
        for(int i = 0; i < 10; i++){
            threadPoolExecutor.execute(new Run(i));
        }
    }
}
