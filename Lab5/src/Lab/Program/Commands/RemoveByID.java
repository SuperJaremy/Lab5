package Lab.Program.Commands;

import Lab.Program.MusicBand;
import Lab.Program.Work;

/**
 * Удаляет элемент из коллекции по значению id
 */
public class RemoveByID extends Command implements SearchID {
    private final String name = "remove_by_id";
    private final String description = "удалить элемент из коллекции по его id";
    @Override
    protected void describe() {
        System.out.println(name+" id: "+description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void act(Work work) throws NullPointerException {
        if(work.getElement()!=null) {
            long l = work.vector.size();
            try {
                MusicBand mb=SearchID(work.vector, Integer.parseInt(work.getElement()));
                MusicBand.getIds().remove(mb.getId());
                work.vector.remove(mb);
            }
            catch(NumberFormatException e){
                System.out.println("Аргументом команды "+name+" должно быть целое число");
                throw new NullPointerException();
            }
            if (work.vector.size() != l) {
                System.out.println("Элемент был удалён");
            }
            else{
                System.out.println("Элемент с id "+work.getElement()+" не был найден");
                throw new NullPointerException();
            }
        }
        else{
            System.out.println("У команды "+name+" должен быть аргумент");
            describe();
            throw new NullPointerException();
        }
    }
}
