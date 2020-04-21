package android.example.dai2;

public class ClassListReclusos {
    public int id_recluse;
    public String img;
    public String nomeRec;
    public String alaRec;
    public String pisoRec;
    public String doencaRec;
    public String nascimento;
    public int numero_rec;



    public ClassListReclusos(int id_recluse, String nomeRec, String doencaRec, String alaRec, String pisoRec, String img, String nascimento, int numero_rec){
        this.id_recluse = id_recluse;
        this.nomeRec = nomeRec;
        this.doencaRec = doencaRec;
        this.alaRec = alaRec;
        this.pisoRec = pisoRec;
        this.img = img;
        this.nascimento = nascimento;
        this.numero_rec = numero_rec;
    }

    public int getNumero_rec() {
        return numero_rec;
    }

    public void setNumero_rec(int numero_rec) {
        this.numero_rec = numero_rec;
    }

    public int getId_recluse() {
        return id_recluse;
    }

    public String getImg() {
        return img;
    }


    public String getNomeRec() {
        return nomeRec;
    }


    public String getAlaRec() {
        return alaRec;
    }


    public String getPisoRec() {
        return pisoRec;
    }


    public String getDoencaRec() {
        return doencaRec;
    }

    public String getNascimento() {
        return nascimento;
    }

}
