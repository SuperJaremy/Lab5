package Lab;


import Lab.Program.MusicBand;
import Lab.Program.Work;

import java.time.LocalDate;
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
        LocalDate initializingDate= LocalDate.now();
        Work work;
        String path = Arrays.stream(args).collect(Collectors.joining(" "));
        work = new Work(Omega, initializingDate, path);
        work.start();
    }
}
