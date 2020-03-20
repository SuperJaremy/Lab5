package Lab.Program.Commands;

import Lab.Program.Work;

/**
 * Заменяет элемент коллекции с заданным id
 */
public class UpdateID extends Command implements SearchID{
    protected final String name = "update_id";
    protected final String description = "обновить значение элемента коллекции, id которого равен заданному";
    @Override
    protected void describe() {
        System.out.println(name+" id: "+ description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void act(Work work) {
        int i=Integer.parseInt(work.getElement());
        SearchID(work.vector,i).fromConsole();
        System.out.println("Информация обновлена");
    }
}
