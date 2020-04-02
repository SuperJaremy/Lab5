package Lab.Program;





import Lab.Program.Commands.DialogBox;
import Lab.Program.Commands.ExitException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MusicBand implements Comparable<MusicBand> {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private Integer numberOfParticipants;
    private long albumsCount;
    private java.util.Date establishmentDate;
    private MusicGenre genre;
    private Album bestAlbum;
    private static int number = 1;
    private static Vector<Integer> ids = new Vector<>();
    private SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public MusicBand() {
        coordinates=new Coordinates();
        bestAlbum = new Album();
        creationDate = LocalDate.now();
    }
    public MusicBand(String name){
        creationDate = LocalDate.now();
        if(name==null){
            this.name=" ";
        }
        else{
            this.name=name;
        }
    }
    public MusicBand(Integer id, String name, Coordinates coordinates, LocalDate creationDate, Integer numberOfParticipants, long albumsCount, java.util.Date establishmentDate, MusicGenre genre, Album bestAlbum){
        boolean uniqueID=!ids.contains(id);
        if(id!=null) {
            if (id > 0) {
                if (uniqueID) {
                    this.id = id;
                }
                else {
                    System.out.println("id "+id+" повторяется");
                    throw new NullPointerException();
                }
            }
            else {
                System.out.println("id объектов должен быть положительным целым числом." + id+" им не является");
                throw new NullPointerException();
            }
        }
        else{
            System.out.println("Не задан id объекта");
            throw new NullPointerException();
        }
        if(name!=null&&name.length()!=0)
            this.name=name;
        else{
            System.out.println("У объекта с id "+id+" должно быть имя");
            throw new NullPointerException();
        }
        if(coordinates!=null){
            if(coordinates.getX()!=null){
                if(coordinates.getX()>-801)
                    this.coordinates=coordinates;
                else{
                    System.out.println("Координата x объекта с id "+id+"  должна быть больше -801");
                    throw new NullPointerException();
                }
            }
            else{
                System.out.println("Координата x объекта с id "+id+"  должна быть задана");
                throw new NullPointerException();
            }
        }
        else{
            System.out.println("Координаты объекта с id "+ id+" должны быть заданы");
            throw new NullPointerException();
        }
        if(creationDate!=null)
            this.creationDate=creationDate;
        else{
            System.out.println("Дата создания объекта с id "+ id+" должна быть задана");
            throw new NullPointerException();
        }
        if(numberOfParticipants==null||numberOfParticipants>0){
            this.numberOfParticipants=numberOfParticipants;
        }
        else{
            System.out.println("Количество участников группы объекта с id "+id+" должно быть либо null, либо целым положительным числом");
            throw new NullPointerException();
        }
        if(albumsCount>0)
            this.albumsCount=albumsCount;
        else{
            System.out.println("Количество альбомов объекта с id "+id+" должно быть целым положительным числом");
            throw new NullPointerException();
        }
        if(genre!=null){
            this.genre=genre;
        }
        else{
            System.out.println("Жанр объекта с id "+id+" должен быть выбран из списка: ");
            MusicGenre.list();
            throw new NullPointerException();
        }
       if(bestAlbum==null)
           this.bestAlbum=null;
       else if(bestAlbum.getName()!=null){
           if(bestAlbum.getLength()>0){
               this.bestAlbum=bestAlbum;
           }
           else{
               System.out.println("Длина альбома объекта с id "+id+" должна быть целым положительным числом");
               throw new NullPointerException();
           }
       }
       else{
           System.out.println("У лучшего альбома объекта с id "+id+" должно быть название");
           throw new NullPointerException();
       }
       ids.add(id);
    }
    public Integer getNumberOfParticipants(){
        return numberOfParticipants;
    }
    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public static Vector<Integer> getIds() {
        return ids;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public long getAlbumsCount() {
        return albumsCount;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Album getBestAlbum() {
        return bestAlbum;
    }

    @Override
    public int compareTo(MusicBand musicBand) {
        if(!this.equals(musicBand)) {
            Integer a,b;
            if(this.numberOfParticipants==null)
                a=0;
            else
                a=this.numberOfParticipants;
            if(musicBand.numberOfParticipants==null)
                b=0;
            else
                b=musicBand.numberOfParticipants;
            return a.compareTo(b);
        }
        return 0;
    }

    @Override
    public String toString() {
        String estDate=null;
        if(establishmentDate!=null)
            estDate=sdf.format(establishmentDate);
        return "Id=" + id +
                "\n Название группы: " + name +
                "\n Координаты:\n " + coordinates +
                "\n Дата создания элемента: " + creationDate.format(formatter) +
                "\n Количество участников: " + numberOfParticipants +
                "\n Количество альбомов: " + albumsCount +
                "\n Дата создания группы: " + estDate +
                "\n Жанр: " + genre +
                "\n Лучший альбом:\n " + bestAlbum +"\n";
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicBand musicBand = (MusicBand) o;
        return albumsCount == musicBand.albumsCount &&
                name.equals(musicBand.name) &&
                coordinates.equals(musicBand.coordinates) &&
                creationDate.equals(musicBand.creationDate) &&
                numberOfParticipants.equals(musicBand.numberOfParticipants) &&
                establishmentDate.equals(musicBand.establishmentDate) &&
                genre == musicBand.genre &&
                bestAlbum.equals(musicBand.bestAlbum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, numberOfParticipants, albumsCount, establishmentDate, genre, bestAlbum);
    }
    public void fromConsole()throws ExitException {
        /**
         * Создаёт класс MusicBand из стандартного потока ввода
         */
        Scanner input = new Scanner(System.in);
        String nullField="Это поле может быть пустым. Нажмите Enter, чтобы оставить поле пустым";
        boolean success = false;
            System.out.println("Пожалуйста, введите имя");
            while (!success) {
                DialogBox db = s -> {
                    if(s.length()==0){
                        System.out.println("У группы должно быть имя. Повторите ввод");
                        return -1;
                    }
                    else {
                        name=s;
                        return 1;
                    }
                };
                try {
                    if(db.chat(input)>0)
                        success=true;
                }
                catch (ExitException e){
                    System.out.println("Введите Y, если это название группы.");
                    DialogBox db1= s->{
                        if(s.toUpperCase().equals("Y")){
                            name=s;
                            return 1;
                        }
                        else{
                            System.out.println("Повторите ввод ещё раз");
                            return -1;
                        }
                    };
                    boolean b=false;
                    while(!b) {
                        if (db1.chat(input)>0) {
                            b = true;
                            success = true;
                        }
                    }
                }
            }
            coordinates=new Coordinates();
        coordinates.fromConsole();
        success = false;
        System.out.println("Пожалуйста, введите количество участников");
        System.out.println(nullField);
        while (!success) {
            DialogBox db = s -> {
                try {
                    int i;
                    if(s.length()==0)
                        numberOfParticipants=null;
                    else {
                        i = Integer.parseInt(s.trim());
                        if (i <= 0)
                            throw new NullPointerException();
                        numberOfParticipants = i;
                    }
                    return 1;
                } catch (NullPointerException | NumberFormatException e) {
                    System.out.println("Количество участников должно быть целым положительным числом. Повторите ввод или оставьте поле пустым");
                    return -1;
                }
            };
            if(db.chat(input)>0){
                success=true;
            }
        }
        success=false;
        System.out.println("Пожалуйста, введите количество альбомов");
        while (!success) {
            DialogBox db= s -> {
                try {
                    long i;
                    i = Long.parseLong(s.trim());
                    if (i <= 0)
                        throw new NullPointerException();
                    albumsCount=i;
                    return 1;
                } catch (NullPointerException | InputMismatchException | NumberFormatException e) {
                    System.out.println("Количество альбомов должно быть целым положительным числом. Повторите ввод");
                    return -1;
                }
            };
            if(db.chat(input)>0)
                success=true;
        }
        success=false;
        System.out.println("Пожалуйста, введите дату создания группы в формате ДД.ММ.ГГГГ");
        System.out.println(nullField);
        while(!success) {
            DialogBox db = s -> {
                try {
                    sdf.setLenient(false);
                    String line = s.trim();
                    if(line.length()!=0) {
                        establishmentDate = sdf.parse(line);
                    }
                    else
                        establishmentDate=null;
                    return 1;
                } catch (ParseException e) {
                    System.out.println("Неверный формат даты создания. Повторите ввод даты в формате ДД.ММ.ГГГГ или оствьте поле пустым");
                    return -1;
                }
            };
            if(db.chat(input)>0)
                success=true;
        }
        success=false;
        System.out.println("Пожалуйста, выберите музыкальный жанр из представленных: ");
        while(!success) {
            MusicGenre.list();
            DialogBox db = s -> {
                try {
                    genre = MusicGenre.valueOf(s.trim().toUpperCase());
                    return 1;
                } catch (IllegalArgumentException e) {
                    System.out.println("Жанр введён неверно");
                    System.out.println("Пожалуйста, выберите музыкальный жанр из представленных: ");
                    return -1;
                }
            };
            if(db.chat(input)>0)
                success=true;
        }
        bestAlbum=new Album();
        System.out.println("Вспомните лучший альбом группы");
        System.out.println(nullField);
        bestAlbum.fromConsole();
        if(bestAlbum.getName()==null){
            bestAlbum=null;
        }
        if(id==null) {
           while(ids.contains(number)) {
               number++;
               if(number==Integer.MAX_VALUE)
                   number=1;
           }
           id=number;
            ids.add(id);
        }
    }

    /**
     * Создаёт класс MusicBand
     */
    public int fromFile(Vector<String>Contents, int i) throws NullPointerException{
            name = Contents.get(i);
            name = name.trim();
            if (name.length() == 0) {
                System.out.println("У группы должно быть имя");
                throw new NullPointerException();
            }
            i=coordinates.fromFile(Contents,i+1);
            try {
                if(Contents.get(i+1).length()==0)
                    numberOfParticipants=null;
                else {
                    numberOfParticipants = Integer.parseInt(Contents.get(i + 1).trim());
                    if (numberOfParticipants <= 0) {
                        System.out.println("Количество участников не может быть неположительным числом");
                        throw new NullPointerException();
                    }
                }
            }
            catch(NumberFormatException e){
                System.out.println("Количество участников должно быть целым числом");
                throw new NullPointerException();
            }
            try {
                albumsCount = Long.parseLong(Contents.get(i + 2).trim());
                if (albumsCount <= 0) {
                    System.out.println("Количество альбомов не может быть неположительным числом");
                    throw new NullPointerException();
                }
            }
            catch (NumberFormatException e){
                System.out.println("Количество альбомов должно быть целым числом");
                throw new NullPointerException();
            }
            sdf.setLenient(false);
            String line=Contents.get(i+3).trim();
            if(line.length()!=0)
                try {
                    establishmentDate = sdf.parse(line);
                }catch (ParseException e){
                    System.out.println("Дата введена некорректно. Введите дату в формате ДД.ММ.ГГГГ");
                    throw new NullPointerException();
                }
            else
                establishmentDate=null;
            try {
                genre = MusicGenre.valueOf(Contents.get(i + 4).trim().toUpperCase());
            }
            catch(IllegalArgumentException e){
                System.out.println("Неверно введён жанр. Выберите жанр из предложенных");
                MusicGenre.list();
                throw new NullPointerException();
            }
            i=bestAlbum.fromFile(Contents, i+5);
            if(bestAlbum.getName()==null)
                bestAlbum=null;
            if(id==null) {
                while(ids.contains(number)) {
                    number++;
                    if(number==Integer.MAX_VALUE)
                        number=1;
                }
                id=number;
                ids.add(id);
            }
            return i;
    }
}
