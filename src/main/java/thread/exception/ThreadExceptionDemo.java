package thread.exception;

import java.util.Random;

public class ThreadExceptionDemo {
    public static class RunnableTest implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread running..");
            while(true){
                Random random = new Random();
                //1 số ngẫu nhiên từ 0-99
                int i = random.nextInt(100);
                System.out.println("next value: " + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i > 70){
                    //mô phỏng 1 ngoại lệ k dc sử dụng trong luồng
                    throw new RuntimeException("have a problem");
                }

            }
        }
    }


    public static void main(String[] args) {
        System.out.println("==> main thread running..");

        Thread thread =new Thread(new RunnableTest());
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("#Thread: " + t);
                System.out.println("#Thread exception message: "+ e.getMessage());
            }
        });
        thread.start();
        System.out.println("==> Main thread end...");
    }
}
