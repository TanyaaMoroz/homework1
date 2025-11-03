package algorithm.hw1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabase {
    private List<Student> students = new ArrayList<>();

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

    public List<Student> getStudents() {
        return students;
    }
}


