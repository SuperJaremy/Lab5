package Lab.Program;




import Lab.Program.Commands.DialogBox;
import Lab.Program.Commands.ExitException;

import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

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
    public void fromConsole() throws ExitException {
        Scanner input = new Scanner(System.in);
        System.out.println("Пожалуйста, введите название альбома");
        DialogBox db = s -> {
            if(s.length()==0){
                name=null;
            }
            else {
                name=s;
            }
            return 1;
        };
        try {
            db.chat(input);
        }
        catch (ExitException e){
            System.out.println("Введите Y, если это название альбома.");
            DialogBox db1= s->{
                if(s.toUpperCase().equals("Y")){
                    name=s;
                    return 1;
                }
                else{
                    System.out.println("Повторите ввод ещё раз");
                    return -1;
                }
            };
            boolean b=false;
            while(!b) {
                if (db1.chat(input)>0) {
                    b = true;
                }
            }
        }
        boolean success;
        if (name != null) {
            System.out.println("Пожалуйста, введите длительность альбома");
            success = false;
            while (!success) {
                DialogBox db1 = s -> {
                    try {
                        length = Long.parseLong(s.trim());
                        if (length <= 0)
                            throw new NullPointerException();
                        return 1;
                    } catch (NullPointerException | NumberFormatException e1) {
                        System.out.println("Длительность альбома должна быть целым положительным числом");
                        return -1;
                    }
                };
                if(db1.chat(input)>0)
                    success=true;
            }
        }
    }

    /**
     * Создаёт класс Album
     * @throws NullPointerException
     */
    public int fromFile(Vector<String> Contents, int i) throws NullPointerException{
        name = Contents.get(i).trim();
        if (name.length() == 0) {
            name = null;
        }
        if (name != null) {
            try {
                length = Long.parseLong(Contents.get(i + 1).trim());
                if (length <= 0) {
                    System.out.println("Длина альбома не может быть неположительной");
                    throw new NullPointerException();
                }
                return i+1;
            }
            catch(NumberFormatException e){
                System.out.println("Длина альбома должна быть целым числом");
                throw new NullPointerException();
            }
        }
        return i;
    }
}

