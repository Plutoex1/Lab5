package entity;

import java.util.Objects;

/**
 * Класс, представляющий координаты с двумя полями: x и y.
 * 
 * <p>Поле x должно быть числом с плавающей точкой (Double) и не может быть больше 61.
 * Поле y - целое число (long).</p>
 * 
 * <p>Пример использования:</p>
 * <pre>
 *     Coordinates coordinates = new Coordinates(50.0, 100);
 * </pre>
 */
public class Coordinates {
    
    /** Координата X, не может быть null и должна быть меньше или равна 61. */
    private Double x;
    
    /** Координата Y, целое число (long). */
    private long y;

    /**
     * Конструктор для создания объекта Coordinates с заданными значениями x и y.
     * 
     * @param x значение для координаты X. Не может быть null и должно быть меньше или равно 61.
     * @param y значение для координаты Y (целое число).
     * @throws IllegalArgumentException если x равно null или x больше 61.
     */
    public Coordinates(Double x, long y) {
        setX(x);
        setY(y);
    }

    /**
     * Возвращает значение координаты X.
     * 
     * @return значение координаты X.
     */
    public Double getX() {
        return x;
    }

    /**
     * Возвращает значение координаты Y.
     * 
     * @return значение координаты Y.
     */
    public long getY() {
        return y;
    }

    /**
     * Устанавливает значение координаты X.
     * 
     * @param x значение для координаты X.
     * @throws IllegalArgumentException если x равно null или больше 61.
     */
    public void setX(Double x) {
        if (x == null) {
            throw new IllegalArgumentException("x cannot be null");
        }
        if (x > 61) {
            throw new IllegalArgumentException("x must be less than or equal to 61");
        }
        this.x = x;
    }

    /**
     * Устанавливает значение координаты Y.
     * 
     * @param y значение для координаты Y.
     */
    public void setY(long y) {
        this.y = y;
    }

    /**
     * Проверяет, равны ли текущие координаты переданному объекту.
     * 
     * @param o объект для сравнения с текущим объектом.
     * @return {@code true}, если объекты равны, иначе {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return y == that.y && x.equals(that.x);
    }

    /**
     * Возвращает хэш-код для текущего объекта.
     * 
     * @return хэш-код текущего объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Возвращает строковое представление объекта Coordinates в виде:
     * <pre>
     *     Coordinates{x=<значение x>, y=<значение y>}
     * </pre>
     * 
     * @return строковое представление объекта.
     */
    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
