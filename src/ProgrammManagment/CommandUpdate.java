package ProgrammManagment;

import entity.*;

import java.time.LocalDateTime;

public class CommandUpdate extends Command {

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Ошибка: Данная команда должна содержать 1 аргумент (ID элемента).");
            return;
        }
        
        Movie movieToUpdate = null;
        Long keyToUpdate = null;

        try {
            Long id = Long.parseLong(args[0]);

            for (var entry : MovieCollection.getMovies().entrySet()) {
                if (entry.getValue().getId().equals(id)) {
                    movieToUpdate = entry.getValue();
                    keyToUpdate = entry.getKey();
                    break;
                }
            }

            if (movieToUpdate != null && keyToUpdate != null) {
                MovieCollection.getMovies().remove(keyToUpdate);

                InputChecker checker = new InputChecker();
                Object[] inputData = checker.readMovieInput();

                {
                    // Распаковка данных
                    String name = (String) inputData[0];
                    Double x = (Double) inputData[1];
                    Long y = (Long) inputData[2];
                    Integer oscarsCount = (Integer) inputData[3];
                    MovieGenre genre = (MovieGenre) inputData[4];
                    MpaaRating mpaaRating = (MpaaRating) inputData[5];
                    String operatorName = (String) inputData[6];
                    LocalDateTime birthday = (LocalDateTime) inputData[7];
                    Integer height = (Integer) inputData[8];
                    Integer weight = (Integer) inputData[9];
                    String passportID = (String) inputData[10];

                    Coordinates coordinates = new Coordinates(x, y);
                    Person operator = new Person(operatorName, birthday, height, weight, passportID);
                    Movie updatedMovie = new Movie(id, name, coordinates, LocalDateTime.now(), oscarsCount, genre, mpaaRating, operator);

                    MovieCollection.getMovies().put(keyToUpdate, updatedMovie);
                    System.out.println("Фильм успешно обновлён!");

                } 
            } else {
                System.out.println("Ошибка: Элемента с таким ID не существует.");
            }
        } catch (NullPointerException e) {
        	System.out.println();
            System.out.println("Ввод остановлен.");
            MovieCollection.getMovies().put(keyToUpdate, movieToUpdate); 
            return;
        }
    }
}
