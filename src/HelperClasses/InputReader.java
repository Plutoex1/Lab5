package HelperClasses;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Класс для чтения ввода с консоли или из файла.
 * <p>
 * Этот класс предоставляет методы для чтения строк, целых чисел и чисел с плавающей запятой
 * из консоли или из указанного файла. Также поддерживает безопасное закрытие ресурсов.
 * </p>
 */
public class InputReader {
    private BufferedReader reader;

    /**
     * Конструктор для чтения ввода с консоли.
     */
    public InputReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Конструктор для чтения ввода из файла.
     *
     * @param filePath Путь к файлу, из которого будет происходить чтение.
     * @throws IOException Если файл не найден или произошла ошибка при открытии файла.
     */
    public InputReader(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * Чтение строки с ввода.
     *
     * @return Строка, введенная пользователем.
     */
    public String readString() {
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            System.err.println("Ошибка чтения ввода: " + e.getMessage());
            return null;
        }
    }

    /**
     * Чтение целого числа с ввода.
     * В случае некорректного ввода будет запрашиваться ввод до тех пор, пока не будет введено корректное значение.
     *
     * @return Целое число, введенное пользователем.
     */
    public int readInt() {
        while (true) {
            try {
                String input = readString();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("Некорректный ввод. Пожалуйста, введите целое число.");
            }
        }
    }

    /**
     * Чтение числа с плавающей запятой с ввода.
     * В случае некорректного ввода будет запрашиваться ввод до тех пор, пока не будет введено корректное значение.
     *
     * @return Число с плавающей запятой, введенное пользователем.
     */
    public double readDouble() {
        while (true) {
            try {
                String input = readString();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.err.println("Некорректный ввод. Пожалуйста, введите число с плавающей запятой.");
            }
        }
    }

    /**
     * Закрытие ресурса BufferedReader.
     * Освобождает все ресурсы, связанные с потоком ввода.
     */
    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии считывателя: " + e.getMessage());
        }
    }
}
