package Lab.Program.Commands;

import Lab.Program.MusicBand;
import Lab.Program.Work;

import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Добавления элемент в коллекцию из консоли, если он больше всех остальных элементов
 */
public class AddIfMax extends Command {
    protected final String name="add_if_max";
    protected final String description="добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    @Override
    protected void describe() {
        System.out.println(name+" {element}: "+description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void act(Work work) {
        MusicBand musicBand = new MusicBand();
        musicBand.fromConsole();
        try {
            if (musicBand.compareTo(Collections.max(work.vector)) == 1) {
                work.vector.add(musicBand);
                System.out.println("Элемент добавлен в список");
            } else
                System.out.println("Элемент не добавлен в список");
        }catch (NoSuchElementException e){
            work.vector.add(musicBand);
            System.out.println("Элемент добавлен в список");
        }
    }
}
