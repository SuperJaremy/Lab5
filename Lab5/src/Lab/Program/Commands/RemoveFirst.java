package Lab.Program.Commands;

import Lab.Program.Work;

/**
 * Удаляет первый элемент из коллекции
 */
public class RemoveFirst extends Command {
    private final String name="remove_first";
    private final String description="удалить первый элемент из коллекции";
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
        if(work.getElement() == null) {
            if (work.vector.size() != 0) {
                work.vector.remove(0);
                System.out.println("Элемент удалён");
            } else {
                System.out.println("В коллекции нет элементов");
                throw new NullPointerException();
            }
        }
        else {
            System.out.println("В команде не должно быть аргументов");
            throw new NullPointerException();
        }
    }
}
