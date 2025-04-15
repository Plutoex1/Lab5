package ProgrammManagment;

import entity.*;

import java.time.LocalDateTime;

public class CommandInsert extends Command {

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Ошибка: Данная команда должна содержать 1 аргумент (ключ элемента).");
            return;
        }

        try {
            Long key = Long.parseLong(args[0]);

            if (MovieCollection.getMovies().containsKey(key)) {
                System.out.println("Ошибка: Элемент с таким ключом уже существует.");
                return;
            }

            InputChecker read = new InputChecker();
            Object[] inputData = read.readMovieInput(); // Получаем массив данных

            // Распаковываем данные
            String name = (String) inputData[0];
            Double x = (Double) inputData[1];
            Long y = (Long) inputData[2];
            Integer oscarsCount = (Integer) inputData[3];
            MovieGenre genre = (MovieGenre) inputData[4];
            MpaaRating rating = (MpaaRating) inputData[5];
            String operatorName = (String) inputData[6];
            LocalDateTime birthday = (LocalDateTime) inputData[7];
            Integer height = (Integer) inputData[8];
            Integer weight = (Integer) inputData[9];
            String passportID = (String) inputData[10];

            // Создаём необходимые объекты
            Coordinates coordinates = new Coordinates(x, y);
            Person operator = new Person(operatorName, birthday, height, weight, passportID);
            Movie movie = new Movie(name, coordinates, LocalDateTime.now(), oscarsCount, genre, rating, operator);

            // Добавляем фильм в коллекцию
            MovieCollection.getMovies().put(key, movie);

            System.out.println("Фильм успешно добавлен в коллекцию!");
            
        } catch (NullPointerException e) {
            System.out.println();
            System.out.println("Ввод остановлен.");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Ключ должен быть целым числом.");
        } catch (Exception e) {
            System.out.println("Произошла ошибка при создании фильма: " + e.getMessage());
        }
    }
}
