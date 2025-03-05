package entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс, представляющий фильм.
 * <p>
 * Этот класс включает поля, такие как ID, название фильма, координаты, дата создания, количество Оскаров, жанр, рейтинг MPAA
 * и оператор, а также соответствующие методы для работы с этими полями.
 * </p>
 */
public class Movie {

    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private Integer oscarsCount;
    private MovieGenre genre;
    private MpaaRating mpaaRating;
    private Person operator;

    /**
     * Конструктор для создания объекта фильма с указанными значениями.
     * 
     * @param id уникальный идентификатор фильма, не может быть null, больше 0
     * @param name название фильма, не может быть null и не может быть пустым
     * @param coordinates координаты фильма, не могут быть null
     * @param creationDate дата создания фильма, не может быть null, генерируется автоматически
     * @param oscarsCount количество Оскаров, должно быть больше 0, не может быть null
     * @param genre жанр фильма, может быть null
     * @param mpaaRating рейтинг MPAA фильма, может быть null
     * @param operator оператор фильма, может быть null
     */
    public Movie(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, Integer oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person operator) {
        this.id = id;
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(LocalDateTime.now());
        setOscarsCount(oscarsCount);
        setGenre(genre);
        setMpaaRating(mpaaRating);
        setOperator(operator);
    }

    /**
     * Конструктор для создания объекта фильма с автоматически генерируемым ID.
     * 
     * @param name название фильма, не может быть null и не может быть пустым
     * @param coordinates координаты фильма, не могут быть null
     * @param creationDate дата создания фильма, не может быть null, генерируется автоматически
     * @param oscarsCount количество Оскаров, должно быть больше 0, не может быть null
     * @param genre жанр фильма, может быть null
     * @param mpaaRating рейтинг MPAA фильма, может быть null
     * @param operator оператор фильма, может быть null
     */
    public Movie(String name, Coordinates coordinates, LocalDateTime creationDate, Integer oscarsCount, MovieGenre genre, MpaaRating mpaaRating, Person operator) {
        this.id = MovieCollection.generateId();
        setName(name);
        setCoordinates(coordinates);
        setCreationDate(LocalDateTime.now());
        setOscarsCount(oscarsCount);
        setGenre(genre);
        setMpaaRating(mpaaRating);
        setOperator(operator);
    }

    /**
     * Получить уникальный идентификатор фильма.
     * 
     * @return id фильма.
     */
    public Long getId() {
        return id;
    }

    /**
     * Получить название фильма.
     * 
     * @return название фильма.
     */
    public String getName() {
        return name;
    }

    /**
     * Получить координаты фильма.
     * 
     * @return координаты фильма.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Получить дату создания фильма.
     * 
     * @return дата создания фильма.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Получить количество Оскаров, полученных фильмом.
     * 
     * @return количество Оскаров.
     */
    public Integer getOscarsCount() {
        return oscarsCount;
    }

    /**
     * Получить жанр фильма.
     * 
     * @return жанр фильма.
     */
    public MovieGenre getGenre() {
        return genre;
    }

    /**
     * Получить рейтинг MPAA фильма.
     * 
     * @return рейтинг MPAA.
     */
    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    /**
     * Получить оператора фильма.
     * 
     * @return оператор фильма.
     */
    public Person getOperator() {
        return operator;
    }

    /**
     * Установить название фильма.
     * 
     * @param name название фильма.
     * @throws IllegalArgumentException если название пустое или null.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Установить координаты фильма.
     * 
     * @param coordinates координаты фильма.
     * @throws IllegalArgumentException если координаты равны null.
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        this.coordinates = coordinates;
    }

    /**
     * Установить количество Оскаров, полученных фильмом.
     * 
     * @param oscarsCount количество Оскаров.
     * @throws IllegalArgumentException если количество Оскаров меньше или равно 0, или равно null.
     */
    public void setOscarsCount(Integer oscarsCount) {
        if (oscarsCount == null || oscarsCount <= 0) {
            throw new IllegalArgumentException("Oscars count must be greater than 0 and cannot be null");
        }
        this.oscarsCount = oscarsCount;
    }

    /**
     * Установить жанр фильма.
     * 
     * @param genre жанр фильма.
     */
    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    /**
     * Установить рейтинг MPAA фильма.
     * 
     * @param mpaaRating рейтинг MPAA.
     */
    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    /**
     * Установить оператора фильма.
     * 
     * @param operator оператор фильма.
     */
    public void setOperator(Person operator) {
        this.operator = operator;
    }

    /**
     * Установить дату создания фильма.
     * 
     * @param creationDate дата создания фильма.
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Проверяет, равен ли текущий фильм другому объекту.
     * 
     * @param o объект для сравнения.
     * @return true, если фильмы равны (по id), иначе false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    /**
     * Возвращает хэш-код фильма.
     * 
     * @return хэш-код фильма, основанный на id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Возвращает строковое представление фильма.
     * 
     * @return строковое представление фильма, включая все его поля.
     */
    @Override
    public String toString() {
        return "Movie {" + "\n" +
                "  id = " + id + "\n" +
                "  name = " + name + "\n" +
                "  coordinates: " + "\n" +
                "    X = " + coordinates.getX() + ", Y = " + coordinates.getY() + "\n" +
                "  creationDate = " + creationDate + "\n" +
                "  oscarsCount = " + oscarsCount + "\n" +
                "  genre = " + (genre != null ? genre : "не указан") + "\n" +
                "  mpaaRating = " + (mpaaRating != null ? mpaaRating : "не указан") + "\n" +
                "  operator: " + "\n" +
                (operator != null ? "" + operator.toString() : "    нет данных") + "\n" +
                '}';
    }
}
