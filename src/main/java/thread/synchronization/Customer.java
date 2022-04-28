package thread.synchronization;

public class Customer {
    private int balance = 1000;

    public Customer(){
        System.out.println("Tài khoản của bạn là " + balance);
    }


    //synchronized: khả năng kiểm soát truy cập của nhiều luòngo đến bất kì tài nguyên chia sẻ
    // (shared resource). có nhiều luồng muốn truy cập cungf 1 biến tại 1 thời điểm: luồng muốn
    //học hay có luồng muốn ghi: khiến dữ liệu bị sai lệch
    //java synchronized: khi muốn 1 luồng dc truy cập vào tài nguyên đó
    private synchronized void withdraw(int amount){
        System.out.println("giao dịch rút tiền đang dc thực hiện "+ amount);
        if(balance < amount){
            System.out.println("không thể rút ");
            try {
                wait();
                //wait(): cho phép thread loại ra khỏi danh sách thread đang hoạt động cho đến khi
                // 1 thread gọi hàm notify()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        balance -= amount;
        System.out.println("rút tiền thành công. tài khoản còn lại là " + balance);
    }

    private synchronized void deposit(int amount){
        System.out.println("giao dịch nạp tiền "+ amount+ "...");
        balance += amount;
        System.out.println("nạp tiền thành công. tài khoản hiện tại: "+ balance);
        notify();
        //notify(): thông báo và kích hoạt trờ lại thread đầu tiền gọi wait() trên cùng
        //1 đối tượng
    }

    public static void main(String[] args) {
        Customer c= new Customer();
        Thread t1 = new Thread(){
            public void run(){
                c.withdraw(2000);
            }
        };

        t1.start();

        Thread t2 = new Thread(){
            public void run(){
                c.deposit(3000);
            }
        };

        t2.start();
    }
}
