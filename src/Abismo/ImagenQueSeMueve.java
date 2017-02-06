package Abismo;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ImagenQueSeMueve extends JButton implements Runnable {

    JFrame ventana;
    boolean continuo;

    public ImagenQueSeMueve(JFrame vent) {
        this(vent, 0, 0);
    }

    public ImagenQueSeMueve(JFrame vent, int PosX, int PosY) {
        ImageIcon img = new ImageIcon("C:\\prueba\\raw.gif");
        this.setIcon(img);
        this.setSize(img.getIconWidth(), img.getIconHeight());
        this.setLocation(PosX, PosY);
        ventana = vent;
    }
    
    @Override
    public void run() {
        int posX = (int)this.getLocation().getX();
        int posY = (int)this.getLocation().getY();
        continuo = true;
        while (continuo) {
            
            while (this.getLocation().getY()+this.getSize().getHeight() < ventana.getSize().getHeight()
                    && this.getLocation().getX()+this.getSize().getWidth() < ventana.getSize().getWidth()) {
                posX++;
                posY++;
                this.setLocation(posX, posY);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            while (this.getLocation().getX() > 0 && this.getLocation().getY() > 0) {
                posX--;
                posY--;
                this.setLocation(posX, posY);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void para() {
        continuo = false;
    }

}
