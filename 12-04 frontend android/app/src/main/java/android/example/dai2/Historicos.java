package android.example.dai2;

public class Historicos {
    private String nomePessoa;
    private String data;
    private String acao;
    private String tipo;
    private String motivo;


    public Historicos(String nomePessoa, String data, String acao, String tipo, String motivo) {
        this.nomePessoa = nomePessoa;
        this.data = data;
        this.acao = acao;
        this.tipo = tipo;
        this.motivo = motivo;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
