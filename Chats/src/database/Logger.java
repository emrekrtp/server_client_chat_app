package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Logger {

    // Mesajları loglama fonksiyonu
    public static void logMessage(String message, String senderType) {
        String url = "jdbc:sqlite:C://Users/emrek/Desktop/deneme/chat_app.sqlite";

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO msg_database (msg_server, msg_client, msg_datetime) VALUES (?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (senderType.equalsIgnoreCase("server")) {
                    pstmt.setString(1, message);
                    pstmt.setString(2, "");
                } else if (senderType.equalsIgnoreCase("client")) {
                    pstmt.setString(1, "");
                    pstmt.setString(2, message);
                }

                pstmt.setString(3, LocalDateTime.now().toString());
                pstmt.executeUpdate();
                System.out.println("Mesaj başarıyla loglandı.");
            }
        } catch (SQLException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Örnek kullanım
        logMessage("Bu bir server mesajıdır.", "server");
        logMessage("Bu bir client mesajıdır.", "client");
    }
}
