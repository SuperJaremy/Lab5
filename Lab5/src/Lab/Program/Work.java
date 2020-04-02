package Lab.Program;

import Lab.Program.Commands.*;
import Lab.Program.Hell.MusicBandDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Это менеджер коллекции. Здесь происходит чтение команд из консоли и их запуск
 */
public class Work {
    public Vector<MusicBand> vector;
    public Date date;
    protected String element=null;
    protected boolean inProcess=true;
    protected Queue<Command> history = new LinkedList<>();
    protected Path pathOfJson;
    protected Path pathOfScript;
    protected Vector<Integer> Scripts = new Vector<>();
    protected Vector<String> Contents = new Vector<>();
    protected int currentLine=0;


    public Vector<Integer> getScripts() {
        return Scripts;
    }

    public Queue<Command> getHistory() {
        return history;
    }


    public String getElement(){
        return element;
    }
    public Path getPathOfJson(){
        return pathOfJson;
    }
    public Path getPathOfScript(){
        return pathOfScript;
    }
    public Work(Vector<MusicBand>V, String path){
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
    public Vector<String> getContents(){
        return Contents;
    }
    public int getCurrentLine(){
        return currentLine;
    }

    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
    }

    /**
     * Запускает менджер коллекций
     */
    public void start() throws ExitException{
        Gson gson = new GsonBuilder().registerTypeAdapter(MusicBand.class,new MusicBandDeserializer()).create();
        if(!FileTester.TestFileToRead(pathOfJson)){
            inProcess=false;
        }
        else {
            if(pathOfJson.getFileName().toString().substring(pathOfJson.getFileName().toString().lastIndexOf(".")).equals(".json")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(pathOfJson.toFile()))) {
                    BasicFileAttributes bfa = Files.readAttributes(pathOfJson, BasicFileAttributes.class);
                    date = new Date(bfa.creationTime().toMillis());
                    Type vectorType = new TypeToken<Vector<MusicBand>>() {
                    }.getType();
                    try {
                        vector = gson.fromJson(reader, vectorType);
                        if (vector == null) {
                            vector = new Vector<>();
                        }
                    } catch (JsonSyntaxException e) {
                        System.out.println("Ошибка внутри файла \""+pathOfJson+"\". Проверьте правильность синтаксиса");
                        throw new NullPointerException();
                    }
                } catch (IOException | NullPointerException e) {
                    System.out.println("Файл \""+pathOfJson+"\" не может быть прочитан.");
                    inProcess = false;
                }
            }
            else{
                inProcess=false;
                System.out.println("Программы работает только с исходными коллекциями в формате json");
                System.out.println("Проверьте, что имя файла соответствует формату *.json");
            }
        }
        if(inProcess) {
            System.out.println("Давайте начнём");
            ExecuteLine("help");
        }
        while(inProcess){
            element=null;
            Scanner input=new Scanner(System.in);
            String line=input.nextLine();
            try{ExecuteLine(line);}
            catch(NullPointerException e){
                System.out.println("Ошибка в команде. Вы можете повторить ввести команду ещё раз");
            }
        }
    }
    protected void ExecuteLine(String line) throws NullPointerException{
        line=line.trim();
        String [] words=line.split(" ");
            String com = words[0].toLowerCase();
            if (words.length > 1) {
                element = line.substring(line.indexOf(" ") + 1);
            }
            if (!Commands.containsKey(com)) {
                System.out.println("Такой команды не существует");
                throw new NullPointerException();
            }
            try {
                Commands.get(com).act(this);
                if (history.size() > 7)
                    history.remove();
                history.add(Commands.get(com));
            }
            catch (ExitException e){
                System.out.println("Выполнение команды "+line.trim()+" было прервано");
            }
    }
}
