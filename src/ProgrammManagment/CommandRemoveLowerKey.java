package ProgrammManagment;

import entity.MovieCollection;

import java.util.Iterator;

/**
 * Команда для удаления фильмов из коллекции с ключом меньшим, чем заданный.
 * <p>
 * Этот класс реализует команду, которая удаляет все фильмы из коллекции, у которых ключ меньше, чем заданный в качестве аргумента.
 * Если таких фильмов нет в коллекции, выводится соответствующее сообщение.
 * </p>
 */
public class CommandRemoveLowerKey extends Command {

    /**
     * Выполняет команду удаления фильмов с ключом меньше заданного.
     * <p>
     * Проверяется, что команда принимает 1 аргумент (ключ элемента), затем удаляются все фильмы с ключом меньшим, чем указанный.
     * Если такие фильмы есть, они удаляются, и выводится сообщение об успешном удалении.
     * Если таких фильмов нет, выводится сообщение об отсутствии таких элементов.
     * </p>
     *
     * @param args Аргументы команды. Ожидается один аргумент: строка, представляющая ключ, по которому фильтры будут удалены.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Ошибка: Команда remove_lower_key требует 1 аргумент (ключ).");
            return;
        }

        Long key;
        try {
            key = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Ключ должен быть числом.");
            return;
        }

        Iterator<Long> iterator = MovieCollection.getMovies().keySet().iterator();
        int removedCount = 0;
        while (iterator.hasNext()) {
            Long currentKey = iterator.next();
            if (currentKey < key) {
                iterator.remove();
                removedCount++;
            }
        }

        if (removedCount > 0) {
            System.out.println("Удалено " + removedCount + " фильма(ов) с ключом меньше, чем " + key + ".");
        } else {
            System.out.println("Не найдено фильмов с ключом меньше, чем " + key + ".");
        }
    }
}