package Lab.Program.Commands;

import Lab.Program.Work;

/**
 * Завершает выполнение программы
 */
public class Exit extends Command {
    private final String name = "exit";
    private final String description="завершить программу";
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
            work.setInProcess(false);
            System.out.println("Выход из программы");
        }
        else {
            System.out.println("В команде не должно быть аргументов");
            throw new NullPointerException();
        }
    }
}
