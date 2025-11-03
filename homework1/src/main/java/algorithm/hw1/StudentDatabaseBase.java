package algorithm.hw1;

import java.util.List;

public abstract class StudentDatabaseBase {
    public abstract void loadFromCSV(String filename);
    public abstract List<Student> getStudents();
    public abstract List<Student> findByBirthday(int month, int day);
    public abstract boolean changeGroupByEmail(String email, String newGroup);
    public abstract String findGroupWithMostSameBirthday();
}
