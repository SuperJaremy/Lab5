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
    public void act(Work work) throws ExitException {
        if(work.getElement()!=null) {
            try {
                int i = Integer.parseInt(work.getElement());
                if(SearchID(work.vector,i)!=null) {
                    SearchID(work.vector, i).fromConsole();
                    System.out.println("Информация обновлена");
                }
                else{
                    System.out.println("Элемент с id "+i+" не был найден");
                    throw new NullPointerException();
                }
            }
            catch(NumberFormatException e){
                System.out.println("Аргументом команды "+name+" должно быть целое число");
                describe();
                throw new NullPointerException();
            }
        }
        else{
            System.out.println("У команды "+name+" должны быть аргументы");
            describe();
            throw new NullPointerException();
        }
    }
}
