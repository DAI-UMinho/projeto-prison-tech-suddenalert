package android.example.dai2;

public class ClassListReclusos {
    public String img;
    public String nomeRec;
    public String alaRec;
    public String pisoRec;
    public String doencaRec;


    public ClassListReclusos(String nomeRec, String doencaRec, String alaRec, String pisoRec, String img){
        this.nomeRec = nomeRec;
        this.doencaRec = doencaRec;
        this.alaRec = alaRec;
        this.pisoRec = pisoRec;
        this.img = img;
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

}
