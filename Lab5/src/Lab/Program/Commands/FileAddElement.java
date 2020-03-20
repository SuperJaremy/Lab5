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
 * Добавляет элемент из файла в коллекцию
 */
public class FileAddElement extends AddElement {
    @Override
    public void act(Work work) {
        if (FileTester.TestFileToRead(work.getPathOfScript())) {
            try {
                BufferedReader reader =work.getReader();
                MusicBand mb = new MusicBand();
                mb.fromFile(reader);
                mb.setName(work.getElement());
                work.vector.add(mb);
            }catch (IOException| NullPointerException| InputMismatchException| ParseException| IllegalArgumentException e){
                throw new NullPointerException();
            }
        }
    }
}
