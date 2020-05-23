package android.example.dai2;

public class ListaHorarios {
    public String nomeHor;
    public int id_hor;

    public ListaHorarios(String nomeHor, int id_hor) {
        this.nomeHor = nomeHor;
        this.id_hor = id_hor;
    }

    public String getNomeHor() {
        return nomeHor;
    }

    public void setNomeHor(String nomeHor) {
        this.nomeHor = nomeHor;
    }

    public int getId_hor() {
        return id_hor;
    }

    public void setId_hor(int id_hor) {
        this.id_hor = id_hor;
    }
}
