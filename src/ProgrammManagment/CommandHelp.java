package ProgrammManagment;

import java.util.Map;

/**
 * Класс, реализующий команду, которая выводит список всех доступных команд.
 * <p>
 * Команда позволяет пользователю увидеть список всех команд, доступных в системе.
 * </p>
 */
public class CommandHelp extends Command {
    private final Map<String, Command> commands;

    /**
     * Конструктор для инициализации команды и списка доступных команд.
     * 
     * @param commands Карта, содержащая все доступные команды.
     */
    public CommandHelp(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * Выполняет команду, выводя список всех доступных команд.
     * <p>
     * Если команда вызывается с аргументами, выводится сообщение об ошибке.
     * </p>
     * 
     * @param args Массив аргументов для команды. Ожидается отсутствие аргументов.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("Данная команда не должна содержать аргументов.");
        } else {
            System.out.println("Доступные команды:");
            for (String command : commands.keySet()) {
                System.out.println(" - " + command);
            }
        }
    }
}