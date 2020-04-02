package Lab.Program.Commands;

import Lab.Program.MusicBand;
import Lab.Program.Work;

/**
 * Выводит сумму знчаений numberOfParticipants всех элементов коллекции
 */
public class SumOfNumberOfParticipants extends Command {
    private final String name="sum_of_number_of_participants";
    private final String description="вывести сумму значений поля numberOfParticipants для всех эллементов коллекции";
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
            long sum = 0;
            for (MusicBand i : work.vector)
                if(!(i.getNumberOfParticipants()==null))
                    sum += i.getNumberOfParticipants();

            System.out.println(sum);
        }
        else {
            System.out.println("У команды "+name+" не должно быть аргументов");
            describe();
            throw new NullPointerException();
        }
    }
}
