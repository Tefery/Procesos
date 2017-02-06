package Abismo;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author DIURNO_2
 */
public class Reloj extends Thread {
    
    String textoReloj;
    Calendar hora;
    JLabel label;
    boolean continuo;
    
    public Reloj(JLabel texto) {
        label = texto;
    }
    
    public void run() {
        continuo = true;
        while(continuo) {
        hora = Calendar.getInstance();
            try {
                textoReloj = "    " + hora.get(Calendar.HOUR_OF_DAY) + ":" + hora.get(Calendar.MINUTE) + ":" + hora.get(Calendar.SECOND);
                
                label.setText(textoReloj);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
