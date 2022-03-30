package thread.join;

public class UsingJoinMethod extends Thread{

    public UsingJoinMethod(String name){
        super(name);
    }

    @Override
    public void run(){
        System.out.println(getName());
        for(int i = 1; i <= 5; i++){
            try{
                System.out.println(i + " ");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        UsingJoinMethod t1 = new UsingJoinMethod("Thread 1");
        UsingJoinMethod t2 = new UsingJoinMethod("Thread 2");
        t1.start();
        try{
            t1.join(3000);
            // join: sau khoảng thòi gian ms khi thread1 dc bắt đầu thì thread2 mới dc chạy
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
