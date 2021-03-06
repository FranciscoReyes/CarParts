/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.ventanas;

import LengthDocument.MaxLengthDocument;
import buscaRecambios.Listas.Marcas;
import buscaRecambios.Listas.Modelos;
import buscaRecambios.entity.Marca;
import buscaRecambios.entity.Modelo;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import listRenders.MarcaListRenderer;

/**
 *
 * @author Usuario
 */
public class AñadirCoche extends javax.swing.JDialog {

    private static Marcas listamarcas;
    private static Modelos listamodelos;
    EntityManager e = CarPartsWindow.entityManager;

    Marca marca;
    private String fueltype;

    /**
     * Creates new form AñadirCoche
     */
    public AñadirCoche(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Query q1 = e.createNamedQuery("Marca.findAll");
        listamarcas = new Marcas(q1.getResultList());
        jComboBox1.setModel(new DefaultComboBoxModel(listamarcas.getListaMarcas().toArray()));
        jComboBox1.setRenderer(new MarcaListRenderer());

        System.out.println(modeloField.getText());
        
        newMarcaField.setDocument(new MaxLengthDocument(newMarcaField.getColumns()));
        modeloField.setDocument(new MaxLengthDocument(modeloField.getColumns()));
        ccField.setDocument(new MaxLengthDocument(ccField.getColumns()));
        cvField.setDocument(new MaxLengthDocument(cvField.getColumns()));
        añoField.setDocument(new MaxLengthDocument(añoField.getColumns()));
    }

    public static void setListas(Modelos listamodelos, Marcas listamarcas) {
        AñadirCoche.listamarcas = listamarcas;
        AñadirCoche.listamodelos = listamodelos;
    }

    private String checkFuel(int index) {
        switch (index) {
            case 0:
                this.fueltype = "Gasolina";
                break;
            case 1:
                this.fueltype = "Diésel";
                break;
            case 2:
                this.fueltype = "Híbrido";
                break;
            case 3:
                this.fueltype = "Eléctrico";
                break;
        }
        return fueltype;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        Insertar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        newMarca = new javax.swing.JButton();
        newMarcaField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        modeloField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ccField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cvField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        añoField = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel2.setText("Insertar datos modelo:");

        Insertar.setText("Insertar");
        Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertarActionPerformed(evt);
            }
        });

        jLabel1.setText("Marca:");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "" }));

        newMarca.setText("Nueva marca:");
        newMarca.setToolTipText("Al pulsar añade una marca");
        newMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMarcaActionPerformed(evt);
            }
        });

        newMarcaField.setColumns(20);
        newMarcaField.setToolTipText("insertar nombre marca...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(newMarca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newMarcaField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newMarca)
                    .addComponent(newMarcaField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel3.setText("Modelo:");

        modeloField.setColumns(20);
        modeloField.setToolTipText("insertar modelo + variante");

        jLabel4.setText("c.c:");

        ccField.setColumns(4);
        ccField.setToolTipText("insertar cilindrada (Ej: 1900)");

        jLabel5.setText("cv:");

        cvField.setColumns(4);
        cvField.setToolTipText("insertar Cv del modelo");

        jLabel6.setText("año:");

        añoField.setColumns(4);
        añoField.setToolTipText("año de construcción");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gasolina", "Diésel", "Híbrido", "Eléctrico" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modeloField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ccField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cvField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(añoField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(modeloField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(ccField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cvField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(añoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(159, 159, 159))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Insertar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Insertar)
                    .addComponent(jButton1))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarActionPerformed
        if (!e.getTransaction().isActive()) {
            e.getTransaction().begin();
        }
        try {
        //Crea objeto modelo y añade datos de los textfield. Añade datos Marca desde ComboBox2
        Modelo modelo = new Modelo(null, modeloField.getText(), Integer.valueOf(ccField.getText()),
                Integer.valueOf(cvField.getText()), añoField.getText());
        modelo.setIdMarca((Marca) jComboBox1.getSelectedItem());

        modelo.setCombustible(this.checkFuel(jComboBox2.getSelectedIndex()));
              
        e.persist(modelo);

        listamodelos.addModelo(modelo);

//        jComboBox1.setModel(new DefaultComboBoxModel(listamarcas.getListaMarcas().toArray()));
//        jComboBox1.setRenderer(new MarcaListRenderer());

        e.getTransaction().commit();
        JOptionPane.showMessageDialog(this, "Se ha insertado el modelo con éxito", "Correcto", 
                JOptionPane.INFORMATION_MESSAGE);
        newMarcaField.setText(null);
        modeloField.setText(null);
        ccField.setText(null);
        cvField.setText(null);
        añoField.setText(null);
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos del modelo incompletos", "Cuidado", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_InsertarActionPerformed

    private void newMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMarcaActionPerformed
        if (!e.getTransaction().isActive()){
            e.getTransaction().begin();
        }
        
        marca = new Marca(null, newMarcaField.getText());
        if (!newMarcaField.getText().isEmpty()) {
            e.persist(marca);
            listamarcas.addMarca(marca);

            jComboBox1.setModel(new DefaultComboBoxModel(listamarcas.getListaMarcas().toArray()));
            jComboBox1.setRenderer(new MarcaListRenderer());
            jComboBox1.setSelectedIndex(listamarcas.getListaMarcas().size()-1);
            
            e.getTransaction().commit();
        } else {
            JOptionPane.showMessageDialog(this, "Introduce el nombre de Marca", "Error", JOptionPane.WARNING_MESSAGE);
            e.getTransaction().rollback();
        }


    }//GEN-LAST:event_newMarcaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (e.getTransaction().isActive()) {
            e.getTransaction().rollback();
        }
        
        modeloField.setText(null);
        ccField.setText(null);
        cvField.setText(null);
        añoField.setText(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if(e.getTransaction().isActive()) {
            e.getTransaction().rollback();
        }     
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(AñadirCoche.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AñadirCoche.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AñadirCoche.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AñadirCoche.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AñadirCoche dialog = new AñadirCoche(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Insertar;
    private javax.swing.JTextField añoField;
    private javax.swing.JTextField ccField;
    private javax.swing.JTextField cvField;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField modeloField;
    private javax.swing.JButton newMarca;
    private javax.swing.JTextField newMarcaField;
    // End of variables declaration//GEN-END:variables
}
