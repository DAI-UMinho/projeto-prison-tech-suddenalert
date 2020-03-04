package backend;
import java.io.Serializable;

public class recluse implements Serializable{
    private int id_recluse;
    private String recluseName;
    private String date_entry;
    private String date_left;

    public recluse(int id_recluse, String recluseName, String date_entry, String date_left){
        this.id_recluse = id_recluse;
        this.recluseName = recluseName;
        this.date_entry = date_entry;
        this.date_left = date_left;
    }
}
