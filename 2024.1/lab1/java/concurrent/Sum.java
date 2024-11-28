import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Semaphore;

public class Sum {

    private static long totalSum = 0;
    private static final Semaphore semaphore = new Semaphore(1);

    public static int sum(FileInputStream fis) throws IOException {
        int sum = 0;
        int byteRead = fis.read();
        while (byteRead != -1) {
        	sum += byteRead;
            byteRead = fis.read();
        }
        return sum;
    }

    public static long sum(String path) throws IOException {

        Path filePath = Paths.get(path);
        if (Files.isRegularFile(filePath)) {
       	    FileInputStream fis = new FileInputStream(filePath.toString());
            return sum(fis);
        } else {
            throw new RuntimeException("Non-regular file: " + path);
        }
    }

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.err.println("Usage: java Sum filepath1 filepath2 filepathN");
            System.exit(1);
        }

        Thread[] threads = new Thread[args.length];

        for(int i=0; i < args.length; i++){
            String path = args[i];

            Runnable task = new Runnable(){
                @Override
                public void run(){
                    try {
                        long fileSum = sum(path); // Soma para o arquivo atual

                        // Região crítica protegida por semáforo
                        semaphore.acquire(); // Bloqueia o acesso
                        try {
                            totalSum += fileSum; // Modifica a soma total
                        } finally {
                            semaphore.release(); // Libera o acesso
                        }

                        System.out.println(path + " : " + fileSum);
                    } catch (IOException e) {
                        System.err.println("Erro ao processar " + path + ": " + e.getMessage());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Thread interrompida: " + e.getMessage());
                    }
                }
            };
        }

        for(Thread thread: threads){
            thread.join();
        }
        System.out.println("Soma total de todos os arquivos: " + totalSum);

    }



}
