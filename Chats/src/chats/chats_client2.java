package chats;

import static chats.chats_client.din;
import static chats.chats_client.dout;
import static chats.chats_client.s;
import database.Logger;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class chats_client2 extends javax.swing.JFrame {

    public chats_client2() {
        initComponents();
        clientSocket();  // İstemci soketini başlat
        attachClearListeners(); // clear listeners'ı ekle

        // İstemciden gelen mesajları almak için yeni bir thread başlat
        Thread messageReceiverThread = new Thread(this::receiveMessages);
        messageReceiverThread.start();
    }

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

    private void clientSocket() {
        try {
            s = new Socket("127.0.0.1", 1201); // Sunucuya bağlanmak için soket oluştur
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

        } catch (IOException e) {
            System.out.println("Hata oluştu -> " + e);
        }
    }

    // İstemciden gelen mesajları dinlemek için metot
    private void receiveMessages() {
        try {
            String msgin;
            while (true) {
                msgin = din.readUTF(); // Sunucudan gelen mesajı oku
                // Gelen mesajı JTextArea'ya ekle
                addMessageToTextArea("Server: " + msgin);
            }
        } catch (IOException e) {
            System.out.println("Hata oluştu -> " + e);
        }
        try {
            Thread messageReceiverThread1 = new Thread(() -> {
                try {
                    String msgin;
                    while (true) {
                        msgin = din.readUTF();
                        addMessageToTextArea("Server : " + msgin);
                    }
                } catch (Exception e) {
                    System.out.println("Hata oluştu -> " + e);
                }
            });
            messageReceiverThread1.start();
        } catch (Exception e) {
        }
    }

    private void addMessageToTextArea(String message) {
        java.awt.EventQueue.invokeLater(() -> {
            msg_area.append(message + "\n"); // Mesajı JTextArea'ya ekle
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

        jLabel1.setText("Client 2");

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

    private void msg_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msg_sendActionPerformed

        try {
            String msgin = "";
            String msgout = msg_text.getText().trim();
            msg_area.setText(msg_area.getText().trim() + "\n Client 2 : " + msgout + "\n");
            dout.writeUTF(msgout); //serverdan cliente mesaj gönderme
            Logger.logMessage(msgout, "client_2");
            msg_text.setText("");
        } catch (Exception e) {
            System.out.println("Hata oluştu -> " + e);
        }
    }//GEN-LAST:event_msg_sendActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new chats_client2().setVisible(true);  // İstemci 2 penceresini görünür yap
        });

        try {
            s = new Socket("127.0.0.1", 1201); // Sunucuya bağlanmak için soket oluştur
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            String msgin = "";
            while (!msgin.equals("exit")) {
                msgin = din.readUTF();
                msg_area.setText(msg_area.getText().trim() + "\n Server :" + msgin);
            }
        } catch (IOException e) {
            System.out.println("Hata oluştu -> " + e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea msg_area;
    private javax.swing.JButton msg_send;
    private javax.swing.JTextField msg_text;
    // End of variables declaration//GEN-END:variables
}
