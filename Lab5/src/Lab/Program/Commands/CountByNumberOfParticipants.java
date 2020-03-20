package Lab.Program.Commands;

import Lab.Program.MusicBand;
import Lab.Program.Work;

/**
 * Считает количество элементов коллекции, значение поля numberOfParticipants, которых равно заданному
 */
public class CountByNumberOfParticipants extends Command {
    private final String name="count_by_number_of_participants";
    private final String description="вывести количество элементов, значения поля numberOfParticipants которых равно заданному";
    @Override
    protected void describe() {
        System.out.println(name+" numberOfParticipants: "+description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void act(Work work) {
        int i=Integer.parseInt(work.getElement());
        long count=0;
        for(MusicBand j:work.vector)
            if(j.getNumberOfParticipants()==i)
                count++;
        System.out.println(count);
    }
}
