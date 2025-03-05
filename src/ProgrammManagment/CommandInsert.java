package ProgrammManagment;

import HelperClasses.InputReader;
import entity.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс, реализующий команду для добавления нового фильма в коллекцию.
 * <p>
 * Команда запрашивает у пользователя данные о фильме, такие как название, координаты, количество Оскаров, жанр, рейтинг, 
 * данные оператора и другие характеристики, а затем добавляет фильм в коллекцию по указанному ключу.
 * </p>
 */
public class CommandInsert extends Command {
    
    /** Множество для хранения использованных паспортных ID операторов */
    public static Set<String> usedPassportIDs = new HashSet<>(); 
    
    /**
     * Выполняет команду добавления фильма в коллекцию.
     * <p>
     * Команда требует один аргумент — ключ, по которому будет добавлен фильм.
     * Если элемент с таким ключом уже существует, выводится ошибка.
     * Затем программа запрашивает данные о фильме и добавляет его в коллекцию.
     * </p>
     *
     * @param args Массив аргументов для команды. Ожидается 1 аргумент — ключ элемента.
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Ошибка: Данная команда должна содержать 1 аргумент (ключ элемента).");
            return;
        }

        try {
            Long key = Long.parseLong(args[0]);

            if (MovieCollection.getMovies().containsKey(key)) {
                System.out.println("Ошибка: Элемент с таким ключом уже существует.");
                return;
            }

            InputReader inputReader = new InputReader();

            String name = null;
            while (name == null || name.trim().isEmpty()) {
                System.out.print("Введите название фильма (не может быть пустым): ");
                name = inputReader.readString().trim();
                if (name.isEmpty()) {
                    System.out.println("Ошибка: Название фильма не может быть пустым!");
                }
            }

            Double x = null;
            while (x == null || x > 61) {
                System.out.print("Введите координату X (число, максимум 61): ");
                String xField = inputReader.readString();
                try {
                    x = Double.parseDouble(xField);
                    if (x > 61) {
                        System.out.println("Ошибка: X не может превышать 61!");
                        x = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: X должен быть числом.");
                }
            }

            Long y = null;
            while (y == null) {
                System.out.print("Введите координату Y (целое число): ");
                String yField = inputReader.readString();
                try {
                    y = Long.parseLong(yField);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Y должен быть целым числом.");
                }
            }

            Integer oscarsCount = null;
            while (oscarsCount == null || oscarsCount <= 0) {
                System.out.print("Введите количество Оскаров (целое число, больше 0): ");
                String oscarsField = inputReader.readString();
                try {
                    oscarsCount = Integer.parseInt(oscarsField);
                    if (oscarsCount <= 0) {
                        System.out.println("Ошибка: Количество Оскаров должно быть больше 0!");
                        oscarsCount = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Количество Оскаров должно быть целым числом.");
                }
            }

            MovieGenre genre = null;
            while (true) {
                System.out.print("Введите жанр фильма (ACTION, MUSICAL, TRAGEDY) или оставьте пустым: ");
                String genreField = inputReader.readString().trim();
                if (genreField.isEmpty()) {
                    break;
                }
                try {
                    genre = MovieGenre.valueOf(genreField.toUpperCase());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: Неверный жанр. Допустимые значения: ACTION, MUSICAL, TRAGEDY.");
                }
            }

            MpaaRating mpaaRating = null;
            while (true) {
                System.out.print("Введите MPAA рейтинг (G, PG, PG_13, R, NC_17) или оставьте пустым: ");
                String ratingField = inputReader.readString().trim();
                if (ratingField.isEmpty()) {
                    break;
                }
                try {
                    mpaaRating = MpaaRating.valueOf(ratingField.toUpperCase());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: Неверный MPAA рейтинг. Допустимые значения: G, PG, PG_13, R, NC_17.");
                }
            }

            String operatorName = null;
            while (operatorName == null || operatorName.trim().isEmpty()) {
                System.out.print("Введите имя оператора (не может быть пустым): ");
                operatorName = inputReader.readString().trim();
                if (operatorName.isEmpty()) {
                    System.out.println("Ошибка: Имя оператора не может быть пустым!");
                }
            }

            LocalDateTime operatorBirthday = null;
            System.out.print("Введите дату рождения оператора (YYYY-MM-DDTHH:MM:SS) или оставьте пустым: ");
            String birthdayField = inputReader.readString();
            if (!birthdayField.isEmpty()) {
                try {
                    operatorBirthday = LocalDateTime.parse(birthdayField);
                } catch (Exception e) {
                    System.out.println("Ошибка: Некорректный формат даты. Используйте YYYY-MM-DDTHH:MM:SS.");
                }
            }

            Integer operatorHeight = null;
            while (operatorHeight == null || operatorHeight <= 0) {
                System.out.print("Введите рост оператора (целое число, больше 0): ");
                String heightField = inputReader.readString();
                try {
                    operatorHeight = Integer.parseInt(heightField);
                    if (operatorHeight <= 0) {
                        System.out.println("Ошибка: Рост должен быть больше 0!");
                        operatorHeight = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Рост должен быть целым числом.");
                }
            }

            Integer operatorWeight = null;
            while (operatorWeight == null || operatorWeight <= 0) {
                System.out.print("Введите вес оператора (целое число, больше 0): ");
                String weightField = inputReader.readString();
                try {
                    operatorWeight = Integer.parseInt(weightField);
                    if (operatorWeight <= 0) {
                        System.out.println("Ошибка: Вес должен быть больше 0!");
                        operatorWeight = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Вес должен быть целым числом.");
                }
            }

            String operatorPassportID = null;
            while (operatorPassportID == null || operatorPassportID.trim().isEmpty() || usedPassportIDs.contains(operatorPassportID)) {
                System.out.print("Введите паспорт ID оператора (уникальный, не может быть пустым): ");
                operatorPassportID = inputReader.readString().trim();
                if (operatorPassportID.isEmpty()) {
                    System.out.println("Ошибка: Паспорт ID не может быть пустым!");
                } else if (usedPassportIDs.contains(operatorPassportID)) {
                    System.out.println("Ошибка: Такой паспорт ID уже используется!");
                }
            }
            usedPassportIDs.add(operatorPassportID);

            Coordinates coordinates = new Coordinates(x, y);
            Person operator = new Person(operatorName, operatorBirthday, operatorHeight, operatorWeight, operatorPassportID);
            Movie movie = new Movie(name, coordinates, LocalDateTime.now(), oscarsCount, genre, mpaaRating, operator);

            MovieCollection.getMovies().put(key, movie);
            System.out.println("Фильм успешно добавлен в коллекцию!");

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Ключ должен быть целым числом.");
        }
    }
}
