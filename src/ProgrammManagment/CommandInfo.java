package ProgrammManagment;

import entity.MovieCollection;

/**
 * Класс, реализующий команду, которая выводит информацию о коллекции фильмов.
 * <p>
 * Команда предоставляет информацию о дате инициализации коллекции, типе коллекции и количестве элементов в ней.
 * </p>
 */
public class CommandInfo extends Command {
    
    /**
     * Выполняет команду, выводя информацию о коллекции фильмов.
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
            System.out.println("Информация о коллекции:");
            System.out.println("Дата инициализации: " + MovieCollection.getInitializationdate());
            System.out.println("Тип коллекции: " + MovieCollection.movies.getClass().getSimpleName());
            System.out.println("Количество элементов: " + MovieCollection.movies.size());
        }
    }
}
