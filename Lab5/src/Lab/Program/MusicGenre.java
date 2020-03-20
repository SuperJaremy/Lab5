package Lab.Program;

public enum MusicGenre {
    JAZZ,
    PUNK_ROCK,
    BRIT_POP;
    static public void list(){
        for(MusicGenre i:values())
            System.out.println(i.toString());
    }
}
