package android.example.dai2;

import java.sql.*;

public class Sistema {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String url = "jbdc:sql.freemysqlhosting.net";

        String dbName = "sql2327084";
        String driver = "jbdc:sql.freemysqlhosting.net";
        String username = "sql2327084";
        String password = "qX1*dU7%";
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url+dbName,username,password);
            Statement st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT id_recluse FROM Recluse");
            while (res.next()){
                int id = res.getInt("id");
                String msg = res.getString("msg");
                System.out.println(id + "\t" + msg);
            }
            int val = st.executeUpdate("INSERT INTO Recluse VALUES ('234567891', '2Preto', '2020-05-22', '2025-10-05');");
            if(val==1)
                System.out.print("Successfully inserted value");
            conn.close();
        } catch (Exception e){

        }

    }
}
