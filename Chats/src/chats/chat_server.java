package chats;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class chat_server extends javax.swing.JFrame {

    // Sunucu, soket ve veri giriş/çıkış akışları tanımlanıyor
    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    private static javax.swing.JTextArea msg_area;
    // Variables declaration - do not modify                     
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton msg_send;
    private javax.swing.JTextField msg_text;

    // Constructor (Oluşturucu)
    public chat_server() {
        initComponents();  // Arayüz bileşenleri başlatılıyor
        serverSocket();  // Sunucu soketi başlatılıyor
        attachClearListeners();  // Temizleme olay dinleyicileri bağlanıyor

    }

    public static void main(String args[]) {
        // Arayüzü başlatma işlemleri bu metotta gerçekleştiriliyor
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new chat_server().setVisible(true);
            }
        });
        // İstemciden gelen mesajları dinlemek için bir döngü
        String msgin = "";
        try {

            while (!msgin.equals("exit")) {
                msgin = din.readUTF();  //clientten gelen mesajı okuma
                msg_area.setText(msg_area.getText().trim() + "\n Client :\t" + msgin);  // clientten gelen mesajı görüntüleme
            }

        } catch (IOException e) {
            System.out.println("Hata oluştu -> " + e);
        }
    }

    // Sunucu soketi oluşturuluyor ve bağlantı bekleniyor
    private void serverSocket() {
        try {
            ss = new ServerSocket(1201); //server 1201 port ile başlıyor
            System.out.println("Server başlatıldı client bekleniyor...");
            s = ss.accept();  // server porta gelen bağlantıyı kabul ediyor
            System.out.println("client bağlandı");
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            // İstemciden gelen mesajları dinlemek için bir thread başlatılıyor
            Thread messageReceiverThread = new Thread(() -> {
                try {
                    String msgin;
                    while (true) {
                        msgin = din.readUTF(); // İstemciden gelen mesajı oku
                        if (msgin.equals("exit")) {
                            break; // İstemci 'exit' mesajı gönderdiğinde döngüyü kır
                        }
                        // Gelen mesajı ekranda göster
                        displayMessageFromClient(msgin);
                    }
                } catch (IOException e) {
                    System.out.println("Hata oluştu -> " + e);
                }
            });
            messageReceiverThread.start();  // Thread'i başlat

        } catch (IOException e) {
            System.out.println("Hata oluştu -> " + e);
        }
    }

    // İstemciden gelen mesajı ekrana göstermek için bir metot
    private void displayMessageFromClient(String message) {
        java.awt.EventQueue.invokeLater(() -> {
            msg_area.append("Client: " + message + "\n");
        });
    }

    // Metin alanına odaklandığında veya tıklandığında içeriği temizlemek için olay dinleyicileri bağlanıyor
    private void attachClearListeners() {
        msg_text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                msg_text.setText(""); // Tıklandığında içeriği temizle
            }
        });
        msg_text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                msg_text.setText(""); // Odak geldiğinde içeriği temizle
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg_text = new javax.swing.JTextField();
        msg_send = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        msg_area.setColumns(20);
        msg_area.setRows(5);
        jScrollPane1.setViewportView(msg_area);

        msg_text.setText("Mesaj giriniz...");
        msg_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_textActionPerformed(evt);
            }
        });

        msg_send.setText("Gönder");
        msg_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msg_sendActionPerformed(evt);
            }
        });

        jLabel1.setText("Server");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(msg_send, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(45, 45, 45)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(msg_text, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(msg_send))
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void msg_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_sendActionPerformed

        try {
            String msgout = msg_text.getText().trim();
            dout.writeUTF(msgout); //serverdan cliente mesaj gönderme
        } catch (IOException e) {
            System.out.println("Hata oluştu -> " + e);
        }

    }//GEN-LAST:event_msg_sendActionPerformed

    // Metin alanına tıklandığında veya odak geldiğinde içeriği temizleme işlemi burada tekrarlanmış,
    // Bu kısmın aynı işlevi yerine getirdiği için tekrar etmesine gerek yok.
    private void msg_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_textActionPerformed
        msg_text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                msg_text.setText(""); // Tıklandığında içeriği temizle
            }
        });
        msg_text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                msg_text.setText(""); // Odak geldiğinde içeriği temizle
            }
        });
    }//GEN-LAST:event_msg_textActionPerformed
    // End of variables declaration                   
}
