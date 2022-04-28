package thread.synchronization.ex;

import java.util.Vector;

public class Producer extends Thread{
    static final int MAXQUEUE = 5;

    private Vector messages = new Vector();

    @Override
    public void run(){
       try{
           while(true){
               putMessage();
           }
       }catch (InterruptedException e){

       }

    }

    private synchronized void putMessage() throws InterruptedException {
        while(messages.size() == MAXQUEUE){
            wait();
        }
        messages.addElement(new java.util.Date().toString());
        System.out.println("put message");
        notify();
        //sau khi event put message dc xảy ra,hàm notify() dc gọi để đánh thức
        //kích hoạt lại thread getMessage tiếp tục hoạt động
    }

    //dc gọi bởi consumer
    public synchronized String getMessage() throws InterruptedException {
        notify();
        while(messages.size() == 0){
            wait();
            //gọi hàm wait() để đồng bộ hóa, thread hiện tại sẽ tạm dừng
            //rơi vào trạng thái nằm chờ đến khi method notify dc gọi
        }

        String message = (String) messages.firstElement();
        messages.removeElement(message);
        return message;
    }
}
class Consumer extends Thread {
    Producer producer;
    Consumer(Producer p){
        producer = p;
    }

    @Override
    public void run(){
        try{
            while(true){
                String message = producer.getMessage();
                System.out.println("got message: " + message);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        producer.start();
        new Consumer(producer).start();
    }
}
