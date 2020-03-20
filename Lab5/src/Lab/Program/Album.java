package Lab.Program;



import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Album {
    private String name;
    private long length=-1;
    String getName(){
        return name;
    }
    long getLength(){
        return length;
    }

    @Override
    public String toString() {
        return "Название альбома: " + name +
                "\n Длина альбома: " + length;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return length == album.length &&
                name.equals(album.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length);
    }

    /**
     * Создаёт класса Album из стандартного потока ввода
     */
    public void fromConsole() {
        Scanner input = new Scanner(System.in);
        System.out.println("Пожалуйста, введите название альбома");
        boolean success;
        name = input.nextLine().trim();
        if (name.length() == 0) {
            name = null;
        }
        if (name != null) {
            System.out.println("Пожалуйста, введите длительность альбома");
            success = false;
            while (!success) {
                try {
                    length = Long.parseLong(input.nextLine().trim());
                    if (length <= 0)
                        throw new InputMismatchException();
                    success = true;
                } catch (InputMismatchException | NullPointerException | NumberFormatException e1) {
                    System.out.println("Данные некорректны. Повторите ввод ещё раз");
                }
            }
        }
    }

    /**
     * Создаёт класс Album из потока Buffered reader reader
     * @param reader
     * @throws IOException
     * @throws NullPointerException
     * @throws InputMismatchException
     */
    public void fromFile(BufferedReader reader) throws IOException, NullPointerException, InputMismatchException {
        name = reader.readLine().trim();
        if (name.length() == 0) {
            name = null;
        }
        if (name != null) {
            length = Long.parseLong(reader.readLine().trim());
            if (length <= 0)
                throw new InputMismatchException();
        }
    }
}

