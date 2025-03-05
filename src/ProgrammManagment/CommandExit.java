package ProgrammManagment;

/**
 * Класс, реализующий команду для завершения программы.
 * <p>
 * Команда завершает выполнение программы, установив флаг {@code process} в {@code false}.
 * </p>
 */
public class CommandExit extends Command {

    /**
     * Выполняет команду завершения программы.
     * <p>
     * При вызове этой команды программа выводит сообщение о завершении и устанавливает флаг {@code process} в {@code false},
     * что прекращает выполнение основного цикла программы.
     * </p>
     * 
     * @param args Массив аргументов для команды. Эта команда не требует аргументов.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 0) {
            System.out.println("Данная команда не должна содержать аргументов.");
        } else {
            System.out.println("Программа завершена.");
            Main.process = false;
        }
    }
}
