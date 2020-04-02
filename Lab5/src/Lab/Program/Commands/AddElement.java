package Lab.Program.Commands;

import Lab.Program.MusicBand;
import Lab.Program.Work;

/**
 * Добавляет элемент в коллекцию из консоли
 */
public class AddElement extends Command {
    protected final String name="add";
    protected final String description="добавить новый элемент в коллекцию";
    @Override
    protected void describe() {
        System.out.println(name+": "+description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void act(Work work) throws NullPointerException, ExitException {
        if(work.getElement()==null) {
            if (work.vector.size() < Integer.MAX_VALUE) {
                MusicBand musicBand = new MusicBand();
                musicBand.fromConsole();
                work.vector.add(musicBand);
                System.out.println("Объект добавлен в список");
            } else
                System.out.println("Слишком много элементов в коллекции");
        }
        else {
            System.out.println("У команды "+name+ " не должно быть аргументов в этой же строке");
            System.out.println("Введите Enter после ввода команды для получения интсрукции");
            describe();
            throw new NullPointerException();
        }
    }
}
