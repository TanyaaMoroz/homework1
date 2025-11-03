package algorithm.hw1;

public class Student {
    private String m_name;
    private String m_surname;
    private String m_email;
    private int m_birth_year;
    private int m_birth_month;
    private int m_birth_day;
    private String m_group;
    private float m_rating;
    private String m_phone_number;

    // Конструктори
    public Student() {}

    public Student(String m_name, String m_surname, String m_email,
                   int m_birth_year, int m_birth_month, int m_birth_day,
                   String m_group, float m_rating, String m_phone_number) {
        this.m_name = m_name;
        this.m_surname = m_surname;
        this.m_email = m_email;
        this.m_birth_year = m_birth_year;
        this.m_birth_month = m_birth_month;
        this.m_birth_day = m_birth_day;
        this.m_group = m_group;
        this.m_rating = m_rating;
        this.m_phone_number = m_phone_number;
    }

    // Геттери та сеттери
    public String getName() { return m_name; }
    public void setName(String name) { this.m_name = name; }

    public String getSurname() { return m_surname; }
    public void setSurname(String surname) { this.m_surname = surname; }

    public String getEmail() { return m_email; }
    public void setEmail(String email) { this.m_email = email; }

    public int getBirthYear() { return m_birth_year; }
    public void setBirthYear(int birthYear) { this.m_birth_year = birthYear; }

    public int getBirthMonth() { return m_birth_month; }
    public void setBirthMonth(int birthMonth) { this.m_birth_month = birthMonth; }

    public int getBirthDay() { return m_birth_day; }
    public void setBirthDay(int birthDay) { this.m_birth_day = birthDay; }

    public String getGroup() { return m_group; }
    public void setGroup(String group) { this.m_group = group; }

    public float getRating() { return m_rating; }
    public void setRating(float rating) { this.m_rating = rating; }

    public String getPhoneNumber() { return m_phone_number; }
    public void setPhoneNumber(String phoneNumber) { this.m_phone_number = phoneNumber; }

    // Порівняння студентів за email (унікальний ідентифікатор)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student s)) return false;
        return m_email != null && m_email.equalsIgnoreCase(s.m_email);
    }

    @Override
    public int hashCode() {
        return m_email == null ? 0 : m_email.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s) %02d-%02d-%02d [%s] rating=%.1f",
                m_surname, m_name, m_email,
                m_birth_year, m_birth_month, m_birth_day,
                m_group, m_rating);
    }
}
