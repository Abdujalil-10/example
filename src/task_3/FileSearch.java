package task_3;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FileSearch {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String folderPath = "C:/New folder"; // Folder manzilini kiriting
        String fileName = "imtixon.txt"; // Fayl nomini kiriting

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<File> searchTask = () -> {
            Path folder = Paths.get(folderPath);
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "imtixon.txt")) {
                for (Path entry : stream) {
                    if (entry.getFileName().toString().equals(fileName)) {
                        return entry.toFile();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };

        Callable<Void> readTask = () -> {
            File file = searchTask.call();
            if (file != null && file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    Pattern pattern = Pattern.compile("\\d{2,}");
                    while ((line = reader.readLine()) != null) {
                        Matcher matcher = pattern.matcher(line);
                        while (matcher.find()) {
                            System.out.println(matcher.group());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Fayl topilmadi.");
            }
            return null;
        };

        Future<Void> readFuture = executor.submit(readTask);
        readFuture.get();

        executor.shutdown();
    }
}
