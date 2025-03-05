package ProgrammManagment;

import entity.MovieCollection;

/**
 * Команда для удаления фильма из коллекции по ключу.
 * <p>
 * Этот класс реализует команду, которая удаляет фильм из коллекции по заданному ключу. Если элемента с таким ключом
 * не существует, выводится соответствующее сообщение.
 * </p>
 */
public class CommandRemoveKey extends Command {

    /**
     * Выполняет команду удаления фильма по ключу.
     * <p>
     * Проверяет, что команда принимает 1 аргумент (ключ элемента), затем пытается удалить фильм с указанным ключом.
     * Если фильм с таким ключом существует в коллекции, он будет удален. Если элемента с таким ключом нет, будет выведено
     * сообщение об ошибке.
     * </p>
     *
     * @param args Аргументы команды. Ожидается один аргумент: строка, представляющая ключ фильма, который необходимо удалить.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Данная команда должна содержать 1 аргумент(ключ элемента).");
        } else {
            try {
                Long key = Long.parseLong(args[0]);

                if (MovieCollection.getMovies().containsKey(key)) {
                    MovieCollection.getMovies().remove(key);
                    System.out.println("Элемент с ключом " + key + " успешно удален.");
                } else {
                    System.out.println("Ошибка: Элемента с таким ключом не существует.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Ключ должен быть целым числом.");
            }
        }
    }
}
