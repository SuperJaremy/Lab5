package Lab.Program.Commands;

import Lab.Program.FileTester;
import Lab.Program.MusicBand;
import Lab.Program.Work;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/**
 * Вспомогательный класс для организации работы в скрипте
 */
class ScriptWork extends Work {
    public ScriptWork(Vector<MusicBand> V, LocalDate date, String path, Work work) {
        super(V, date, path);
        this.element=work.getElement();
        this.pathOfScript= Paths.get(element);
        this.history=work.getHistory();
        this.Commands.replace("add",new FileAddElement());
        this.Commands.replace("add_if_max",new FileAddIfMax());
        this.Commands.replace("update_id",new FileUpdateID());
        this.Scripts=work.getScripts();
    }
    @Override
    public void start() {
        boolean correct = true;
        while (correct) {
            String line = element;
            correct = false;
            if (FileTester.TestFileToRead(pathOfScript)) {
                try (LineNumberReader reader = new LineNumberReader(new FileReader(pathOfScript.toFile()))) {
                    this.reader = reader;
                    while (inProcess && line != null) {
                        element = null;
                        line = reader.readLine();
                        if (line != null) {
                            line = line.trim();
                            String[] words = line.split(" ");
                            try {
                                String com = words[0].toLowerCase();
                                if (words.length > 1) {
                                    element = line.substring(line.indexOf(" ") + 1);
                                }
                                if (!Commands.containsKey(com))
                                    throw new NullPointerException();
                                Commands.get(com).act(this);
                                if (history.size() > 7)
                                    history.remove();
                                history.add(Commands.get(com));
                            } catch (NullPointerException | NumberFormatException e) {
                                System.out.println("Ошибка в скрипте: " + pathOfScript + " в строке " + reader.getLineNumber());
                                System.out.println("Хотите ли вы её исправить? [Y/N]");
                                boolean success = false;
                                Scanner input = new Scanner(System.in);
                                while (!success) {
                                    String l = input.nextLine().trim().toUpperCase();
                                    if (l.equals("Y")) {
                                        success = true;
                                        System.out.println("Нажмите Enter для продолжения работы");
                                        correct = true;
                                        line = null;
                                        input.nextLine();
                                    } else if (l.equals("N")) {
                                        success = true;
                                    } else
                                        System.out.println("Данные некорректны. Повторите ввод");
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Файл скрипта " + pathOfScript + " не был найден");
                    throw new NullPointerException();
                }
            }
        }
    }
}
