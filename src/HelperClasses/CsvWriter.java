package HelperClasses;

import java.io.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import entity.Movie;
import entity.MovieCollection;

/**
 * Класс для записи данных о фильмах в CSV файл.
 * <p>
 * Этот класс позволяет записывать коллекцию фильмов в файл в формате CSV. Данные сортируются перед записью.
 * </p>
 */
public class CsvWriter {
    
    /**
     * Метод для записи отсортированных данных о фильмах в CSV файл.
     * <p>
     * Данные о фильмах записываются в файл в формате CSV с заголовками:
     * id, name, x, y, creationDate, oscarsCount, genre, mpaaRating, operatorName, operatorBirthday, operatorHeight, operatorWeight, operatorPassportID
     * </p>
     *
     * @param filePath Путь к файлу, в который будут записаны данные.
     */
    public static void writeToFile(String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
             BufferedWriter writer = new BufferedWriter(osw)) {
            
            writer.write("id,name,x,y,creationDate,oscarsCount,genre,mpaaRating,operatorName,operatorBirthday,operatorHeight,operatorWeight,operatorPassportID");
            writer.newLine();
            
            List<Movie> sortedMovies = MovieCollection.sortedMovie();
            
            for (Movie movie : sortedMovies) {
                writer.write(formatMovieToCsv(movie));
                writer.newLine();
            }
            
            System.out.println("Отсортированные данные успешно записаны в файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
    
    /**
     * Форматирует объект {@link Movie} в строку CSV.
     *
     * @param movie Объект фильма, который нужно преобразовать в строку CSV.
     * @return Строка, представляющая фильм в формате CSV.
     */
    private static String formatMovieToCsv(Movie movie) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        
        return String.join(",",
                String.valueOf(movie.getId()),
                movie.getName(),
                String.valueOf(movie.getCoordinates().getX()),
                String.valueOf(movie.getCoordinates().getY()),
                movie.getCreationDate().format(formatter),
                String.valueOf(movie.getOscarsCount()),
                movie.getGenre() != null ? movie.getGenre().name() : "",
                movie.getMpaaRating() != null ? movie.getMpaaRating().name() : "",
                movie.getOperator() != null ? movie.getOperator().getName() : "",
                (movie.getOperator() != null && movie.getOperator().getBirthday() != null)
                        ? movie.getOperator().getBirthday().format(formatter)
                        : "",
                movie.getOperator() != null ? String.valueOf(movie.getOperator().getHeight()) : "",
                movie.getOperator() != null ? String.valueOf(movie.getOperator().getWeight()) : "",
                movie.getOperator() != null ? movie.getOperator().getPassportID() : ""
        );
    }
}