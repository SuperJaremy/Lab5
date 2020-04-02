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
        System.out.println(name+": "+description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void act(Work work) throws ExitException {
        if(work.getElement()==null) {
            MusicBand musicBand = new MusicBand();
            musicBand.fromConsole();
            try {
                if (musicBand.compareTo(Collections.max(work.vector)) > 0) {
                    work.vector.add(musicBand);
                    System.out.println("Элемент добавлен в список");
                } else
                    System.out.println("Элемент не добавлен в список");
            } catch (NoSuchElementException e) {
                work.vector.add(musicBand);
                System.out.println("Элемент добавлен в список");
            }
        }
        else {
            System.out.println("У команды " + name + " не должно быть аргументов в этой же строке");
            describe();
            throw new NullPointerException();
        }
    }
}
