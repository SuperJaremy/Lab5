package Lab.Program.Commands;

import java.util.Scanner;

/**
 * Интерфейс для общения с пользователем с возможностью выхода из диалога
 */
public interface DialogBox {
    int isValid(String s);
    default int chat(Scanner input) throws ExitException{
        System.out.println("Пожалуйста, введите exit, чтобы прервать выполнение команды");
        String line=input.nextLine();
        line=line.trim();
        if(line.equals("exit")){
            throw new ExitException();
        }
        else
            return isValid(line);
    }
}
