import java.util.concurrent.*;

public class HelloRunnable implements Runnable{

    public static void main(String[] args){
        HelloRunnable hello = new HelloRunnable();
        hello.run();

        HelloThread thread = new HelloThread();
        thread.start();
    }

    @Override
    public void run(){
        int i = 2;
        while (i > 0) {
            System.out.println("Uo");
            i--;
        }
    }

    static class HelloThread extends Thread {
        @Override
        public void run(){
            int i = 2;
            while (i > 0) {
                System.out.println("Ie");
                i--;
            }
            System.out.println("Iiiiiii");
        }
    }
}