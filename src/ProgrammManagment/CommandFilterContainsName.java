package ProgrammManagment;

import entity.Movie;
import entity.MovieCollection;

/**
 * Класс, реализующий команду для фильтрации фильмов по подстроке в названии.
 * <p>
 * Команда фильтрует коллекцию фильмов и выводит те, у которых название содержит указанную подстроку (без учета регистра).
 * </p>
 */
public class CommandFilterContainsName extends Command {

    /**
     * Выполняет команду фильтрации фильмов по подстроке в названии.
     * <p>
     * Команда принимает один аргумент: подстроку, которую нужно искать в названии фильмов.
     * Фильмы, содержащие указанную подстроку, выводятся на экран.
     * </p>
     * 
     * @param args Массив аргументов для команды. Ожидается 1 аргумент: подстрока для поиска в названии.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Ошибка: Команда filter_contains_name требует 1 аргумент (подстроку для поиска).");
            return;
        }

        String substring = args[0].toLowerCase();
        boolean found = false;
        
        for (Movie movie : MovieCollection.getMovies().values()) {
            if (movie.getName().toLowerCase().contains(substring)) {
                System.out.println(movie);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Ошибка: Фильмы с таким названием не найдены.");
        }
    }
}