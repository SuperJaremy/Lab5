package Lab;



import Lab.Program.Commands.ExitException;
import Lab.Program.MusicBand;
import Lab.Program.Work;


import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Класс Main
 */
public class Main {

    public static void main(String[] args) {
	// write your code here
        Vector<MusicBand>Omega=new Vector<>();
        String path = Arrays.stream(args).collect(Collectors.joining(" ")).trim();
        Work work = new Work(Omega, path);
        try {
            work.start();
        }
        catch (ExitException e){
            System.out.println("Это очень плохо. Программа умирант");
        }
    }
}
