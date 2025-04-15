package ProgrammManagment;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import HelperClasses.InputReader;
import entity.MovieGenre;
import entity.MpaaRating;

public class InputChecker {
	
	public static Set<String> usedPassportIDs = new HashSet<>();

	public Object[] readMovieInput() {
        InputReader inputReader = new InputReader();

        String name = null;
        while (name == null || name.trim().isEmpty()) {
            System.out.print("Введите название фильма (не может быть пустым): ");
            name = inputReader.readString().trim();
            if (name.isEmpty()) System.out.println("Ошибка: Название не может быть пустым.");
        }

        Double x = null;
        while (x == null || x > 61) {
            System.out.print("Введите координату X (число, максимум 61): ");
            try {
                x = Double.parseDouble(inputReader.readString());
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
            System.out.print("Введите количество Оскаров: ");
            try {
                oscarsCount = Integer.parseInt(inputReader.readString());
                if (oscarsCount <= 0) {
                    System.out.println("Ошибка: должно быть больше 0");
                    oscarsCount = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: целое число.");
            }
        }

        MovieGenre genre = null;
        while (true) {
            System.out.print("Введите жанр (ACTION, MUSICAL, TRAGEDY) или оставьте пустым: ");
            String input = inputReader.readString().trim();
            if (input.isEmpty()) break;
            try {
                genre = MovieGenre.valueOf(input.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: неверный жанр.");
            }
        }

        MpaaRating rating = null;
        while (true) {
            System.out.print("Введите MPAA рейтинг (G, PG, PG_13, R, NC_17) или оставьте пустым: ");
            String input = inputReader.readString().trim();
            if (input.isEmpty()) break;
            try {
                rating = MpaaRating.valueOf(input.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: неверный рейтинг.");
            }
        }

        String operatorName = null;
        while (operatorName == null || operatorName.trim().isEmpty() || !operatorName.matches("^[а-яА-Яa-zA-Z\\s]+$")) {
            System.out.print("Введите имя оператора: ");
            operatorName = inputReader.readString().trim();
            if (operatorName.isEmpty() || !operatorName.matches("^[а-яА-Яa-zA-Z\\s]+$")) {
                System.out.println("Ошибка: Имя некорректное.");
            }
        }

        LocalDateTime birthday = null;
        while (true) {
            System.out.print("Введите дату рождения (YYYY-MM-DDTHH:MM:SS) или оставьте пустым: ");
            String input = inputReader.readString().trim();
            if (input.isEmpty()) break;
            try {
                birthday = LocalDateTime.parse(input);
                if (birthday.isAfter(LocalDateTime.now())) {
                    System.out.println("Ошибка: Дата не может быть в будущем.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Ошибка: неверный формат даты.");
            }
        }

        Integer height = null;
        while (true) {
            System.out.print("Введите рост оператора: ");
            try {
                height = Integer.parseInt(inputReader.readString().trim());
                if (height > 0) break;
                else System.out.println("Ошибка: должно быть > 0");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: должно быть целым числом.");
            }
        }

        Integer weight = null;
        while (true) {
            System.out.print("Введите вес оператора: ");
            try {
                weight = Integer.parseInt(inputReader.readString().trim());
                if (weight > 0) break;
                else System.out.println("Ошибка: должно быть > 0");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: должно быть числом.");
            }
        }

        String passportID = null;
        while (passportID == null || passportID.trim().isEmpty() || usedPassportIDs.contains(passportID)) {
            System.out.print("Введите паспорт ID: ");
            passportID = inputReader.readString().trim();
            if (passportID.isEmpty()) {
                System.out.println("Ошибка: не может быть пустым.");
            } else if (usedPassportIDs.contains(passportID)) {
                System.out.println("Ошибка: уже используется.");
            }
        }
        usedPassportIDs.add(passportID);

        return new Object[] {
            name, x, y, oscarsCount, genre, rating,
            operatorName, birthday, height, weight, passportID
        };
	}
}
