package ProgrammManagment;

import entity.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CommandExecuteScript extends Command {
    private final CommandManager commandManager;
    private static final Set<String> executingScripts = new HashSet<>();

    public CommandExecuteScript(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

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

                    Long key;
                    try {
                        key = Long.parseLong(commandArgs[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Ключ должен быть числом.");
                        continue;
                    }

                    if (commandName.equals("insert") && MovieCollection.getMovies().containsKey(key)) {
                        System.out.println("Ошибка: Элемент с таким ключом уже существует!");
                        continue;
                    }
                    if (commandName.equals("update") && !MovieCollection.getMovies().containsKey(key)) {
                        System.out.println("Ошибка: Элемент с таким ключом не найден!");
                        continue;
                    }

                    String[] data = new String[11];
                    for (int i = 0; i < 11; i++) {
                        if (!scanner.hasNextLine()) {
                            System.out.println("Ошибка: Недостаточно данных для команды '" + commandName + "'.");
                            break;
                        }
                        data[i] = scanner.nextLine().trim();
                    }

                    Movie movie = parseMovie(data);
                    if (movie == null) {
                        System.out.println("Ошибка: Неверный формат данных. Фильм не добавлен.");
                        continue;
                    }

                    MovieCollection.getMovies().put(key, movie);
                    System.out.println("Фильм успешно " + (commandName.equals("insert") ? "добавлен" : "обновлен") + " в коллекции!");
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

    private Movie parseMovie(String[] data) {
        try {
            if (data[0] == null || data[0].isEmpty()) return null;
            String name = data[0];

            Double x = Double.parseDouble(data[1]);
            if (x > 61) return null;
            Long y = Long.parseLong(data[2]);
            Coordinates coordinates = new Coordinates(x, y);

            Integer oscarsCount = Integer.parseInt(data[3]);
            if (oscarsCount <= 0) return null;

            MovieGenre genre = data[4] != null && !data[4].isEmpty() ? MovieGenre.valueOf(data[4].toUpperCase()) : null;
            MpaaRating mpaaRating = data[5] != null && !data[5].isEmpty() ? MpaaRating.valueOf(data[5].toUpperCase()) : null;

            if (data[6] == null || data[6].isEmpty()) return null;
            String operatorName = data[6];

            LocalDateTime operatorBirthday = data[7] != null && !data[7].isEmpty() ? LocalDateTime.parse(data[7]) : null;
            Integer operatorHeight = Integer.parseInt(data[8]);
            if (operatorHeight <= 0) return null;

            Integer operatorWeight = Integer.parseInt(data[9]);
            if (operatorWeight <= 0) return null;

            String passportID = data[10];
            if (passportID == null || passportID.isEmpty() || InputChecker.usedPassportIDs.contains(passportID)) {
                return null;
            }
            InputChecker.usedPassportIDs.add(passportID);

            Person operator = new Person(operatorName, operatorBirthday, operatorHeight, operatorWeight, passportID);
            return new Movie(name, coordinates, LocalDateTime.now(), oscarsCount, genre, mpaaRating, operator);
        } catch (Exception e) {
            return null;
        }
    }
}
