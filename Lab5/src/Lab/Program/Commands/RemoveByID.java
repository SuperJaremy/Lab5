package Lab.Program.Commands;

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
        long l= work.vector.size();
        work.vector.remove(SearchID(work.vector,Integer.parseInt(work.getElement())));
        if(work.vector.size()==l){
            System.out.println("Элемент был удалён");
        }
        else{
            throw new NullPointerException();
        }
    }
}
