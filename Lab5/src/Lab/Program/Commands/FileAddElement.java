package Lab.Program.Commands;

import Lab.Program.FileTester;
import Lab.Program.MusicBand;
import Lab.Program.Work;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.InputMismatchException;

/**
 * Добавляет файл в коллекцию из построчного представления полей
 */
public class FileAddElement extends AddElement {
    @Override
    public void act(Work work) throws NullPointerException {
        if(work.getElement()==null) {
            if(work.vector.size()<Integer.MAX_VALUE) {
                MusicBand mb = new MusicBand();
                work.setCurrentLine(mb.fromFile(work.getContents(), work.getCurrentLine() + 1));
                work.vector.add(mb);
            }
            else{
                System.out.println("Слишком много элементов в коллекции");
                throw new NullPointerException();
            }
        }
        else {
            System.out.println("У команды "+name+" не должно быть аргументов в этой же строке");
            System.out.println("Значения полей объекта прописываются по одному в строке");
            describe();
            throw new NullPointerException();
        }
    }
}
