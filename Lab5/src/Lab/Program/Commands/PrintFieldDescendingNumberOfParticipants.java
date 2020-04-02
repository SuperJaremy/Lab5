package Lab.Program.Commands;

import Lab.Program.MusicBand;
import Lab.Program.Work;

import java.util.Collections;
import java.util.Vector;

/**
 * Выводит в стандартный поток вывода значения поля numberOfParticipants
 */
public class PrintFieldDescendingNumberOfParticipants extends Command {
    private final String name="print_field_descending_number_of_participants";
    private final String description="вывести значения поля numberOfParticipants в порядке убывания";
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
            Vector<MusicBand> V = work.vector;
            V.sort(Collections.reverseOrder());
            for (MusicBand i : V) {
                if(!(i.getNumberOfParticipants()==null))
                    System.out.println(i.getNumberOfParticipants());
            }
            System.out.println("Все значения выведены");
        }
        else {
            System.out.println("У команды "+name+ " не должно быть аргументов");
            describe();
            throw new NullPointerException();
        }
    }
}
