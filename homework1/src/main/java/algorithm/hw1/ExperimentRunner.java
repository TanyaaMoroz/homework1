package algorithm.hw1;

import java.util.*;

public class ExperimentRunner {

    public static void run(StudentDatabaseBase db, int seconds) {
        List<Student> students = db.getStudents();
        if (students.isEmpty()) {
            System.out.println("База порожня!");
            return;
        }

        // беремо реальні email, дати і групи
        List<String> emails = new ArrayList<>();
        List<int[]> birthdays = new ArrayList<>();
        Set<String> groups = new HashSet<>();

        for (Student s : students) {
            emails.add(s.getEmail());
            birthdays.add(new int[]{s.getBirthMonth(), s.getBirthDay()});
            groups.add(s.getGroup());
        }

        List<String> groupList = new ArrayList<>(groups);
        Random random = new Random();
        long end = System.currentTimeMillis() + seconds * 1000;

        int total = 0, op1 = 0, op2 = 0, op3 = 0;

        while (System.currentTimeMillis() < end) {
            int r = random.nextInt(250); // 100 + 100 + 50

            if (r < 100) { // 1. пошук за днем народження
                int[] b = birthdays.get(random.nextInt(birthdays.size()));
                db.findByBirthday(b[0], b[1]);
                op1++;
            } else if (r < 200) { // 2. зміна групи
                String email = emails.get(random.nextInt(emails.size()));
                String newGroup = groupList.get(random.nextInt(groupList.size()));
                db.changeGroupByEmail(email, newGroup);
                op2++;
            } else { // 3. група з найбільше однакових ДН
                db.findGroupWithMostSameBirthday();
                op3++;
            }
            total++;
        }

        System.out.println("\n=== Результати експерименту ===");
        System.out.println("Тривалість: " + seconds + " секунд");
        System.out.println("Операція 1 (findByBirthday): " + op1);
        System.out.println("Операція 2 (changeGroupByEmail): " + op2);
        System.out.println("Операція 3 (findGroupWithMostSameBirthday): " + op3);
        System.out.println("Усього виконано: " + total);
        System.out.println("===============================");
    }
}
