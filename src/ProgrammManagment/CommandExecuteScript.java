package ProgrammManagment;

import entity.MovieGenre;
import entity.MpaaRating;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Класс, реализующий команду для выполнения скрипта команд из файла.
 * <p>
 * Команда считывает строки из указанного файла и выполняет команды, записанные в этом файле.
 * Поддерживает рекурсивное выполнение скриптов и проверку зацикливания.
 * </p>
 */
public class CommandExecuteScript extends Command {

    private final CommandManager commandManager;
    private static final Set<String> executingScripts = new HashSet<>();

    /**
     * Конструктор для инициализации команды с менеджером команд.
     *
     * @param commandManager Менеджер команд для выполнения команд.
     */
    public CommandExecuteScript(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команды из указанного файла.
     * <p>
     * Метод читает файл и выполняет команды в нем поочередно. Если в файле встречаются другие скрипты, 
     * они выполняются рекурсивно. Проверяется, чтобы не было зацикливания выполнения скриптов.
     * </p>
     * 
     * @param args Массив аргументов, содержащий путь к файлу скрипта. Должен содержать ровно один элемент — путь к файлу.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1 || args[0].trim().isEmpty()) {
            System.out.println("Ошибка: Команда execute_script требует 1 аргумент (путь к файлу).");
            return;
        }

        File scriptFile = new File(args[0]).getAbsoluteFile();
        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.out.println("Ошибка: Файл не найден или это не файл.");
            return;
        }

        if (executingScripts.contains(scriptFile.getAbsolutePath())) {
            System.out.println("Ошибка: Обнаружено зацикливание! Файл '" + scriptFile.getAbsolutePath() + "' уже выполняется.");
            return;
        }

        executingScripts.add(scriptFile.getAbsolutePath());

        try (Scanner scanner = new Scanner(scriptFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] commandParts = line.split(" ", 2);
                String commandName = commandParts[0];
                String[] commandArgs = commandParts.length > 1 ? new String[]{commandParts[1].trim()} : new String[0];

                System.out.println(">> Выполняем команду: " + commandName);

                if (commandName.equals("insert") || commandName.equals("update")) {
                    if (commandArgs.length != 1) {
                        System.out.println("Ошибка: Команда '" + commandName + "' должна содержать 1 аргумент (ключ элемента).");
                        continue;
                    }

                    String[] data = new String[11];
                    for (int i = 0; i < 11; i++) {
                        if (!scanner.hasNextLine()) {
                            System.out.println("Ошибка: Недостаточно данных для команды '" + commandName + "'.");
                            break;
                        }
                        String input = scanner.nextLine().trim();
                        data[i] = input.isEmpty() ? null : input;
                    }

                    if (!validateData(data)) {
                        System.out.println("Ошибка: Неверный формат данных для команды '" + commandName + "'.");
                        continue;
                    }

                    System.out.println("Фильм успешно добавлен в коллекцию!");
                } else if (commandName.equals("execute_script")) {
                    execute(commandArgs);
                } else {
                    if (!commandManager.executeCommand(commandName, commandArgs)) {
                        System.out.println("Ошибка: Команда '" + commandName + "' не найдена или имеет неверные аргументы.");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: Файл не найден.");
        } finally {
            executingScripts.remove(scriptFile.getAbsolutePath());
        }
    }

    /**
     * Валидирует данные для команды 'insert' или 'update'.
     * Проверяется корректность формата данных для фильма, оператора и других полей.
     * <p>
     * Также проверяется уникальность паспортных данных оператора.
     * </p>
     * 
     * @param data Массив строк, содержащий данные для команды 'insert' или 'update'.
     * @return {@code true}, если данные валидны, иначе {@code false}.
     */
    private boolean validateData(String[] data) {
        try {
            if (data[0] == null) return false;
            if (data[1] != null && Double.parseDouble(data[1]) > 61) return false;
            if (data[2] == null) return false;
            Long.parseLong(data[2]);
            if (data[3] == null || Integer.parseInt(data[3]) <= 0) return false;

            if (data[4] != null) {
                try {
                    MovieGenre.valueOf(data[4].toUpperCase());
                } catch (IllegalArgumentException e) {
                    return false;
                }
            }

            if (data[5] != null) {
                try {
                    MpaaRating.valueOf(data[5].toUpperCase());
                } catch (IllegalArgumentException e) {
                    return false;
                }
            }

            if (data[6] == null) return false;
            if (data[7] != null) java.time.LocalDateTime.parse(data[7]);
            if (data[8] == null || Integer.parseInt(data[8]) <= 0) return false;
            if (data[9] == null || Integer.parseInt(data[9]) <= 0) return false;

            if (data[10] == null) return false;
            if (CommandInsert.usedPassportIDs.contains(data[10])) {
                System.out.println("Ошибка: Паспортный идентификатор '" + data[10] + "' уже существует.");
                return false;
            }
            CommandInsert.usedPassportIDs.add(data[10]);

            return true;
        } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
            return false;
        }
    }
}
