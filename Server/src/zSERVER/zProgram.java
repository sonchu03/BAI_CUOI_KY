package zSERVER;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class zProgram {
    public static void main(String[] args) {
        new Thread(new FrmServerGUI()).start();
    }
}
