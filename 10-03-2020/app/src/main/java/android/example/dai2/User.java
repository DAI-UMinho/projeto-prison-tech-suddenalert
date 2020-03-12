package android.example.dai2;

import java.sql.ResultSet;
import java.util.ArrayList;

public class User extends _Default {

    private String scan;
    private int id_type;
    private String name;
    private String location;
    private String color;
    private int points;

    public User(){
        super();
        this.scan = "";
        this.id_type = -1;
        this.name = "";
        this.location = "";
        this.color = "";
        this.points = -1;

    }

    public ArrayList<User> getLista(){
        DB db = new DB();
        ArrayList<User> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM Profile");
            if(resultSet!= null){
                while (resultSet.next()){
                    User obj = new User();
                    obj.setScan(resultSet.getString("scan"));
                    obj.setId_type(resultSet.getInt("id_type"));
                    obj.setName(resultSet.getString("name"));
                    obj.setLocation(resultSet.getString("location"));
                    obj.setColor(resultSet.getString("color"));
                    obj.setPoints(resultSet.getInt("points"));
                    lista.add(obj);
                    obj = null;
                }
            }
        }catch (Exception ex) {
            this._mensagem = ex.getMessage();
            this._status = false;

        }
        return lista;
    }

    public void salvar(){
        String comando = "";
        if(this.getId_type() == -1){
            comando = String.format("INSERT INTO Profile (id_type, name) VALUES ('%d', '%s');",
            this.getId_type(),this.getName());

        }else{
            comando = String.format("UPDATE Profile SET id_type = '%i', name = '%s' WHERE scan = '%s';",
                    this.getId_type(),this.getName(),this.getScan());
        }
        DB db = new DB();
        db.execute(comando);
        this._mensagem = db._mensagem;
        this._status = db._status;
    }
    public void apagar(){
        String comando = String.format("DELETE FROM WHERE scan = '%s';",this.getScan());
        DB db = new DB();
        db.execute(comando);
        this._mensagem = db._mensagem;
        this._status = db._status;

    }

    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
