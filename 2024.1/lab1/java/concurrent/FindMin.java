public class FindMin {

    public static void main(String[] args) throws InterruptedException {
        int[] array = {12, 3, 19, 8, 7, 25, 5, 17, 6, 9, 14, 2, 1, 20};

        Task task = new Task(array);
        Thread myThread = new Thread(task, "myThread-Task");
        myThread.start();
        myThread.join();

        System.out.println("Mínimo: "+ task.getMin());
    }

    public static class Task implements Runnable {
        private final int[] array;
        private int minValue;

        public Task(int[] array){
            this.array = array;
            this.minValue = Integer.MAX_VALUE;
        }

        @Override
        public void run(){
            for(int num: array){
                if(num < minValue){
                    minValue = num;
                }
            }
        }

        public int getMin(){
            return this.minValue;
        }
    }
}
