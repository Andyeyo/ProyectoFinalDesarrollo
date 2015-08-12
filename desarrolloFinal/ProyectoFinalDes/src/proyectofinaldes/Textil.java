/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Textil.java
 *
 * Created on 30-jul-2015, 13:02:30
 */
package proyectofinaldes;

/**
 *
 * @author Andres
 */
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;


public class Textil extends javax.swing.JFrame {

    /** Creates new form Textil */
    DefaultTableModel model;
    public Textil() {
        initComponents();
        cargarTabla("");   
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(jTable1.getSelectedRow()!=-1)
                {
                    int fila = jTable1.getSelectedRow();
                    jTextField1.setText(jTable1.getValueAt(fila, 0).toString());
                    jTextField2.setText(jTable1.getValueAt(fila, 1).toString());
                    jTextField3.setText(jTable1.getValueAt(fila, 2).toString());
                    jTextField4.setText(jTable1.getValueAt(fila, 3).toString());
                    jTextField6.setText(jTable1.getValueAt(fila, 4).toString());
                    jTextField1.setEnabled(false);
                    jTextField2.setEnabled(true);
                    jTextField3.setEnabled(true);
                    jTextField4.setEnabled(true);
                    jTextField6.setEnabled(true);
                }
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }
    
    
    public void guardar(){
         if (jTextField1.getText().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Debe ingresar una cedula");
            jTextField1.requestFocus();
        }
        else if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el nombre");
            jTextField2.requestFocus();
        }
        else if (jTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la apellido");
            jTextField3.requestFocus();
        }
        else if (jTextField4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el sueldo");
            jTextField4.requestFocus();
        }
         else
        {
        Conexion cc = new Conexion();
        Connection cn = cc.conexion();
        String sql = "";
        String codigo, nombre, precio, stock, tall;
        codigo = jTextField1.getText();
        nombre = jTextField2.getText();
        precio = jTextField3.getText();
        stock = jTextField4.getText();
        tall=jTextField6.getText();
        sql = "insert into textil(codtex, nomtex, preunit, stockmed, taltex) values('"+codigo+"','"+nombre+"','"+precio+"','"+stock+"',"+tall+")";
        try {
            PreparedStatement psd = cn.prepareStatement(sql);
            int n = psd.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "se inserto correctamente");
                cargarTabla("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en el guardado");
        }
    }
    }
    
    public void cargarTabla(String dato)
    {
        String[]titulos={"CODIGO", "NOMBRE", "PRECIO UNITARIO", "STOCK", "TALLA"};
        String[]registros=new String[5];
        Conexion cc = new Conexion();
        Connection cn = cc.conexion();
        model=new DefaultTableModel(null, titulos);
        String sql="";
        sql="select * from textil where codtex like'%"+dato+"%' order by preunit";
        try {
            Statement psd = cn.createStatement();
            ResultSet rs=psd.executeQuery(sql);
            while (rs.next()){
                registros[0]=rs.getString("codtex");
                registros[1]=rs.getString("nomtex");
                registros[2]=String.valueOf((float) Math.rint(rs.getFloat("preunit")*100)/100);
                registros[3]=rs.getString("stockmed");
                registros[4]=rs.getString("taltex");
                model.addRow(registros);
            }
            jTable1.setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    

           
           
           public void actualizar(){
           if (jTextField1.getText().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Debe ingresar la cedula");
            jTextField1.requestFocus();
        }
        else if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la nombre");
            jTextField2.requestFocus();
        }
        else if (jTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la apellido");
            jTextField3.requestFocus();
        }
        else if (jTextField4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el sueldo");
            jTextField4.requestFocus();
        }
         else
        {
        Conexion cc = new Conexion();
        Connection cn = cc.conexion();
                String sql="";
                sql="update textil set nomtex='"+jTextField2.getText()+"',"
            + "preunit='"+jTextField3.getText()+"',"
            + "stockmed="+jTextField4.getText()+ ", taltex="+jTextField6.getText()+ " where codtex='"+jTextField1.getText()+"'";
        try {
                PreparedStatement psd=cn.prepareStatement(sql);
                int n=psd.executeUpdate();
                if(n>0)
                {
                    JOptionPane.showMessageDialog(null, "Se actualizo correctamente");
                    cargarTabla("");
                }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
           }
           
     public void borrar(){   
        if(JOptionPane.showConfirmDialog(null, "Estas seguro que deseas borrar el dato", "Borrar Registro", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
    {
    Conexion cc = new Conexion();
    Connection cn = cc.conexion();
    String sql="";
    sql="delete from textil where codtex='"+jTextField1.getText()+"'";
        try {
            PreparedStatement psd=cn.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0)
            {
              //  JOptionPane.showMessageDialog(null, "Se borro correctamente");
                cargarTabla("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        buttonTask1 = new org.edisoncor.gui.button.ButtonTask();
        buttonTask2 = new org.edisoncor.gui.button.ButtonTask();
        buttonTask3 = new org.edisoncor.gui.button.ButtonTask();
        buttonTask4 = new org.edisoncor.gui.button.ButtonTask();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        buttonTask5 = new org.edisoncor.gui.button.ButtonTask();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel1.setText("Codigo");

        jLabel4.setText("Stock");

        jLabel3.setText("Precio U.");

        jLabel2.setText("Nombre");

        jTextField5.setText("Buscar encargado");
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel10.setText("jLabel10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        buttonTask1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new_file.png"))); // NOI18N

        buttonTask2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/insertar.png"))); // NOI18N
        buttonTask2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTask2ActionPerformed(evt);
            }
        });

        buttonTask3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        buttonTask3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTask3ActionPerformed(evt);
            }
        });

        buttonTask4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/remove.png"))); // NOI18N
        buttonTask4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTask4ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel5.setText("Nuevo");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel6.setText(" Insertar");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel7.setText("Eliminar");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel8.setText("Actualizar");

        buttonTask5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancel.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel9.setText("Cancelar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonTask1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buttonTask2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(buttonTask5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonTask3, 0, 0, Short.MAX_VALUE)
                            .addComponent(buttonTask4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTask1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonTask3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(buttonTask2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonTask4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addContainerGap(57, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTask5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained
// TODO add your handling code here:
    jTextField5.setText("");
}//GEN-LAST:event_jTextField5FocusGained

private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
// TODO add your handling code here:
    if(jTextField5.getText().isEmpty()){
        jTextField5.setText("Buscar encargado");
    }
}//GEN-LAST:event_jTextField5FocusLost

private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_jTextField5KeyPressed

private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
// TODO add your handling code here:
        cargarTabla(jTextField5.getText());
}//GEN-LAST:event_jTextField5KeyReleased

private void buttonTask2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTask2ActionPerformed
// TODO add your handling code here:
    guardar();  
}//GEN-LAST:event_buttonTask2ActionPerformed

private void buttonTask3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTask3ActionPerformed
// TODO add your handling code here:
    actualizar();
}//GEN-LAST:event_buttonTask3ActionPerformed

private void buttonTask4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTask4ActionPerformed
// TODO add your handling code here:
    borrar();
}//GEN-LAST:event_buttonTask4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Textil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Textil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Textil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Textil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Textil().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonTask buttonTask1;
    private org.edisoncor.gui.button.ButtonTask buttonTask2;
    private org.edisoncor.gui.button.ButtonTask buttonTask3;
    private org.edisoncor.gui.button.ButtonTask buttonTask4;
    private org.edisoncor.gui.button.ButtonTask buttonTask5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
