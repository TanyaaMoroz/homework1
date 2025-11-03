package algorithm.hw1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Обери реалізацію
        StudentDatabaseBase db = new StudentDatabaseArrayList();
        // StudentDatabaseBase db = new StudentDatabaseHashMap();
        // StudentDatabaseBase db = new StudentDatabaseTreeMap();

        String filePath = "/Users/admin/Desktop/algorithms/homework1/homework1/src/main/resources/students.csv";

        System.out.println("cwd: " + System.getProperty("user.dir")); // де саме створяться файли

        // Задача I: завантаження + памʼять
        long memBefore = usedMemory();
        long t0 = System.currentTimeMillis();
        db.loadFromCSV(filePath);
        long loadMs = System.currentTimeMillis() - t0;
        long memAfter = usedMemory();
        double memUsedMb = (memAfter - memBefore) / (1024.0 * 1024.0);
        System.out.printf("⏱ Час завантаження бази: %d ms%n", loadMs);
        System.out.printf("💾 Використано памʼяті: %.2f MB%n", memUsedMb);
        System.out.println("Завантажено студентів: " + db.getStudents().size());

        // Задача II: експеримент
        ExperimentRunner.run(db, 10);

        // Задача III: сортування за rating
        List<Student> all = db.getStudents();

        // a) стандартне (копія списку!)
        List<Student> sortedStandard = SortedStudent.sortByRatingStandard(new ArrayList<>(all));
        SortedStudent.saveToCSV(sortedStandard, "sorted_standard.csv");

        // b) власний QuickSort (теж копія!)
        List<Student> sortedQuick = SortedStudent.sortByRatingQuickSort(new ArrayList<>(all));
        SortedStudent.saveToCSV(sortedQuick, "sorted_quicksort.csv");
    }

    private static long usedMemory() {
        Runtime rt = Runtime.getRuntime();
        rt.gc();
        return rt.totalMemory() - rt.freeMemory();
    }
}
