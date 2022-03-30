package thread.yield;

import java.util.Date;

//yield(): sử dụng khi thấy thread đó đang rảnh rỗi, ko làm việc gì quan trọng, nên gợi ý
//hệ điều hành tạm thời nhường ưu tiên cho luồng khác
//vi dụ: có 2 luồng, mỗi luồng in ra 1 dòng văn bản 100k lần. 1 luồng set độ ưu tiên cao nhất
//       1 luồng set độ ưu tiên ít nhất
public class YieldThreadExample {
    private static Date importantEndTime;
    private static Date unImportantEndTime;

    public static void main(String[] args) {
        importantEndTime = new Date();
        unImportantEndTime = new Date();

        System.out.println("create thread 1");
        Thread importantThread = new ImportantThread();

        //set độ ưu tiên cao nhất cho thread này
        importantThread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("create thread 2");

        Thread unImportantThread = new UnImportantThread();

        //set độ ưu tiên thấp nhất
        unImportantThread.setPriority(Thread.MIN_PRIORITY);

        //start threads
        unImportantThread.start();
        importantThread.start();

    }

    static class ImportantThread extends Thread{
        @Override
        public void run(){
            for(int i =0; i < 100000; i++){
                System.out.println("\n Important work "+ i);
                //thông báo cho hdh
                //thread nhường quyền ưu tiên cho các luồng khác
                Thread.yield();
            }
            //thời điểm kết thúc của thread này
            importantEndTime= new Date();
            printTime();
        }
    }

    static class UnImportantThread extends Thread{
        @Override
        public void run(){
            for(int i =0; i< 100000; i++){
                System.out.println("\n --UnImportant work " + i);
            }
            //thời điểm kết thúc thread này
            unImportantEndTime = new Date();
            printTime();
        }
    }
    private static void printTime(){
        //time: ms
        long interval = unImportantEndTime.getTime() - importantEndTime.getTime();
        System.out.println("UnImportant Thread - important thread = " + interval + "ms");
    }
}
