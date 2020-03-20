package Lab.Program.Commands;

import Lab.Program.MusicBand;
import Lab.Program.Work;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.InputMismatchException;

/**
 * Добавляет элемент из файла в коллекцию, если он больше остальных
 */
public class FileAddIfMax extends AddIfMax {
    @Override
    public void act(Work work){
        try{
            BufferedReader reader =work.getReader();
            MusicBand mb = new MusicBand();
            mb.fromFile(reader);
            if(work.vector.size()!=0) {
                if (mb.compareTo(Collections.max(work.vector)) == 1) {
                    work.vector.add(mb);
                }
            }
            else
                work.vector.add(mb);
        }catch (IOException| NullPointerException| InputMismatchException | ParseException | IllegalArgumentException e){
            throw new NullPointerException();
        }
    }
}
