package ProgrammManagment;

import entity.MovieCollection;

import java.util.Iterator;

/**
 * Команда для удаления всех фильмов с ключом, большим заданного значения.
 * <p>
 * Этот класс реализует команду, которая удаляет все фильмы из коллекции, у которых ключ больше, чем заданное
 * значение. Если фильмов с таким ключом нет, выводится соответствующее сообщение.
 * </p>
 */
public class CommandRemoveGreaterKey extends Command {

    /**
     * Выполняет команду удаления всех фильмов с ключом больше заданного.
     * <p>
     * Проверяет аргументы команды, преобразует указанный ключ в число, затем удаляет все фильмы, у которых
     * ключ больше заданного значения. Если таких фильмов нет, выводится сообщение об ошибке.
     * </p>
     *
     * @param args Аргументы команды. Ожидается один аргумент: строка, представляющая ключ для сравнения.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Ошибка: Команда remove_greater_key требует 1 аргумент (ключ).");
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
            if (currentKey > key) {
                iterator.remove();
                removedCount++;
            }
        }

        if (removedCount > 0) {
            System.out.println("Удалено " + removedCount + " фильма(ов) с ключом больше, чем " + key + ".");
        } else {
            System.out.println("Не найдено фильмов с ключом больше, чем " + key + ".");
        }
    }
}
