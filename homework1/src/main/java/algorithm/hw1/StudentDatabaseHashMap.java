package algorithm.hw1;

import java.io.*;
import java.util.*;

public class StudentDatabaseHashMap extends StudentDatabaseBase {

    private Map<String, Student> byEmail = new HashMap<>();
    private Map<String, List<Student>> byBirthday = new HashMap<>();
    private Map<String, Map<String, Integer>> groupBirthdayCount = new HashMap<>();

    @Override
    public void loadFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] t = line.split(",", -1);
                if (t.length < 9) continue;

                Student s = new Student(
                        t[0].trim(), t[1].trim(), t[2].trim(),
                        Integer.parseInt(t[3].trim()),
                        Integer.parseInt(t[4].trim()),
                        Integer.parseInt(t[5].trim()),
                        t[6].trim(),
                        Float.parseFloat(t[7].trim()),
                        t[8].trim()
                );

                byEmail.put(s.getEmail(), s);

                String birthday = s.getBirthMonth() + "-" + s.getBirthDay();
                byBirthday.computeIfAbsent(birthday, k -> new ArrayList<>()).add(s);

                groupBirthdayCount
                        .computeIfAbsent(s.getGroup(), g -> new HashMap<>())
                        .merge(birthday, 1, Integer::sum);
            }
        } catch (IOException e) {
            System.err.println("Помилка читання CSV: " + e.getMessage());
        }
    }

    @Override
    public List<Student> getStudents() {
        return new ArrayList<>(byEmail.values());
    }

    @Override
    public List<Student> findByBirthday(int month, int day) {
        String key = month + "-" + day;
        return byBirthday.getOrDefault(key, Collections.emptyList());
    }

    @Override
    public boolean changeGroupByEmail(String email, String newGroup) {
        Student s = byEmail.get(email);
        if (s == null) return false;
        s.setGroup(newGroup);
        return true;
    }

    @Override
    public String findGroupWithMostSameBirthday() {
        String bestGroup = null;
        int bestCount = 0;

        for (var entry : groupBirthdayCount.entrySet()) {
            int max = Collections.max(entry.getValue().values());
            if (max > bestCount) {
                bestCount = max;
                bestGroup = entry.getKey();
            }
        }
        return bestGroup;
    }
}
