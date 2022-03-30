package thread.threadpool;

public class Run implements Runnable{
    int id;

    public Run(int id){
        this.id = id;
    }


    @Override
    public void run() {
        System.out.println("tiến trình đang dc thực thi " + id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tiến trình "+ id+ " đã được thực thi");
    }
}
