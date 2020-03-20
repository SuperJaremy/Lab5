package Lab.Program.Commands;

import Lab.Program.Work;

/**
 * Выводит справку в стандартный поток вывода
 */
public class Help extends Command {
    private final String name="help";
    private final String description="вывести справку по доступным командам";
    @Override
    public String getName(){
        return name;
    }
    @Override
    public void describe(){
        System.out.println(name+": "+description);
    }
    @Override
    public void act(Work work) throws NullPointerException{
        if(work.getElement()==null)
            for(Command i : work.getCommands().values()){
            i.describe();
            }
        else {
            System.out.println("В команде не должно быть аргументов");
            throw new NullPointerException();
        }
    }
}
