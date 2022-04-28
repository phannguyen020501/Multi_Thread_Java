package thread.sleep;

public class SleepMessages {
    public static void main(String[] args) throws InterruptedException {
        String importantInfo[]= {"a", "b", "c", "d"};

        for(int i = 0; i < importantInfo.length; i++){
            //pause for 4 seconds
            Thread.sleep(4000);

            System.out.println(importantInfo[i]);
        }

    }

}
