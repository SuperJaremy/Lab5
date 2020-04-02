package Lab.Program;


import Lab.Program.Commands.DialogBox;
import Lab.Program.Commands.ExitException;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class Coordinates {
    private Float x;
    private long y;
    Float getX(){
        return x;
    }

    /**
     * Создаёт класс Coordinates из стандартного потока ввода
     */
    public void fromConsole() throws ExitException {
        Scanner input = new Scanner(System.in);
        System.out.println("Пожалуйста, введите координату x");
        boolean success=false;
        while(!success) {
            DialogBox db = s -> {
                try {
                    x=Float.parseFloat(s.trim());
                    if(x<-801){
                        x=null;
                        throw new NullPointerException();
                    }
                    return 1;
                } catch (NullPointerException | NumberFormatException e) {
                    System.out.println("Координата x должны быть числом не меньше -801");
                    return -1;
                }
            };
            if(db.chat(input)>0)
                success=true;
        }
        success=false;
        System.out.println("Пожалуйста, введите координату y");
        while (!success){
            DialogBox db = s -> {
                try{
                    y=Long.parseLong(s.trim());
                    return 1;
                }
                catch (InputMismatchException|NullPointerException | NumberFormatException e){
                    System.out.println("Координата y должна быть целым числом");
                    return -1;
                }
            };
            if(db.chat(input)>0)
                success=true;
        }
    }

    /**
     * Создаёт класс Coordinates
     * @throws NullPointerException
     */
        public int fromFile(Vector<String> Contents, int i) throws NullPointerException{
            try {
                x = Float.parseFloat(Contents.get(i).trim());
                if (x < -801) {
                    System.out.println("Координата x должна быть не меньше -801");
                    throw new NullPointerException();
                }
            }catch (NumberFormatException e){
                System.out.println("Координата x должна быть числом");
                throw new NullPointerException();
            }
            try {
                y = Long.parseLong(Contents.get(i + 1).trim());
            }
            catch(NumberFormatException e){
                System.out.println("Координата y должна быть целым числом");
            }
            return i+1;
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
