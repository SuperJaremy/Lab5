package Lab.Program.Commands;

import Lab.Program.Work;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;

/**
 * Заменяет элемент с заданным id элементом из файла
 */
public class FileUpdateID extends UpdateID implements SearchID{
    @Override
    public void act(Work work){
        try{
            BufferedReader reader =work.getReader();
            int i=Integer.parseInt(work.getElement());
            SearchID(work.vector,i).fromFile(reader);
        }catch (IOException| NullPointerException| InputMismatchException| ParseException| IllegalArgumentException e){
            throw new NullPointerException();
        }
    }
}
