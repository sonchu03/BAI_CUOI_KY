/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zSERVER;

import PACKAGES.ComputerTableModel;
import PACKAGES.PacketRemoteDesktop;
import PACKAGES.PacketTheoDoiClient;
import PACKAGES.PacketTruyenFile;
import UTILS.DataUtils;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author sonchubeo
 */
public class testServerGui extends javax.swing.JFrame implements Runnable{
        
    private final int mainThreadPortNumber = 999;
    private final int remoteDesktopThreadPortNumber = 998;
    private final int theoDoiClientThreadPortNumber = 997;
    private final int fileTransferThreadPortNumber = 996;
    
    Timer timerUpdateListSocket;
    private int timeUpdateTable = 5; // second
    public testServerGui() {
        initComponents();
        setLocation(300, 100);
        tbComputerInfo.setModel(new ComputerTableModel(new ArrayList()));
        tbComputerInfo.getColumnModel().getColumn(0).setMaxWidth(100);
        setVisible(true);
        // Cập nhật list socket sau mỗi timeUpdateTable giây
        timerUpdateListSocket = new Timer();
        timerUpdateListSocket.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getTbModel().updateAllElement();
            }
        }, timeUpdateTable * 1000, timeUpdateTable * 1000);
        // server lắng nghe remote desktop
        runThreadRemoteDesktop(); 
        // server lắng nghe Gửi/ nhận file
        runThreadTransferFile();
        // server lắng nghe theo dõi client
        runThreadTheoDoiClient();
    }
    
    private ComputerTableModel getTbModel() {
        return (ComputerTableModel) tbComputerInfo.getModel();
    }


    @Override
    public void run() {
       // chat, Gửi thông điệp, Gửi lệnh shell
       try {
            final ServerSocket server = new ServerSocket(mainThreadPortNumber);
            // Phục vụ nhiều client
            while (true) {
                Socket socket;
                try {
                    // Nếu không dùng thread
                    // chương trình sẽ chờ 1 client đầu tiên ở đây
                    socket = server.accept();
                    getTbModel().addElement(socket);
                    System.out.println("Server: Đã kết nối client thứ "
                            + getTbModel().getSize());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }   
    }
     
    //<editor-fold defaultstate="collapsed" desc="Thread transfer file">
    private void runThreadTransferFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ServerSocket server = new ServerSocket(fileTransferThreadPortNumber);
                    // Phục vụ nhiều client
                    while (true) {
                        Socket socket;
                        try {
                            socket = server.accept();
                            new Thread(new FrmGoiFile(socket)).start();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Thread remote desktop">

    private void runThreadRemoteDesktop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ServerSocket server = new ServerSocket(remoteDesktopThreadPortNumber);
                    // Phục vụ nhiều client
                    while (true) {
                        Socket socket;
                        try {
                            socket = server.accept();
                            new Thread(new FrmRemoteDesktop(socket)).start();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Thread theo dõi client">
    private void runThreadTheoDoiClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final ServerSocket server = new ServerSocket(theoDoiClientThreadPortNumber);
                    // Phục vụ nhiều client
                    while (true) {
                        Socket socket;
                        try {
                            socket = server.accept();
                            new Thread(new FrmTheoDoiClient(socket)).start();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    //</editor-fold>
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    jToolBar1 = new javax.swing.JToolBar();
    btnChatClient = new javax.swing.JButton();
    btnGoiThongDiep = new javax.swing.JButton();
    btnTruyenTapTin = new javax.swing.JButton();
    jButtonTheoDoiClient = new javax.swing.JButton();
    jButtonChupHinhClient = new javax.swing.JButton();
    jButtonRemoteDesktop = new javax.swing.JButton();
    jButtonGoiLenhShell = new javax.swing.JButton();
    jTabbedPane1 = new javax.swing.JTabbedPane();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tbComputerInfo = new javax.swing.JTable();
    jPanel2 = new javax.swing.JPanel();
    lblTrangThai = new javax.swing.JLabel();
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jToolBar1.setRollover(true);

    // Set icons and text for toolbar buttons
    btnChatClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/chat_client.png")));
    btnChatClient.setText("Trò chuyện");
    btnChatClient.setFocusable(false);
    btnChatClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnChatClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(btnChatClient);

    btnGoiThongDiep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/send_mes.png")));
    btnGoiThongDiep.setText("Gởi thông điệp");
    btnGoiThongDiep.setFocusable(false);
    btnGoiThongDiep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnGoiThongDiep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(btnGoiThongDiep);

    btnTruyenTapTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/file_transfer.png")));
    btnTruyenTapTin.setText("Truyền tập tin");
    btnTruyenTapTin.setFocusable(false);
    btnTruyenTapTin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnTruyenTapTin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(btnTruyenTapTin);

    jButtonTheoDoiClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/tracking-computer.png")));
    jButtonTheoDoiClient.setText("Theo dõi client");
    jButtonTheoDoiClient.setFocusable(false);
    jButtonTheoDoiClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonTheoDoiClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(jButtonTheoDoiClient);

    jButtonChupHinhClient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/screenshot.png")));
    jButtonChupHinhClient.setText("Chụp hình client");
    jButtonChupHinhClient.setFocusable(false);
    jButtonChupHinhClient.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonChupHinhClient.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(jButtonChupHinhClient);

    jButtonRemoteDesktop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/remote.png")));
    jButtonRemoteDesktop.setText("Điều khiển client");
    jButtonRemoteDesktop.setFocusable(false);
    jButtonRemoteDesktop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonRemoteDesktop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(jButtonRemoteDesktop);

    jButtonGoiLenhShell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RES/terminal.png")));
    jButtonGoiLenhShell.setText("Gởi lệnh Shell");
    jButtonGoiLenhShell.setFocusable(false);
    jButtonGoiLenhShell.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonGoiLenhShell.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(jButtonGoiLenhShell);

    // Add toolbar to the frame
    getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

    tbComputerInfo.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "STT", "Tên máy", "IP"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane1.setViewportView(tbComputerInfo);

    // Add computer information table to the panel
    jPanel1.setLayout(new java.awt.BorderLayout());
    jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

    // Add status label to the panel
    jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    jPanel2.add(lblTrangThai);

    jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

    // Add the panel to the tabbed pane
    jTabbedPane1.addTab("Thông tin máy tính trong mạng LAN", jPanel1);

    // Add menu item to the menu
    jMenu1.setText("Quản lý");
    jMenuItem1.setText("Thoát");
    jMenu1.add(jMenuItem1);
    jMenuBar1.add(jMenu1);
    setJMenuBar(jMenuBar1);

    // Set layout of the frame
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(jTabbedPane1, BorderLayout.CENTER);

    pack();
}

    

    private void btnChatClientActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để chat!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Mở form chat với client đã chọn
        new Thread(new FrmChatVoiClient(mayClient)).start();
    }                                             
    
    private void btnGoiThongDiepActionPerformed(java.awt.event.ActionEvent evt) {                                                
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để Gửi!");
            return;
        }
        int[] rowsSelected = tbComputerInfo.getSelectedRows();
        List<Socket> dsMayClient = new ArrayList();
        for (int i : rowsSelected) {
            dsMayClient.add(getTbModel().getItem(i));
        }
        // Mở form chat với các client đã chọn
        FrmGoiThongDiep goiThongDiep = new FrmGoiThongDiep(dsMayClient);
        goiThongDiep.setVisible(true);
    }                                               
   
    private void btnTruyenTapTinActionPerformed(java.awt.event.ActionEvent evt) {                                                
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane,
                    "Bạn chưa chọn máy để Gửi file!");
            return;
        }
        Socket mayClient =
                getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Gửi lệnh yêu cầu client kết nối đến socket server file transfer
        PacketTruyenFile packetTruyenFile = new PacketTruyenFile();
        packetTruyenFile.setCmd(PacketTruyenFile.CMD_KHOIDONG);
        packetTruyenFile.setMessage(String.valueOf(fileTransferThreadPortNumber));
        DataUtils.goiDuLieu(mayClient, packetTruyenFile.toString()); 
    }                                               
    
    private void jButtonGoiLenhShellActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để Gửi lệnh shell!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Mở form Gửi lệnh shell đến client đã chọn
        FrmGoiLenhShell goiLenhShell = new FrmGoiLenhShell(mayClient);
        goiLenhShell.khoiDong();
        goiLenhShell.setVisible(true);
    }                                                   
    
    private void jButtonRemoteDesktopActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để điều khiển!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Gửi lệnh yêu cầu client kết nối đến socket server remote desktop
        PacketRemoteDesktop pk = new PacketRemoteDesktop();
        pk.setCmd(PacketRemoteDesktop.CMD_KHOIDONG);
        pk.setMessage(String.valueOf(remoteDesktopThreadPortNumber));
        DataUtils.goiDuLieu(mayClient, pk.toString());
    }                                                    
    
    private void jButtonTheoDoiClientActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để điều khiển!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Gửi lệnh yêu cầu client kết nối đến socket server remote desktop
        PacketTheoDoiClient pk = new PacketTheoDoiClient();
        pk.setCmd(PacketTheoDoiClient.CMD_KHOIDONG);
        pk.setMessage(String.valueOf(theoDoiClientThreadPortNumber));
        DataUtils.goiDuLieu(mayClient, pk.toString());
        
    }                                                    

    private void jButtonChupHinhClientActionPerformed(java.awt.event.ActionEvent evt) {                                                      
         if (tbComputerInfo.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn máy để chụp hình!");
            return;
        }
        Socket mayClient = getTbModel().getItem(tbComputerInfo.getSelectedRow());
        // Mở form chụp hình với client đã chọn
        new Thread(new FrmChupHinhClient(mayClient)).start();
    }                                                     

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnChatClient;
    private javax.swing.JButton btnGoiThongDiep;
    private javax.swing.JButton btnTruyenTapTin;
    private javax.swing.JButton jButtonChupHinhClient;
    private javax.swing.JButton jButtonGoiLenhShell;
    private javax.swing.JButton jButtonRemoteDesktop;
    private javax.swing.JButton jButtonTheoDoiClient;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JTable tbComputerInfo;
}
