package Lab.Program.Commands;

import Lab.Program.FileTester;
import Lab.Program.Work;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Посторочно выполняет скрипт из заданного файла
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
    public void act(Work work) throws NullPointerException, ExitException {
        if (work.getElement() != null) {
            if (FileTester.TestFileToRead(Paths.get(work.getElement()))) {
                if (!work.getScripts().contains(Paths.get(work.getElement()).hashCode())) {
                    work.getScripts().add(Paths.get(work.getElement()).hashCode());
                    ScriptWork innerWork = new ScriptWork(work.vector, work.getPathOfJson().toString(), work);
                    innerWork.start();
                    work.setInProcess(innerWork.getInProcess());
                    if (innerWork.getInProcess()) {
                        System.out.println("Скрипт " + innerWork.getPathOfScript() + " выполнен");
                    }
                } else {
                    System.out.println("Рекурсия в скрипте:" + work.getPathOfScript());
                    System.out.println("Вы хотите зайти в рекурсию? [Y/N]");
                    boolean success = false;
                    Scanner input = new Scanner(System.in);
                    while (!success) {
                        DialogBox db = s -> {
                            if (s.equals("Y")) {

                                return  1;
                            } else if (s.equals("N")) {
                                return 0;
                            } else
                                System.out.println("Данные некорректны. Повторите ввод");
                            return -1;
                        };
                        int i=db.chat(input);
                        if(i>-1)
                            success=true;
                        if(i>0){
                            ScriptWork innerWork = new ScriptWork(work.vector, work.getPathOfJson().toString(), work);
                            innerWork.start();
                            work.setInProcess(innerWork.getInProcess());
                        }
                    }

                }
            } else
                throw new NullPointerException();
        }
        else{
            System.out.println("У команды "+ name+" должен быть аргумент");
        }
    }
}
