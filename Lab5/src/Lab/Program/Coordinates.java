package Lab.Program;





import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Coordinates {
    private Float x;
    private long y;
    Float getX(){
        return x;
    }

    /**
     * Создаёт класс Coordinates из стандартного потока ввода
     */
    public void fromConsole(){
        Scanner input = new Scanner(System.in);
        System.out.println("Пожалуйста, введите координату x");
        boolean success=false;
        while(!success) {
            try {
                x=Float.parseFloat(input.nextLine().trim());
                if(x<-801){
                    x=null;
                    throw new InputMismatchException();
                }
                success=true;
            } catch (InputMismatchException|NullPointerException | NumberFormatException e) {
                System.out.println("Данные некорректны. Повторите ввод ещё раз");
            }
        }
        success=false;
        System.out.println("Пожалуйста, введите координату y");
        while (!success){
            try{
                y=Long.parseLong(input.nextLine().trim());
                success=true;
            }
            catch (InputMismatchException|NullPointerException | NumberFormatException e){
                System.out.println("Данные некорректны. Повторите ввод ещё раз");
            }
        }
    }

    /**
     * Создаёт класс Coordinates из потока BufferedReader reader
     * @param reader
     * @throws InputMismatchException
     * @throws NullPointerException
     * @throws NumberFormatException
     * @throws IOException
     */
        public void fromFile(BufferedReader reader) throws InputMismatchException, NullPointerException, NumberFormatException, IOException {
            x=Float.parseFloat(reader.readLine().trim());
            if(x<-801){
                throw new InputMismatchException();
            }
            y=Long.parseLong(reader.readLine().trim());
        }

    @Override
    public String toString() {
        return "x=" + x +
                "\n y=" + y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return y == that.y &&
                x.equals(that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
