package Lab.Program.Commands;

import Lab.Program.MusicBand;
import Lab.Program.Work;

/**
 * Выводит в стандартный поток вывода все элменты коллекции
 */
public class Show extends Command {
    private final String name="show";
    private final String description="вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    @Override
    public void describe() {
        System.out.println(name+": "+description);
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void act(Work work)throws NullPointerException{
        if(work.getElement()!=null) {
            System.out.println("В команде "+name+" не должно быть аргументов");
            describe();
            throw new NullPointerException();
        }
        else {
            for (MusicBand i : work.vector) {
                System.out.println(i);
            }
            System.out.println("Конец списка");
        }
    }
}
