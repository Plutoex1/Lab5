package ProgrammManagment;

import entity.Movie;
import entity.MovieCollection;

/**
 * Команда для вычисления и вывода суммы всех Оскаров в коллекции фильмов.
 * <p>
 * Этот класс реализует команду, которая суммирует количество Оскаров для всех фильмов в коллекции
 * и выводит общий результат.
 * </p>
 */
public class CommandSumOfOscarsCount extends Command {

    /**
     * Выполняет команду для вычисления суммы всех Оскаров в коллекции фильмов.
     * <p>
     * Метод проверяет, что команда не принимает аргументов. После этого он проходит по коллекции фильмов
     * и суммирует количество Оскаров для каждого фильма.
     * </p>
     *
     * @param args Аргументы команды. Ожидается, что команда не будет содержать аргументов.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("Ошибка: Команда sum_of_oscars_count не принимает аргументы.");
            return;
        }

        int totalOscarsCount = 0;

        for (Movie movie : MovieCollection.getMovies().values()) {
            totalOscarsCount += movie.getOscarsCount();
        }

        System.out.println("Сумма всех Оскаров: " + totalOscarsCount);
    }
}
