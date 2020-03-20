package Lab.Program.Commands;

import Lab.Program.Work;

import java.util.Queue;

/**
 * Выводит в стандарнтный поток вывода 8 последних команд с их описанием
 */
public class History extends Command{
    private final String name="history";
    private final String description="вывести последние 8 команд";
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
            Queue<Command> h = work.getHistory();
            for (Command i : h) {
                i.describe();
            }
            System.out.println("Конец истории");
        }
        else {
            System.out.println("В команде не должно быть аргументов");
            throw new NullPointerException();
        }
    }
}
