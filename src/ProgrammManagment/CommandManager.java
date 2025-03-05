package ProgrammManagment;

import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер команд, который управляет коллекцией доступных команд и их выполнением.
 * <p>
 * Класс предназначен для регистрации и выполнения команд, введенных пользователем.
 * Он хранит карты команд и может выполнить команду по её имени.
 * </p>
 */
public class CommandManager {
    /** Коллекция команд с их именами в качестве ключей */
    private Map<String, Command> commands = new HashMap<>();

    /**
     * Конструктор класса. Инициализирует список команд, которые могут быть выполнены.
     * Каждая команда добавляется в коллекцию с уникальным именем.
     */
    public CommandManager() {
        commands.put("help", new CommandHelp(commands));              // Команда помощи
        commands.put("exit", new CommandExit());                      // Команда выхода
        commands.put("save", new CommandSave());                      // Команда сохранения
        commands.put("info", new CommandInfo());                      // Команда информации о коллекции
        commands.put("show", new CommandShow());                      // Команда отображения всех элементов
        commands.put("clear", new CommandClear());                    // Команда очистки коллекции
        commands.put("remove_key", new CommandRemoveKey());           // Команда удаления по ключу
        commands.put("insert", new CommandInsert());                  // Команда добавления нового элемента
        commands.put("update", new CommandUpdate());                  // Команда обновления элемента
        commands.put("execute_script", new CommandExecuteScript(this));// Команда выполнения скрипта
        commands.put("replaceIf_greater", new CommandReplaceIfGreater(new CommandUpdate())); // Команда замены, если больше
        commands.put("filter_contains_name", new CommandFilterContainsName());  // Команда фильтрации по имени
        commands.put("sum_of_oscars_count", new CommandSumOfOscarsCount());    // Команда подсчета суммы Оскаров
        commands.put("remove_all_by_mpaa_rating", new CommandRemoveAllByMpaaRating());  // Команда удаления по MPAA рейтингу
        commands.put("remove_lower_key", new CommandRemoveLowerKey());         // Команда удаления по ключу ниже
        commands.put("remove_greater_key", new CommandRemoveGreaterKey());     // Команда удаления по ключу больше
    }

    /**
     * Выполняет команду, если она существует в коллекции.
     * <p>
     * Если команда найдена в коллекции команд, она будет выполнена с переданными аргументами.
     * В противном случае будет выведено сообщение об ошибке.
     * </p>
     *
     * @param commandName Имя команды для выполнения.
     * @param args Аргументы, передаваемые команде.
     * @return {@code true}, если команда успешно выполнена, иначе {@code false}.
     */
    public boolean executeCommand(String commandName, String[] args) {
        Command command = commands.get(commandName);
        if (command != null) {
            command.execute(args);
            return true;
        } else {
            System.out.println("Команда не найдена!");
            return false;
        }
    }
}
