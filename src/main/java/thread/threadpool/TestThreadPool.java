package thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
    public static void main(String[] args) {
        int corePoolSize = 5;
        //corePoolSize: số lượng thread tối tiểu trong ThreadPool, số lượng thread có thể = 0
        //khi nhiệm vụ dc thêm vào thì thread dc dc tạo ra,nếu số lượng thread ít hơn corePoolSize
        //thì thread mới dc tạo ra đến khi số Thread = giả trị của corePoolSize

        int maximumPoolSize = 10;
        //maximumPoolSize: số lượng tối đa các thread có trong threadpool

        long keepAliveTime = 500;
        //khi số thread lớn hơn corePoolSize thì keepAliveTimelà thời gian tối đa mà 1 thread "nhàn rồi"
        //chờ nhiệm vụ.khi hết thời gian chờ mà thread chưa có nv thì nó sẽ bị hủy

        TimeUnit unit = TimeUnit.SECONDS;
        //Unit: đơn vị thời gian của keepAliveTime,

        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        //workQueue là hàng đợi dùng để chứa nhiệm vụ  mà các thread sẽ lấy chúng ra và thực hiện
        //lầm lượt

        RejectedExecutionHandler handler= new ThreadPoolExecutor.CallerRunsPolicy();
        //handler: hành động khi một request(task) bị từ chối

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        for(int i = 0; i < 10; i++){
            threadPoolExecutor.execute(new Run(i));
        }
    }
}
