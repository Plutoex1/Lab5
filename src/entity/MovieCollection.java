package entity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import HelperClasses.CsvParser;

/**
 * Класс, представляющий коллекцию фильмов.
 * <p>
 * Этот класс предоставляет функциональность для загрузки фильмов из CSV файла,
 * сортировки фильмов по имени, а также для работы с хранилищем фильмов, представленным
 * в виде хэш-таблицы. Кроме того, класс предоставляет методы для генерации уникальных ID
 * для фильмов и получения даты инициализации коллекции.
 * </p>
 */
public class MovieCollection {
    /**
     * Хранилище фильмов. Используется хэш-таблица, где ключом является уникальный ID фильма.
     */
    public static Hashtable<Long, Movie> movies = new Hashtable<>(); 
    
    /**
     * Дата инициализации коллекции фильмов.
     */
    public static LocalDateTime DateInitialization = LocalDateTime.now();
    
    /**
     * Загружает фильмы из CSV файла в коллекцию.
     * <p>
     * Метод читает CSV файл по заданному пути и добавляет фильмы в коллекцию.
     * Если файл не может быть прочитан, будет выведено сообщение об ошибке.
     * </p>
     * 
     * @param filePath путь к CSV файлу, из которого нужно загрузить фильмы.
     */
    public static void loadMovies(String filePath) {
        try {
            CsvParser parser = new CsvParser(filePath);
            List<Movie> movieList = parser.parseMovies();

            for (Movie movie : movieList) {
                movies.put(movie.getId(), movie);
            }

            System.out.println("Фильмы успешно загружены в коллекцию!");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    /**
     * Сортирует фильмы в коллекции по названию в алфавитном порядке.
     * 
     * @return отсортированный список фильмов.
     */
    public static ArrayList<Movie> sortedMovie() {
        ArrayList<Movie> sortedList = new ArrayList<>(movies.values());
        sortedList.sort(Comparator.comparing(Movie::getName));

        return sortedList;
    }

    /**
     * Получить все фильмы из коллекции.
     * 
     * @return хэш-таблица всех фильмов, где ключом является уникальный ID фильма.
     */
    public static Hashtable<Long, Movie> getMovies() {
        return movies;
    }
    
    /**
     * Генерирует уникальный ID для нового фильма.
     * <p>
     * Метод находит максимальный ID в коллекции и увеличивает его на 1, чтобы создать новый уникальный ID.
     * </p>
     * 
     * @return сгенерированный уникальный ID.
     */
    public static long generateId() {
        ArrayList<Movie> collection = MovieCollection.sortedMovie();
        return collection.stream()
                     .mapToLong(Movie::getId)
                     .max()
                     .orElse(0) + 1;
    }
    
    /**
     * Получить дату инициализации коллекции фильмов.
     * 
     * @return дата инициализации коллекции.
     */
    public static LocalDateTime getInitializationdate() {
        return DateInitialization;
    }
}
