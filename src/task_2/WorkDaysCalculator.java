package task_2;

import java.util.concurrent.*;

public class WorkDaysCalculator {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> leapYearTask = () -> {
            int leapYears = 0;
            for (int year = 1990; year <= 2023; year++) {
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    leapYears++;
                }
            }
            return leapYears;
        };

        Callable<Integer> workDaysTask = () -> {
            int totalDays = 0;
            for (int year = 1990; year <= 2023; year++) {
                totalDays += (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 366 : 365;
            }
            int workDays = (totalDays / 7) * 5 + (totalDays % 7);
            return workDays;
        };

        Future<Integer> leapYearFuture = executor.submit(leapYearTask);
        Future<Integer> workDaysFuture = executor.submit(workDaysTask);

        int leapYears = leapYearFuture.get();
        int workDays = workDaysFuture.get();

        executor.shutdown();

        System.out.println("Kabisa yillari soni: " + leapYears);
        System.out.println("Umumiy ish kunlari soni: " + workDays);
    }
}
