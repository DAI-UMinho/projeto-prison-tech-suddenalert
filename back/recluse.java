
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

    public int getId_recluse(){
        return id_recluse;
    }

    public String getRecluseName() {
        return recluseName;
    }

    public String getDate_entry() {
        return date_entry;
    }

    public String getDate_left() {
        return date_left;
    }

    public void setId_recluse(int id_recluse) {
        this.id_recluse = id_recluse;
    }

    public void setRecluseName(String recluseName) {
        this.recluseName = recluseName;
    }

    public void setDate_entry(String date_entry) {
        this.date_entry = date_entry;
    }

    public void setDate_left(String date_left) {
        this.date_left = date_left;
    }
    public String toString(){
        return "Recluse{" + "idRecluse=" + id_recluse + ",Recluse Name=" + recluseName + ", Entry Date=" + date_entry + ", Left Date=" + date_left + '}';
    }
}
