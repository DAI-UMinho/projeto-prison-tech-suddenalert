import java.io.Serializable;

public class appointment implements Serializable {
    private int id_appointment;
    private int id_recluse;
    private String dateAppointment;

    public appointment(int id_appointment, int id_recluse, String dateAppointment){
        this.id_appointment = id_appointment;
        this.id_recluse = id_recluse;
        this.dateAppointment = dateAppointment;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public int getId_recluse() {
        return id_recluse;
    }

    public String getDateAppointment() {
        return dateAppointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public void setId_recluse(int id_recluse) {
        this.id_recluse = id_recluse;
    }

    public void setDateAppointment(String dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    @Override
    public String toString() {
        return "appointment{" +
                "id_appointment=" + id_appointment +
                ", id_recluse=" + id_recluse +
                ", dateAppointment='" + dateAppointment + '\'' +
                '}';
    }
}
