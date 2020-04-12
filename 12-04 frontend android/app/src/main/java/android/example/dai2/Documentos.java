package android.example.dai2;

public class Documentos {
    private String nomeRel;
    private String nomeEn;
    private String data;
    private String gravidade;
    private String email;


    public Documentos(String nomeRel, String nomeEn, String data, String gravidade, String email) {
        this.nomeRel = nomeRel;
        this.nomeEn = nomeEn;
        this.data = data;
        this.gravidade = gravidade;
        this.email = email;
    }

    public String getNomeRel() {
        return nomeRel;
    }
public String getemail(){return email;}

    public void setNomeRel(String nomeRel) {
        this.nomeRel = nomeRel;
    }
    public void setemail(String email) {this.email = email;}

    public String getNomeEn() {
        return nomeEn;
    }

    public void setNomeEn(String nomeEn) {
        this.nomeEn = nomeEn;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getgravidade(){
        return gravidade;
    }
    public void setgravidade(String gravidade) {this.gravidade = gravidade;}


}
