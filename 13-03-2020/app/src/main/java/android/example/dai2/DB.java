package android.example.dai2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DB extends  _Default implements Runnable  {

    private Connection conn;
    private String host = "193.136.11.180";
    private String db = "suddenalert";
    private int port = 3306;
    private String user = "suddenalertuser";
    private String pass = "Suddenalert.0";
    private String url = "jdbc:mysql://%s:%d/%s";

    public DB() {
        super();
        this.url = String.format(this.url, this.host, this.port, this.db);

        this.conecta();
        this.disconecta();

    }

    @Override
    public void run() {
        try {
            Class.forName("com.centurylink.mdw.assets.Driver");
            this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (Exception e){
            this._mensagem = e.getMessage();
            this._status = false;
        }
    }
    private void conecta(){
        Thread thread = new Thread(this);
        thread.start();
        try{
            thread.join();
        }catch (Exception e){
            this._mensagem = e.getMessage();
            this._status = false;

        }
    }

    private void disconecta(){
        if(this.conn!= null){
            try{
                this.conn.close();
            } catch (Exception e){

            }finally {
                this.conn = null;
            }
        }
    }

    public ResultSet select(String query){
         this.conecta();
         ResultSet resultSet = null;
         try{
             resultSet = new ExecuteDB(this.conn,query).execute().get();
         }catch (Exception e){
             this._status = false;
             this._mensagem = e.getMessage();
         }
         return resultSet;
    }

    public ResultSet execute(String query){
        this.conecta();
        ResultSet resultSet = null;
        try{
            resultSet = new ExecuteDB(this.conn,query).execute().get();
        }catch (Exception e){
            this._status = false;
            this._mensagem = e.getMessage();
        }
        return resultSet;
    }


}
