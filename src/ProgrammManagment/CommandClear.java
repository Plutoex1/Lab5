package ProgrammManagment;

import entity.MovieCollection;

/**
 * Класс, представляющий команду для очистки коллекции фильмов.
 * <p>
 * Этот класс наследует от {@link Command} и реализует метод {@link Command#execute(String[])}.
 * Команда очищает коллекцию фильмов, удаляя все элементы из хранилища {@link MovieCollection#movies}.
 * </p>
 */
public class CommandClear extends Command {

    /**
     * Выполняет очистку коллекции фильмов.
     * <p>
     * Этот метод удаляет все элементы из коллекции {@link MovieCollection#movies}.
     * Если метод был вызван с аргументами, выводится ошибка, так как команда не должна принимать аргументы.
     * </p>
     * 
     * @param args Массив строковых аргументов, переданных командой. Этот массив не должен содержать аргументов.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("Данная команда не должна содержать аргументов.");
        } else {
            MovieCollection.movies.clear();
            System.out.println("Коллекция очищена.");
        }
    }
}
