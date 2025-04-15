package ProgrammManagment;

import entity.Movie;
import entity.MovieCollection;

/**
 * Команда для отображения всех фильмов в коллекции.
 * <p>
 * Этот класс реализует команду, которая выводит строковое представление каждого фильма в коллекции,
 * отсортированной с использованием метода {@link MovieCollection#sortedMovie()}.
 * </p>
 */
public class CommandShow extends Command {

    /**
     * Выполняет команду отображения всех фильмов в коллекции.
     * <p>
     * Метод проверяет, что команда не содержит аргументов. Затем для каждого фильма из отсортированной коллекции фильмов
     * вызывается метод {@link Movie#toString()} для вывода строкового представления фильма.
     * </p>
     *
     * @param args Аргументы команды. Ожидается, что команда не будет содержать аргументов.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("Данная команда не должна содержать аргументов.");
        } else {
            System.out.println("Cтроковое представление каждого movie: ");
            for (Movie movie : MovieCollection.sortedMovie()) {
                System.out.println(movie.toString());
            }
        }
    }
}