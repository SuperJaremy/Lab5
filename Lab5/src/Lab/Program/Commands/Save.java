package Lab.Program.Commands;

import Lab.Program.FileTester;
import Lab.Program.Hell.MusicBandSerializer;
import Lab.Program.MusicBand;
import Lab.Program.Work;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;

/**
 * Записывает коллекцию в изначальный json файл
 */
public class Save extends Command {
    private final String name="save";
    private final String description="сохранить коллекцию в файл";
    @Override
    protected void describe() {
        System.out.println(name+": "+description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void act(Work work) throws NullPointerException {
        if(work.getElement()==null) {
            if (FileTester.TestFileToWrite(work.getPathOfJson())) {
                Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
                        .registerTypeAdapter(MusicBand.class, new MusicBandSerializer()).create();
                try(BufferedWriter writer= new BufferedWriter(new FileWriter(work.getPathOfJson().toFile())))  {
                    gson.toJson(work.vector, writer);
                    writer.flush();
                    System.out.println("Коллекция сохранена");
                } catch (IOException e) {
                    System.out.println("Файл не может быть открыт.");
                }
            } else {
                throw new NullPointerException();
            }
        }
        else {
            System.out.println("У команды "+name+ " не должно быть аргументов");
            describe();
            throw new NullPointerException();
        }
    }
}
