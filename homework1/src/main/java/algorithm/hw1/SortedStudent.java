package algorithm.hw1;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SortedStudent {

    /** a) Сортування стандартним методом Collections.sort() */
    public static List<Student> sortByRatingStandard(List<Student> students) {
        List<Student> list = new ArrayList<>(students); // копія, щоб не змінювати оригінал

        long start = System.nanoTime();
        list.sort(Comparator.comparingDouble(Student::getRating));
        long end = System.nanoTime();

        double timeMs = (end - start) / 1_000_000.0;
        System.out.printf("⏱ Стандартне сортування виконано за %.3f ms%n", timeMs);

        return list;
    }

    /** b) Власна реалізація QuickSort */
    public static List<Student> sortByRatingQuickSort(List<Student> students) {
        List<Student> list = new ArrayList<>(students); // копія

        long start = System.nanoTime();
        quickSort(list, 0, list.size() - 1);
        long end = System.nanoTime();

        double timeMs = (end - start) / 1_000_000.0;
        System.out.printf("⚡ QuickSort виконано за %.3f ms%n", timeMs);

        return list;
    }

    private static void quickSort(List<Student> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private static int partition(List<Student> list, int low, int high) {
        double pivot = list.get(high).getRating();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getRating() <= pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    /** Збереження у CSV */
    public static void saveToCSV(List<Student> students, String filename) {
        try {
            Path outDir = Paths.get("out");
            Files.createDirectories(outDir);
            Path file = outDir.resolve(filename);

            try (BufferedWriter writer = Files.newBufferedWriter(file)) {
                writer.write("name,surname,email,birth_year,birth_month,birth_day,group,rating,phone\n");
                for (Student s : students) {
                    writer.write(String.format("%s,%s,%s,%d,%d,%d,%s,%.2f,%s%n",
                            s.getName(), s.getSurname(), s.getEmail(),
                            s.getBirthYear(), s.getBirthMonth(), s.getBirthDay(),
                            s.getGroup(), s.getRating(), s.getPhoneNumber()));
                }
            }

            System.out.println("✅ Збережено файл: " + file.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ Помилка запису у файл: " + e.getMessage());
        }
    }
}
