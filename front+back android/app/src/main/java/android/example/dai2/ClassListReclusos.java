package android.example.dai2;

public class ClassListReclusos {
    public int id_recluse;
    public String img;
    public String nomeRec;
    public String alaRec;
    public String pisoRec;
    public String doencaRec;
    public String nascimento;



    public ClassListReclusos(int id_recluse, String nomeRec, String doencaRec, String alaRec, String pisoRec, String img, String nascimento){
        this.id_recluse = id_recluse;
        this.nomeRec = nomeRec;
        this.doencaRec = doencaRec;
        this.alaRec = alaRec;
        this.pisoRec = pisoRec;
        this.img = img;
        this.nascimento = nascimento;
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
