package ProgrammManagment;

import HelperClasses.CsvWriter;

/**
 * Команда для сохранения коллекции фильмов в файл в формате CSV.
 * <p>
 * Этот класс реализует команду, которая сохраняет коллекцию фильмов в файл. Для сохранения используется класс CsvWriter,
 * и файл выбирается с помощью переменной окружения "MOVIE_FILE", которая указывает путь к файлу.
 * </p>
 */
public class CommandSave extends Command {

    /**
     * Выполняет команду сохранения коллекции фильмов в файл в формате CSV.
     * <p>
     * Метод проверяет, что команда не содержит аргументов. Затем он вызывает метод {@link CsvWriter#writeToFile(String)} для
     * записи данных в файл, путь к которому берется из переменной окружения "MOVIE_FILE".
     * </p>
     *
     * @param args Аргументы команды. Ожидается, что команда не будет содержать аргументов.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("Данная команда не должна содержать аргументов.");
        } else {
            CsvWriter.writeToFile(System.getenv("MOVIE_FILE"));
        }
    }
}
