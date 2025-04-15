package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Person {
    private String name;
    private int height; // Теперь Integer вместо int, чтобы поддерживать null
    private int weight; // Теперь Integer вместо int, чтобы поддерживать null
    private String passportID;
    private LocalDateTime birthday;

    public Person(String name, LocalDateTime birthday, Integer height, Integer weight, String passportID) {
        setName(name);
        setBirthday(birthday);
        setHeight(height); // Теперь может быть null
        setWeight(weight); // Теперь может быть null
        setPassportID(passportID);
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public Integer getHeight() { // Теперь Integer
        return height;
    }

    public Integer getWeight() { // Теперь Integer
        return weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public void setHeight(int height) {
        if (height <= 0) { // Проверка только если height не null
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.height = height;
    }

    public void setWeight(int weight) {
        if (weight <= 0) { // Проверка только если weight не null
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        this.weight = weight;
    }

    public void setPassportID(String passportID) {
        if (passportID == null || passportID.isEmpty()) {
            throw new IllegalArgumentException("Passport ID cannot be null or empty");
        }
        this.passportID = passportID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(height, person.height) &&
               Objects.equals(weight, person.weight) &&
               Objects.equals(name, person.name) &&
               Objects.equals(birthday, person.birthday) &&
               Objects.equals(passportID, person.passportID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height, weight, passportID);
    }

    @Override
    public String toString() {
        return "    name='" + name + '\'' + "\n" +
               "    birthday=" + birthday + "\n" +
               "    height=" + height + "\n" +
               "    weight=" + weight + "\n" +
               "    passportID='" + passportID + '\'';
    }
}
