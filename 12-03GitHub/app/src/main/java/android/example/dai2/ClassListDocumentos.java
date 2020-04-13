package android.example.dai2;

import org.w3c.dom.Text;

import java.sql.Timestamp;

public class ClassListDocumentos {
    public int id_Report;
    public String scan;
    public String report;
    public String data;
    public String id_recluse;
    public String titulo;

    public ClassListDocumentos(int id_Report, String scan, String report, String data, String id_recluse, String titulo) {
        this.id_Report = id_Report;
        this.scan = scan;
        this.report = report;
        this.data = data;
        this.id_recluse = id_recluse;
        this.titulo = titulo;
    }

    public int getId_Report() {
        return id_Report;
    }

    public void setId_Report(int id_Report) {
        this.id_Report = id_Report;
    }

    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId_recluse() {
        return id_recluse;
    }

    public void setId_recluse(String id_recluse) {
        this.id_recluse = id_recluse;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
