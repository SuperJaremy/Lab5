package Lab.Program.Commands;

import Lab.Program.MusicBand;
import Lab.Program.Work;

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
        if(work.getElement()==null) {
                MusicBand mb = new MusicBand();
                work.setCurrentLine(mb.fromFile(work.getContents(),work.getCurrentLine()+1));
                if (work.vector.size() != 0) {
                    if (mb.compareTo(Collections.max(work.vector)) > 0) {
                        work.vector.add(mb);
                    }
                } else
                    work.vector.add(mb);
        }
        else{
            System.out.println("У команды "+name+" не должно быть аргументов в этой же строке");
            describe();
            throw new NullPointerException();
        }
    }
}
