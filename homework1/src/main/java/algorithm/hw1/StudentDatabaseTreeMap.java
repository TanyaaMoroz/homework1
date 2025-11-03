package algorithm.hw1;

import java.io.*;
import java.util.*;

public class StudentDatabaseTreeMap extends StudentDatabaseBase {

    private TreeMap<String, Student> byEmail = new TreeMap<>();

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
        List<Student> res = new ArrayList<>();
        for (Student s : byEmail.values()) {
            if (s.getBirthMonth() == month && s.getBirthDay() == day)
                res.add(s);
        }
        return res;
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
        Map<String, Map<String, Integer>> groupCount = new HashMap<>();

        for (Student s : byEmail.values()) {
            String group = s.getGroup();
            String birthday = s.getBirthMonth() + "-" + s.getBirthDay();
            groupCount.computeIfAbsent(group, g -> new HashMap<>())
                    .merge(birthday, 1, Integer::sum);
        }

        String bestGroup = null;
        int max = 0;
        for (var g : groupCount.entrySet()) {
            int cur = Collections.max(g.getValue().values());
            if (cur > max) {
                max = cur;
                bestGroup = g.getKey();
            }
        }
        return bestGroup;
    }
}
