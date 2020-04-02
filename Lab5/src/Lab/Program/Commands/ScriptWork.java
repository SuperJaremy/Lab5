package Lab.Program.Commands;

import Lab.Program.FileTester;
import Lab.Program.MusicBand;
import Lab.Program.Work;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;

/**
 * Вспомогательный класс для организации работы в скрипте
 */
class ScriptWork extends Work {
    public ScriptWork(Vector<MusicBand> V, String path, Work work) {
        super(V, path);
        date=work.date;
        this.element = work.getElement();
        this.pathOfScript = Paths.get(element);
        this.history = work.getHistory();
        this.Commands.replace("add", new FileAddElement());
        this.Commands.replace("add_if_max", new FileAddIfMax());
        this.Commands.replace("update_id", new FileUpdateID());
        this.Scripts = work.getScripts();
    }
    @Override
    public void start() throws ExitException{
        boolean correct = true;
        while (correct) {
            String line = "e";
            correct = false;
            if (FileTester.TestFileToRead(pathOfScript)) {
                Contents.clear();
                    try (BufferedReader reader = new BufferedReader(new FileReader(pathOfScript.toFile()))) {
                        while(line!=null) {
                            line = reader.readLine();
                            if (line != null) {
                                Contents.add(line);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Файл не может быть прочитан");
                    }
                while (currentLine < Contents.size() && !correct) {
                    element=null;
                    try {
                        ExecuteLine(Contents.get(currentLine));
                        currentLine++;
                    } catch (NullPointerException e) {
                        System.out.println("Ошибка в скрипте " + pathOfScript + " в строке " + (currentLine + 1)+":");
                        System.out.println(Contents.get(currentLine));
                        System.out.println("Вы можете исправить ошибку. Исполнение скрипта продолжится с той же строки в которой находилась ошибочная команда");
                        System.out.println("Вы хотите исправить ошибку? [Y/N]");
                        boolean success = false;
                        Scanner input = new Scanner(System.in);
                        while (!success) {
                            DialogBox db = s -> {
                                if (s.equals("Y")) {
                                    System.out.println("Исправьте ошибку. Затем нажимте Enter для продожения");
                                    input.nextLine();
                                    return 1;
                                } else if (s.equals("N")) {
                                    currentLine++;
                                    return 0;
                                } else {
                                    System.out.println("Данные некорректны. Повторите ввод");
                                    return -1;
                                }
                            };
                            int i=db.chat(input);
                            if(i>-1)
                                success=true;
                            if(i>1)
                                correct=true;
                        }
                    }
                }
            }
        }
    }
}
