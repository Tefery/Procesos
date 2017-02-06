package Abismo;

import java.awt.event.ActionEvent;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DIURNO_2
 */
@SuppressWarnings("serial")
public class ImagenEnMovimiento extends javax.swing.JFrame {

    boolean estaParado;
    LinkedList<Thread> hilos;
    LinkedList<ImagenQueSeMueve> imagenes;

    public ImagenEnMovimiento() {

        hilos = new LinkedList<Thread>();
        imagenes = new LinkedList<ImagenQueSeMueve>();
        estaParado = false;
        ImagenQueSeMueve jake = new ImagenQueSeMueve(this);
        jake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminaImagen(evt);
            }
        });
        initComponents();
        Reloj reloj = new Reloj(JLabelHora);
        reloj.start();
        getContentPane().add(jake);
        Thread mov = new Thread(jake);
        hilos.add(mov);
        imagenes.add(jake);
        mov.start();
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnParaContinuar = new javax.swing.JButton();
        JLabelHora = new javax.swing.JLabel();
        BtnAniadir = new javax.swing.JButton();
        BtnBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnParaContinuar.setText("PARAR");
        BtnParaContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnParaContinuarActionPerformed(evt);
            }
        });

        JLabelHora.setText("HORA");

        BtnAniadir.setText("A\u00F1adir Imagen");
        BtnAniadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAniadirActionPerformed(evt);
            }
        });

        BtnBorrar.setText("Quitar Imagen");
        BtnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnParaContinuar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JLabelHora))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnAniadir)
                            .addComponent(BtnBorrar))
                        .addGap(0, 570, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnParaContinuar)
                    .addComponent(JLabelHora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnAniadir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnBorrar)
                .addContainerGap(530, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("deprecation")
	private void BtnParaContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnParaContinuarActionPerformed
        if (!estaParado) {
            estaParado = true;
            for (Thread t : hilos) {
                t.suspend();
            }
            BtnParaContinuar.setText("CONTINUAR");
        } else {
            estaParado = false;
            for (Thread t : hilos) {
                t.resume();
            }
            BtnParaContinuar.setText("PARAR");
        }
    }//GEN-LAST:event_BtnParaContinuarActionPerformed

    private void BtnAniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAniadirActionPerformed

        ImagenQueSeMueve imagen = new ImagenQueSeMueve(this, (int) (Math.random() * this.getSize().getHeight()), (int) (Math.random() * this.getSize().getWidth()));
        getContentPane().add(imagen);
        Thread movi = new Thread(imagen);
        hilos.add(movi);
        imagenes.add(imagen);
        imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminaImagen(evt);
            }
        });
        movi.start();
    }//GEN-LAST:event_BtnAniadirActionPerformed

    @SuppressWarnings("deprecation")
	private void eliminaImagen(ActionEvent evt) {
        ImagenQueSeMueve im = (ImagenQueSeMueve) evt.getSource();
        for (int i = 0; i < imagenes.size(); i++) {
            if (imagenes.get(i).equals(im)) {
                hilos.get(i).stop();
                hilos.remove(i);
                imagenes.remove(i);
                remove(im);
                repaint();
                break;
            }
        }
    }

    @SuppressWarnings("deprecation")
	private void BtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBorrarActionPerformed
        int tamanio = imagenes.size();
        if (tamanio > 0) {
            hilos.get(0).stop();
            hilos.removeFirst();
            this.remove(imagenes.get(0));
            imagenes.removeFirst();
            repaint();
        }
    }//GEN-LAST:event_BtnBorrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImagenEnMovimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAniadir;
    private javax.swing.JButton BtnBorrar;
    private javax.swing.JButton BtnParaContinuar;
    private javax.swing.JLabel JLabelHora;
    // End of variables declaration//GEN-END:variables
}
