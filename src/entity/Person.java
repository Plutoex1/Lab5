package entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс, представляющий персональные данные оператора фильма.
 * <p>
 * Этот класс содержит информацию о человеке, которая включает его имя, рост, вес, паспортный идентификатор и дату рождения.
 * </p>
 */
public class Person {

    /**
     * Имя оператора. Не может быть пустым или null.
     */
    private String name;

    /**
     * Рост оператора. Должен быть больше 0.
     */
    private int height;

    /**
     * Вес оператора. Должен быть больше 0.
     */
    private int weight;

    /**
     * Паспортный идентификатор оператора. Не может быть пустым или null и должен быть уникальным.
     */
    private String passportID;

    /**
     * Дата рождения оператора. Может быть null.
     */
    private LocalDateTime birthday;

    /**
     * Конструктор, создающий объект {@link Person} с указанными значениями.
     * 
     * @param name       Имя оператора. Не может быть null или пустым.
     * @param birthday   Дата рождения оператора. Может быть null.
     * @param height     Рост оператора. Должен быть больше 0.
     * @param weight     Вес оператора. Должен быть больше 0.
     * @param passportID Паспортный идентификатор оператора. Не может быть null или пустым.
     */
    public Person(String name, LocalDateTime birthday, int height, int weight, String passportID) {
        setName(name);
        setBirthday(birthday);
        setHeight(height);
        setWeight(weight);
        setPassportID(passportID);
    }

    // Getters

    /**
     * Получает имя оператора.
     * 
     * @return имя оператора.
     */
    public String getName() {
        return name;
    }

    /**
     * Получает дату рождения оператора.
     * 
     * @return дата рождения оператора.
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * Получает рост оператора.
     * 
     * @return рост оператора.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Получает вес оператора.
     * 
     * @return вес оператора.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Получает паспортный идентификатор оператора.
     * 
     * @return паспортный идентификатор оператора.
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * Устанавливает имя оператора.
     * 
     * @param name имя оператора. Не может быть пустым или null.
     * @throws IllegalArgumentException если имя пустое или null.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Устанавливает дату рождения оператора.
     * 
     * @param birthday дата рождения оператора.
     */
    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    /**
     * Устанавливает рост оператора.
     * 
     * @param height рост оператора. Должен быть больше 0.
     * @throws IllegalArgumentException если рост меньше или равен 0.
     */
    public void setHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
        this.height = height;
    }

    /**
     * Устанавливает вес оператора.
     * 
     * @param weight вес оператора. Должен быть больше 0.
     * @throws IllegalArgumentException если вес меньше или равен 0.
     */
    public void setWeight(int weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        this.weight = weight;
    }

    /**
     * Устанавливает паспортный идентификатор оператора.
     * 
     * @param passportID паспортный идентификатор оператора. Не может быть пустым или null.
     * @throws IllegalArgumentException если паспортный идентификатор пустой или null.
     */
    public void setPassportID(String passportID) {
        if (passportID == null || passportID.isEmpty()) {
            throw new IllegalArgumentException("Passport ID cannot be null or empty");
        }
        this.passportID = passportID;
    }

    /**
     * Метод, который проверяет на равенство текущий объект с другим.
     * 
     * @param o объект для сравнения.
     * @return true, если объекты равны, иначе false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return height == person.height &&
               weight == person.weight &&
               Objects.equals(name, person.name) &&
               Objects.equals(birthday, person.birthday) &&
               Objects.equals(passportID, person.passportID);
    }

    /**
     * Метод, который вычисляет хеш-код для объекта.
     * 
     * @return хеш-код для объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, height, weight, passportID);
    }

    /**
     * Метод, который возвращает строковое представление объекта {@link Person}.
     * 
     * @return строковое представление объекта {@link Person}.
     */
    @Override
    public String toString() {
        return "    name='" + name + '\'' + "\n" +
               "    birthday=" + birthday + "\n" +
               "    height=" + height + "\n" +
               "    weight=" + weight + "\n" +
               "    passportID='" + passportID + '\'';
    }
}
