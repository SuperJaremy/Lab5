package Lab.Program.Commands;

import Lab.Program.Work;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Выполняет скрипт из заданного файла
 */
public class ExecuteScript extends Command{
    private final String name="execute_script";
    private final String description = "считать и исполнить скрипт из указанного файла";
    @Override
    protected void describe() {
        System.out.println(name+" file_name: "+description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void act(Work work) throws NullPointerException {
        if(!work.getScripts().contains(Paths.get(work.getElement()).hashCode())) {
            work.getScripts().add(Paths.get(work.getElement()).hashCode());
            ScriptWork innerWork = new ScriptWork(work.vector, work.date, work.getPathOfJson().toString(), work);
            innerWork.start();
            work.setInProcess(innerWork.getInProcess());
            if(innerWork.getInProcess()) {
                System.out.println("Скрипт " + innerWork.getPathOfScript() + " выполнен");
            }
        }
        else {
            System.out.println("Рекурсия в скрипте:" + work.getPathOfScript());
            System.out.println("Вы хотите зайти в рекурсию? [Y/N]");
            boolean success = false;
            Scanner input = new Scanner(System.in);
            while (!success) {
                String line = input.nextLine().trim().toUpperCase();
                if (line.equals("Y")) {
                    success = true;
                    ScriptWork innerWork = new ScriptWork(work.vector, work.date, work.getPathOfJson().toString(), work);
                    innerWork.start();
                    work.setInProcess(innerWork.getInProcess());
                } else if (line.equals("N")) {
                    success = true;
                } else
                    System.out.println("Данные некорректны. Повторите ввод");
            }
        }
    }
}
