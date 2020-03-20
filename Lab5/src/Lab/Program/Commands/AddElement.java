package Lab.Program.Commands;

import Lab.Program.MusicBand;
import Lab.Program.Work;

/**
 * Добавляет элемент в коллекцию из командной строки
 */
public class AddElement extends Command {
    protected final String name="add";
    protected final String description="добавить новый элемент в коллекцию";
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
        if(work.vector.size()<100000000) {
            MusicBand musicBand = new MusicBand(work.getElement());
            work.vector.add(musicBand);
            System.out.println("Объект добавлен в список");
        }
        else
            System.out.println("Слишком много элементов в коллекции");
    }
}
