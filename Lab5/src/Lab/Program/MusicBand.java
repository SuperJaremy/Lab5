package Lab.Program;




import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static Lab.Program.MusicGenre.list;

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
    private static int number = 0;
    private static Vector<Integer> ids = new Vector<>();
    private SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public MusicBand() {
        coordinates=new Coordinates();
        bestAlbum = new Album();
        creationDate = LocalDate.now();
    }
    public MusicBand(String name){
        coordinates=new Coordinates();
        bestAlbum = new Album();
        creationDate = LocalDate.now();
        if(name==null){
            this.name=" ";
            this.fromConsole();
        }
        else{
            this.fromConsole();
            this.name=name;
        }
    }
    public MusicBand(Integer id, String name, Coordinates coordinates, LocalDate creationDate, Integer numberOfParticipants, long albumsCount, java.util.Date establishmentDate, MusicGenre genre, Album bestAlbum){
        boolean uniqueID=!ids.contains(id);
        if(id!=null&&id>0&&coordinates!=null&&coordinates.getX()!=null&&coordinates.getX()>-801&&creationDate!=null&&(numberOfParticipants==null||numberOfParticipants>0)&&albumsCount>0&&genre!=null&&(bestAlbum==null||bestAlbum.getName()!=null&&bestAlbum.getLength()>0)&&uniqueID) {
            this.id = id;
            this.name = name;
            this.coordinates = coordinates;
            this.creationDate = creationDate;
            this.numberOfParticipants = numberOfParticipants;
            this.albumsCount = albumsCount;
            this.establishmentDate = establishmentDate;
            this.genre = genre;
            this.bestAlbum = bestAlbum;
            ids.add(id);
        }
        else {
            System.out.println("Ошибка в полях объекта");
            throw new NullPointerException();
        }
    }
    public int getNumberOfParticipants(){
        return numberOfParticipants;
    }
    public int getID(){
        return id;
    }

    public String getName() {
        return name;
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
            return this.numberOfParticipants.compareTo(musicBand.numberOfParticipants);
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
    public void fromConsole(){
        /**
         * Создаёт класс MusicBand из стандартного потока ввода
         */
        Scanner input = new Scanner(System.in);
        boolean success = false;
        if(this.name!=null) {
            System.out.println("Пожалуйста, введите имя");
            while (!success) {
                try {
                    name = input.nextLine();
                    name = name.trim();
                    if (name.length() == 0)
                        throw new NullPointerException();
                    success = true;
                } catch (NullPointerException e) {
                    System.out.println("Данные некорректны. Повторите ввод ещё раз");
                }
            }
        }
        coordinates.fromConsole();
        success = false;
        System.out.println("Пожалуйста, введите количество участников");
        while (!success) {
            try {
                numberOfParticipants = Integer.parseInt(input.nextLine().trim());
                if (numberOfParticipants <= 0)
                    throw new NullPointerException();
                success=true;
            } catch (InputMismatchException | NullPointerException | NumberFormatException e) {
                System.out.println("Данные некорректны. Повторите ввод ещё раз");
            }
        }
        success=false;
        System.out.println("Пожалуйста, введите количество альбомов");
        while (!success) {
            try {
                albumsCount = Long.parseLong(input.nextLine().trim());
                if (albumsCount <= 0)
                    throw new NullPointerException();
                success=true;
            } catch (NullPointerException | InputMismatchException | NumberFormatException e) {
                System.out.println("Данные некорректны. Повторите ввод ещё раз");
            }
        }
        success=false;
        System.out.println("Пожалуста, введите дату создания группы в формате ДД.ММ.ГГГГ");

        while(!success) {
            try {
                sdf.setLenient(false);
                String line = input.nextLine().trim();
                if(line.length()!=0) {
                    establishmentDate = sdf.parse(line);
                }
                else
                    establishmentDate=null;
                success=true;
            } catch (ParseException e) {
                System.out.println("Данные некорректны. Повторите ввод ещё раз");
            }
        }
        success=false;
        System.out.println("Пожалуйста, выберите музыкальный жанр из представленных: ");
        while(!success) {
            list();
            try {
                genre = MusicGenre.valueOf(input.nextLine().trim().toUpperCase());
                success=true;
            } catch (IllegalArgumentException e) {
                System.out.println("Данные некорректны. Повторите ввод ещё раз");
            }
        }
        bestAlbum.fromConsole();
        if(bestAlbum.getName()==null){
            bestAlbum=null;
        }
        if(id==null) {
            id = ++number;
            ids.add(id);
        }
    }

    /**
     * Создаёт класс MusicBand из потока BufferedReader reader
     * @param reader
     * @throws IOException
     * @throws NullPointerException
     * @throws InputMismatchException
     * @throws ParseException
     * @throws IllegalArgumentException
     */
    public void fromFile(BufferedReader reader) throws IOException, NullPointerException, InputMismatchException, ParseException,IllegalArgumentException{
        if(name!=null) {
            name = reader.readLine();
            name = name.trim();
            if (name.length() == 0) {
                throw new NullPointerException();
            }
        }
            coordinates.fromFile(reader);
            numberOfParticipants = Integer.parseInt(reader.readLine().trim());
            if (numberOfParticipants <= 0) {
                throw new NullPointerException();
            }
            albumsCount = Long.parseLong(reader.readLine().trim());
            if (albumsCount <= 0) {
                throw new NullPointerException();
            }
            sdf.setLenient(false);
            String line=reader.readLine().trim();
            if(line.length()!=0)
                establishmentDate = sdf.parse(line);
            else
                establishmentDate=null;
            genre = MusicGenre.valueOf(reader.readLine().trim().toUpperCase());
            bestAlbum.fromFile(reader);
            if(bestAlbum.getName()==null)
                bestAlbum=null;
            if(id!=null) {
                id = ++number;
                ids.add(id);
            }
    }
}
