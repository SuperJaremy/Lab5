package Lab.Program.Commands;

import Lab.Program.MusicBand;

import java.util.Vector;

/**
 * Вспомогательный интерфейс для поиска элемента коллекции по его id
 */
public interface SearchID {
    default MusicBand SearchID(Vector<MusicBand> V, int id){
        int i=0;
        MusicBand mb= null;
        while(i<V.size()&&mb==null){
            if(V.get(i).getId()!=id)
                i++;
            else
                mb=V.get(i);
        }
        return mb;
    }
}
