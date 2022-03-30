package thread.threadpool2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        /*
        - single thread executor: trong threadpool chỉ có 1 thread và các task sẽ dc xử lý 1 cách tuần
            tự
        - cached thread pool: trong threadpool có rất nhiều thread, và các task sẽ dc xử lý song song
            các thread cũ sau khi dc xử lý xong sẽ dc sử dụng trong tác vụ mới. mặc định trong
            60s thread k sử dụng thì thread sẽ dc hủy (shutdownw)
         - fixed thread pool: sẽ dc cố định số lượng các thread. nếu 1 task mới dc đưa vào thread đều
            đang bận rộn thì task đó sẽ dc gửi vào blocking queue, ngay sau khi một thread đã thực thi
            xong nhiệm vụ thì nhiệm vụ ở trong queue sẽ dc push ra khỏi queue và thread dó xử lý tiếp
         - scheduled thread pool: tương tự "cached thread pool" nhưng sẽ có khoảng delay giữa các thread
         - single thread schedule pool : tương tự "single thread executor" nhưng sẽ có khoảng delay
            giữa các thread
        * */

        for(int i = 0; i < 10; i++){
            pool.submit(new Run(i));
        }

        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
