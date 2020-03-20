package Lab.Program.Commands;

import Lab.Program.FileTester;
import Lab.Program.Hell.MusicBandSerealizer;
import Lab.Program.MusicBand;
import Lab.Program.Work;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
                Gson gson = new GsonBuilder().setPrettyPrinting()
                        .registerTypeAdapter(MusicBand.class, new MusicBandSerealizer()).create();
                try (Writer writer = new FileWriter(work.getPathOfJson().toFile())) {
                    writer.write(gson.toJson(work.vector));
                } catch (IOException e) {
                    System.out.println("Файл не может быть открыт.");
                }
            } else {
                throw new NullPointerException();
            }
        }
        else {
            System.out.println("В команде не должно быть аргументов");
            throw new NullPointerException();
        }
    }
}
