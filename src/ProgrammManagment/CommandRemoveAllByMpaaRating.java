package ProgrammManagment;

import entity.Movie;
import entity.MpaaRating;
import entity.MovieCollection;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Команда для удаления всех фильмов из коллекции, которые имеют указанный MPAA рейтинг.
 * <p>
 * Этот класс реализует команду, которая удаляет все фильмы из коллекции, у которых MPAA рейтинг соответствует
 * переданному значению. Если фильмов с таким рейтингом нет, выводится соответствующее сообщение.
 * </p>
 */
public class CommandRemoveAllByMpaaRating extends Command {

    /**
     * Выполняет команду удаления всех фильмов с заданным MPAA рейтингом.
     * <p>
     * Проверяет аргументы команды, преобразует указанный MPAA рейтинг в значение перечисления, находит и удаляет
     * фильмы из коллекции с этим рейтингом. Если в коллекции нет таких фильмов, выводится сообщение об ошибке.
     * </p>
     *
     * @param args Аргументы команды. Ожидается один аргумент: строка, представляющая MPAA рейтинг фильма.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Ошибка: Команда remove_all_by_mpaa_rating требует 1 аргумент (MPAA рейтинг).");
            return;
        }

        MpaaRating mpaaRating;
        try {
            mpaaRating = MpaaRating.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Неверный MPAA рейтинг. Допустимые значения: G, PG, PG_13, R, NC_17.");
            return;
        }

        List<Long> keysToRemove = new ArrayList<>();
        for (Movie movie : MovieCollection.getMovies().values()) {
            if (movie.getMpaaRating() == mpaaRating) {
                keysToRemove.add(movie.getId());
            }
        }

        int removedCount = 0;
        Iterator<Long> iterator = keysToRemove.iterator();
        while (iterator.hasNext()) {
            Long key = iterator.next();
            MovieCollection.getMovies().remove(key);
            removedCount++;
        }

        if (removedCount > 0) {
            System.out.println("Удалено " + removedCount + " фильма(ов) с рейтингом " + mpaaRating + ".");
        } else {
            System.out.println("Фильмов с рейтингом " + mpaaRating + " не найдено в коллекции.");
        }
    }
}
