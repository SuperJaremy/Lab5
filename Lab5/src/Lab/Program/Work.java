package Lab.Program;

import Lab.Program.Commands.*;
import Lab.Program.Hell.MusicBandDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

/**
 * Это менеджер коллекции. Здесь происходит чтение команд из консоли и их запуск
 */
public class Work {
    public Vector<MusicBand> vector;
    public LocalDate date;
    protected String element=null;
    protected boolean inProcess=true;
    protected Queue<Command> history = new LinkedList<>();
    protected Path pathOfJson;
    protected Path pathOfScript;
    protected BufferedReader reader;
    protected Vector<Integer> Scripts = new Vector<>();

    public BufferedReader getReader() {
        return reader;
    }

    public Vector<Integer> getScripts() {
        return Scripts;
    }

    public Queue<Command> getHistory() {
        return history;
    }
    public void setHistory(Queue<Command> history){
        this.history=history;
    }

    public String getElement(){
        return element;
    }
    public void setElement(String element){
        this.element=element;
    }
    public Path getPathOfJson(){
        return pathOfJson;
    }
    public Path getPathOfScript(){
        return pathOfScript;
    }
    public Work(Vector<MusicBand>V, LocalDate date, String path){
        this.date=date;
        vector=V;
        this.pathOfJson= Paths.get(path);
        Command info = new Info();
        Command help = new Help();
        Command show = new Show();
        Command add = new AddElement();
        Command clear = new Clear();
        Command exit = new Exit();
        Command remove=new RemoveFirst();
        Command ifMax = new AddIfMax();
        Command history1 = new History();
        Command sum = new SumOfNumberOfParticipants();
        Command count=new CountByNumberOfParticipants();
        Command printFieldDescendingNumberOfParticipants= new PrintFieldDescendingNumberOfParticipants();
        Command uid=new UpdateID();
        Command remove_id = new RemoveByID();
        Command save = new Save();
        Command script = new ExecuteScript();
        Commands.put(info.getName(), info);
        Commands.put(help.getName(), help);
        Commands.put(show.getName(),show);
        Commands.put(add.getName(), add);
        Commands.put(clear.getName(),clear);
        Commands.put(exit.getName(), exit);
        Commands.put(remove.getName(),remove);
        Commands.put(ifMax.getName(),ifMax);
        Commands.put(history1.getName(),history1);
        Commands.put(sum.getName(),sum);
        Commands.put(count.getName(),count);
        Commands.put(printFieldDescendingNumberOfParticipants.getName(),printFieldDescendingNumberOfParticipants);
        Commands.put(uid.getName(),uid);
        Commands.put(remove_id.getName(),remove_id);
        Commands.put(save.getName(),save);
        Commands.put(script.getName(),script);
    }
    protected Map<String,Command> Commands = new HashMap<>();
    public Map<String,Command> getCommands(){
        return Commands;
    }
    public boolean getInProcess(){
        return inProcess;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }

    /**
     * Запускает менджер коллекций
     */
    public void start(){
        Gson gson = new GsonBuilder().registerTypeAdapter(MusicBand.class,new MusicBandDeserializer()).create();
        if(!FileTester.TestFileToRead(pathOfJson)){
            inProcess=false;
        }
        else {
            try (BufferedReader reader = new BufferedReader(new FileReader(pathOfJson.toFile()))) {
                this.reader = reader;
                Type vectorType = new TypeToken<Vector<MusicBand>>() {
                }.getType();
                vector = gson.fromJson(this.reader, vectorType);
            } catch (IOException | NullPointerException e) {
                System.out.println("Файл .json не может быть прочитан.");
                inProcess = false;
            }
        }
        if(inProcess) {
            System.out.println("Давайте начнём");
            Commands.get("help").act(this);
        }
        while(inProcess){
            element=null;
            Scanner input=new Scanner(System.in);
            String line=input.nextLine();
            line=line.trim();
            String [] words=line.split(" ");
            try {
                String com = words[0].toLowerCase();
                if(words.length>1) {
                    element=line.substring(line.indexOf(" ") + 1);
                }
                if(!Commands.containsKey(com))
                    throw new NullPointerException();
                Commands.get(com).act(this);
                if(history.size()>7)
                    history.remove();
                history.add(Commands.get(com));
            }
            catch (NullPointerException | NumberFormatException e){
                System.out.println("Данные некорректны. Повторите ввод ещё раз");
            }
        }
    }
}
