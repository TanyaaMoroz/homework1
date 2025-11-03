package algorithm.hw1;

import java.io.*;
import java.util.*;

public class StudentDatabaseArrayList extends StudentDatabaseBase {

    private List<Student> students = new ArrayList<>();

    @Override
    public void loadFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // пропускаємо перший рядок

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 9) continue;

                Student s = new Student();
                s.setName(data[0].trim());
                s.setSurname(data[1].trim());
                s.setEmail(data[2].trim());
                s.setBirthYear(Integer.parseInt(data[3].trim()));
                s.setBirthMonth(Integer.parseInt(data[4].trim()));
                s.setBirthDay(Integer.parseInt(data[5].trim()));
                s.setGroup(data[6].trim());
                s.setRating(Float.parseFloat(data[7].trim()));
                s.setPhoneNumber(data[8].trim());

                students.add(s);
            }

            System.out.println("Завантажено студентів: " + students.size());

        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }
    }

    @Override
    public List<Student> getStudents() {
        return students;
    }

    @Override
    public List<Student> findByBirthday(int month, int day) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getBirthMonth() == month && s.getBirthDay() == day) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public boolean changeGroupByEmail(String email, String newGroup) {
        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                s.setGroup(newGroup);
                return true;
            }
        }
        return false;
    }

    @Override
    public String findGroupWithMostSameBirthday() {
        Map<String, Map<String, Integer>> groupBirthdayCount = new HashMap<>();

        for (Student s : students) {
            String group = s.getGroup();
            String birthday = s.getBirthMonth() + "-" + s.getBirthDay();

            groupBirthdayCount.putIfAbsent(group, new HashMap<>());
            Map<String, Integer> birthdayMap = groupBirthdayCount.get(group);
            birthdayMap.put(birthday, birthdayMap.getOrDefault(birthday, 0) + 1);
        }

        String bestGroup = null;
        int maxCount = 0;

        for (var entry : groupBirthdayCount.entrySet()) {
            int groupMax = Collections.max(entry.getValue().values());
            if (groupMax > maxCount) {
                maxCount = groupMax;
                bestGroup = entry.getKey();
            }
        }

        return bestGroup;
    }
}
