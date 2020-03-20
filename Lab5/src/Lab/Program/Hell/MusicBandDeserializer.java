package Lab.Program.Hell;

import Lab.Program.Album;
import Lab.Program.Coordinates;
import Lab.Program.MusicBand;
import Lab.Program.MusicGenre;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Класс десериализатора MusicBand из файла json
 */
public class MusicBandDeserializer implements JsonDeserializer<MusicBand> {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private Gson gson = new Gson();
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    @Override
    public MusicBand deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject= jsonElement.getAsJsonObject();
        JsonElement jsonId=jsonObject.get("id");
        JsonElement jsonName=jsonObject.get("name");
        JsonElement jsonCoordinates=jsonObject.getAsJsonObject("coordinates");
        Coordinates coord = gson.fromJson(jsonCoordinates,Coordinates.class);
        JsonElement jsonCreationDate=jsonObject.get("creationDate");
        JsonElement jsonNumberOfParticipants=jsonObject.get("numberOfParticipants");
        JsonElement jsonAlbumsCount=jsonObject.get("albumsCount");
        JsonElement jsonEstablishmentDate=jsonObject.get("establishmentDate");
        JsonElement jsonGenre=jsonObject.get("genre");
        JsonElement jsonBestAlbum=jsonObject.getAsJsonObject("bestAlbum");
        Album alb = gson.fromJson(jsonBestAlbum,Album.class);
        java.util.Date date;
        java.time.LocalDate localDate;
        try {
            date =sdf.parse(jsonEstablishmentDate.getAsString());
            localDate=LocalDate.parse(jsonCreationDate.getAsString(),dateTimeFormatter);
        } catch (ParseException| java.time.format.DateTimeParseException e) {
            throw new NullPointerException();
        }
        MusicBand mb = new MusicBand(jsonId.getAsInt(),jsonName.getAsString(), coord,
                localDate, jsonNumberOfParticipants.getAsInt(), jsonAlbumsCount.getAsLong(),
                date, MusicGenre.valueOf(jsonGenre.getAsString()), alb);
        return mb;
    }
}
