package HelperClasses;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import ProgrammManagment.InputChecker;
import entity.Coordinates;
import entity.Movie;
import entity.MovieGenre;
import entity.MpaaRating;
import entity.Person;

/**
 * Класс, отвечающий за разбор и загрузку данных о фильмах из CSV-файла.
 * <p>
 * Этот класс читает CSV-файл, парсит его строки и создает соответствующие объекты {@link Movie} с использованием данных из файла.
 * </p>
 */
public class CsvParser {
    private BufferedReader reader;

    /**
     * Конструктор для инициализации объекта {@link CsvParser}.
     * Открывает файл для чтения.
     *
     * @param filePath Путь к CSV-файлу.
     * @throws IOException если не удается открыть или прочитать файл.
     */
    public CsvParser(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Метод для парсинга фильмов из CSV-файла.
     * Читает файл построчно, извлекает данные и создает соответствующие объекты {@link Movie}.
     *
     * @return Список объектов {@link Movie}, который был загружен из файла.
     * @throws IOException если возникает ошибка при чтении файла.
     */
    public List<Movie> parseMovies() throws IOException {
        List<Movie> movies = new ArrayList<>();
        String line;
        boolean isFirstLine = true;
        
        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            String[] data = line.split(",");

            String movieName = "Неизвестное название";
            
            try {
                Long id = Long.parseLong(data[0]);
                movieName = data[1];
                String name = data[1];
                Double x = Double.parseDouble(data[2]);
                Long y = Long.parseLong(data[3]);
                LocalDateTime creationDate = LocalDateTime.parse(data[4]);
                Integer oscarsCount = Integer.parseInt(data[5]);
                
                MovieGenre genre = null;
                if (!data[6].isBlank()) {
                	genre = MovieGenre.valueOf(data[6]);
                }
                
                MpaaRating mpaaRating = null;
                if (!data[7].isBlank()) {
                	mpaaRating = MpaaRating.valueOf(data[7]);
                }
                
                String operatorName = data[8];
                LocalDateTime operatorBirthday = null;
                if (!data[9].isBlank()) {
                    operatorBirthday = LocalDateTime.parse(data[9]);
                }
                int operatorHeight = Integer.parseInt(data[10]);
                int operatorWeight = Integer.parseInt(data[11]);
                String operatorPassportID = data[12];
                InputChecker.usedPassportIDs.add(operatorPassportID);

                Coordinates coordinates = new Coordinates(x, y);
                Person operator = new Person(operatorName, operatorBirthday, operatorHeight, operatorWeight, operatorPassportID);
                Movie movie = new Movie(id, name, coordinates, creationDate, oscarsCount, genre, mpaaRating, operator);

                movies.add(movie);
            } catch (Exception e) {
                System.out.println("Фильм \"" + movieName + "\" был удален из-за ошибки обработки строки: " + line);
            }
        }
        return movies;
    }
}