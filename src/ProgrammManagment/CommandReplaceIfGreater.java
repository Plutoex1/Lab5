package ProgrammManagment;

import entity.Movie;
import entity.MovieCollection;

/**
 * Команда для замены фильма в коллекции, если количество Оскаров нового фильма больше, чем у текущего.
 * <p>
 * Этот класс реализует команду, которая заменяет фильм в коллекции, если количество Оскаров нового фильма больше, чем у текущего.
 * Если новое количество Оскаров не больше текущего, замена не происходит, и текущий фильм остается в коллекции.
 * </p>
 */
public class CommandReplaceIfGreater extends Command {

    private final CommandUpdate commandUpdate;

    /**
     * Конструктор класса.
     * <p>
     * Устанавливает команду обновления для использования в процессе замены.
     * </p>
     *
     * @param commandUpdate Команда для обновления фильма в коллекции.
     */
    public CommandReplaceIfGreater(CommandUpdate commandUpdate) {
        this.commandUpdate = commandUpdate;
    }

    /**
     * Выполняет команду замены фильма, если количество Оскаров нового фильма больше, чем у текущего.
     * <p>
     * Если ключ существует в коллекции, сравнивается количество Оскаров текущего и нового фильма.
     * Если у нового фильма больше Оскаров, он заменяет старый фильм. В противном случае замена отменяется, и
     * старый фильм остаётся в коллекции.
     * </p>
     *
     * @param args Аргументы команды. Ожидается один аргумент: строка, представляющая ключ фильма, который будет заменён.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Ошибка: Команда replace_if_greater требует 1 аргумент (ключ).");
            return;
        }

        try {
            Long key = Long.parseLong(args[0]);

            if (!MovieCollection.getMovies().containsKey(key)) {
                System.out.println("Ошибка: Элемента с ключом '" + key + "' не существует.");
                return;
            }

            Movie existingMovie = MovieCollection.getMovies().get(key);
            int currentValue = existingMovie.getOscarsCount();

            System.out.println("Введите данные нового фильма для сравнения:");
            commandUpdate.execute(args);

            Movie updatedMovie = MovieCollection.getMovies().get(key);

            if (updatedMovie.getOscarsCount() > currentValue) {
                System.out.println("Замена успешно выполнена! Новое значение больше старого.");
            } else {
                System.out.println("Отмена замены: новое значение (" + updatedMovie.getOscarsCount() +
                        ") не больше текущего (" + currentValue + ").");
                MovieCollection.getMovies().put(key, existingMovie);
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Ключ должен быть числом.");
        }
    }
}