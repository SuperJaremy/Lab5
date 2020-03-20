package Lab.Program.Commands;

import Lab.Program.Work;

/**
 * Удаляет все элементы коллекции
 */
public class Clear  extends Command{
    private final String description="очистить коллекцию";
    private final String name="clear";
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
        if(work.getElement()!=null) {
            System.out.println("В команде не должно быть аргументов");
            throw new NullPointerException();
        }
        else {
            work.vector.removeAllElements();
            System.out.println("Список очищен");
        }
    }
}
