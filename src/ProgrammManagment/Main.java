package ProgrammManagment;

import java.util.Arrays;

import HelperClasses.InputReader;
import entity.MovieCollection;

/**
 * Основной класс программы, который запускает приложение и обрабатывает пользовательские команды.
 * <p>
 * Этот класс управляет загрузкой фильмов, обработкой команд и взаимодействием с пользователем через командную строку.
 * Программа предоставляет пользователю возможность взаимодействовать с коллекцией фильмов с помощью различных команд.
 * </p>
 */
public class Main {

    /**
     * Флаг для продолжения работы приложения.
     * Если значение флага равно true, приложение продолжает работать, иначе завершает выполнение.
     */
    public static boolean process = true;

    /**
     * Главный метод программы, который выполняет запуск приложения и обрабатывает команды пользователя.
     * <p>
     * Метод загружает коллекцию фильмов из файла, предоставленного через переменную окружения MOVIE_FILE,
     * и продолжает ожидать и обрабатывать команды пользователя до тех пор, пока процесс не будет завершен.
     * </p>
     *
     * @param args Массив аргументов командной строки (не используется в данной реализации).
     */
    public static void main(String[] args) {
        String fileName = System.getenv("MOVIE_FILE");

        if (fileName == null || fileName.isEmpty()) {
            System.err.println("Ошибка: Переменная окружения MOVIE_FILE не установлена.");
            return;
        }

        InputReader inputReader = new InputReader();
        CommandParser cmdPars = new CommandParser();
        CommandManager commandManager = new CommandManager();

        MovieCollection.loadMovies(fileName);

        System.out.println("Введите команду help для ознакомления с доступными командами.");

        while (process == true) {
            System.out.print("> ");
            String str = inputReader.readString();

            String[] parts = cmdPars.parseCommandName(str);
            String commandName = parts[0];
            String[] argsArray = (parts.length > 1) ? Arrays.copyOfRange(parts, 1, parts.length) : new String[0];

            commandManager.executeCommand(commandName, argsArray);
        }
    }
}
