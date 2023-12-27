package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class baglanti {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:C://Users/emrek/Desktop/deneme/chat_app.sqlite";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
            System.out.println(conn.getMetaData());
            System.out.println("veritabanı bağlantısı başarılı");
        } catch (SQLException e) {
            System.out.println("Hata " + e);

        }
    }
}
