package Lab.Program.Commands;

import Lab.Program.Work;

import java.text.SimpleDateFormat;

/**
 * Выводит информацию о коллекции в стандартный поток вывода
 */
public class Info extends Command {
    private final String name="info";
    private final String description = "вывести в стандартный поток вывода информацию о коллекции";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void describe() {
        System.out.println(name+": "+description);
    }
    @Override
    public void act(Work work) throws NullPointerException{
        if(work.getElement()==null) {
            SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
            System.out.println("Тип коллекции: " + work.vector.getClass());
            System.out.println("Время инициализации: " + sdf.format(work.date));
            System.out.println("Количество элементов: " + work.vector.size());
        }
        else {
            System.out.println("У команды "+name+" не должно быть аргументов");
            describe();
            throw new NullPointerException();
        }
    }
}
