# Лабораторная работа #5
# Вариант 5281
```java
public class Movie {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer oscarsCount; //Значение поля должно быть больше 0, Поле не может быть null
    private MovieGenre genre; //Поле может быть null
    private MpaaRating mpaaRating; //Поле может быть null
    private Person operator; //Поле может быть null
}
public class Coordinates {
    private Double x; //Максимальное значение поля: 61, Поле не может быть null
    private long y;
}
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле может быть null
    private int height; //Значение поля должно быть больше 0
    private int weight; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле не может быть null
}
public enum MovieGenre {
    ACTION,
    MUSICAL,
    TRAGEDY;
}
public enum MpaaRating {
    G,
    PG,
    PG_13,
    R,
    NC_17;
}
```
