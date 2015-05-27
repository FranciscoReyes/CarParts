/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaRecambios.ventanas;

import buscaRecambios.Listas.Marcas;
import buscaRecambios.Listas.Modelos;
import buscaRecambios.Listas.Piezas;
import buscaRecambios.Listas.TiposPieza;
import buscaRecambios.Renders.AñoMODELORenderer;
import buscaRecambios.Renders.CcMODELORenderer;
import buscaRecambios.Renders.CombustibleMODELORenderer;
import buscaRecambios.Renders.CvMODELORenderer;
import buscaRecambios.Renders.DescPIEZA_Renderer;
import buscaRecambios.Renders.IdMARCA_Renderer;
import buscaRecambios.Renders.IdMODELORenderer;
import buscaRecambios.Renders.IdPIEZA_Renderer;
import buscaRecambios.Renders.MarcaMARCA_Renderer;
import buscaRecambios.Renders.MarcaMODELORenderer;
import buscaRecambios.Renders.ModeloMODELORenderer;
import buscaRecambios.Renders.ModeloPIEZA_Renderer;
import buscaRecambios.Renders.TPiezaPIEZA_Renderer;
import buscaRecambios.Renders.nombrePIEZA_Renderer;
import buscaRecambios.TableModels.TableModelMarcas;
import buscaRecambios.TableModels.TableModelModelos;
import buscaRecambios.TableModels.TableModelPiezas;
import buscaRecambios.entity.Marca;
import buscaRecambios.entity.Modelo;
import buscaRecambios.entity.Pieza;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import listRenders.MarcaListRenderer;
import listRenders.TiposListRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Usuario
 */
public class MostradorPiezas extends javax.swing.JDialog {

    private static Modelo idModelo;
    private static Piezas listapiezas;

    private Marcas listamarcas;
    private Modelos listamodelos;
    private TiposPieza listatipPieza;
    EntityManager e;

    Query query1;
    Query query2;
    Query query3;
    Query query4;

    TableModelMarcas tablemarca;
    TableModelModelos tablemodelos;
    TableModelPiezas tablemodel;

    private static int index;

    Connection connection;

    /**
     * Creates new form MostradorPiezas
     */
    public MostradorPiezas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        areaDetailsPieza.setVisible(false);

        e = CarPartsWindow.entityManager;

        jTabbedPane1.setSelectedIndex(index);

        //Tabla con piezas
        query1 = e.createQuery("SELECT p FROM Pieza p WHERE p.idModelo = :id").setParameter(
                "id", MostradorPiezas.idModelo);
        listapiezas = new Piezas(query1.getResultList());
        tablemodel = new TableModelPiezas(listapiezas);
        jTable1.setModel(tablemodel);

        jTable1.getColumnModel().getColumn(0).setCellRenderer(new IdPIEZA_Renderer());
        jTable1.getColumnModel().getColumn(1).setCellRenderer(new ModeloPIEZA_Renderer());
        jTable1.getColumnModel().getColumn(2).setCellRenderer(new nombrePIEZA_Renderer());
        jTable1.getColumnModel().getColumn(3).setCellRenderer(new TPiezaPIEZA_Renderer());
        jTable1.getColumnModel().getColumn(4).setCellRenderer(new DescPIEZA_Renderer());

        // Tabla con marcas
        query2 = e.createNamedQuery("Marca.findAll");
        listamarcas = new Marcas(query2.getResultList());

        tablemarca = new TableModelMarcas(listamarcas);
        jTable2.setModel(tablemarca);

        jTable2.getColumnModel().getColumn(0).setCellRenderer(new IdMARCA_Renderer());
        jTable2.getColumnModel().getColumn(1).setCellRenderer(new MarcaMARCA_Renderer());

        //Tabla con modelos
        query3 = e.createNamedQuery("Modelo.findAll");
        listamodelos = new Modelos(query3.getResultList());

        tablemodelos = new TableModelModelos(listamodelos);
        jTable3.setModel(tablemodelos);

        jTable3.getColumnModel().getColumn(0).setCellRenderer(new IdMODELORenderer());
        jTable3.getColumnModel().getColumn(1).setCellRenderer(new MarcaMODELORenderer());
        jTable3.getColumnModel().getColumn(2).setCellRenderer(new ModeloMODELORenderer());
        jTable3.getColumnModel().getColumn(3).setCellRenderer(new CcMODELORenderer());
        jTable3.getColumnModel().getColumn(4).setCellRenderer(new CvMODELORenderer());
        jTable3.getColumnModel().getColumn(5).setCellRenderer(new AñoMODELORenderer());
        jTable3.getColumnModel().getColumn(6).setCellRenderer(new CombustibleMODELORenderer());

        query4 = e.createNamedQuery("TipoPieza.findAll");
        listatipPieza = new TiposPieza(query4.getResultList());

        // Introducir ComboBox en tabla Modelo con Marcas
        TableColumn marcaColumn = jTable3.getColumnModel().getColumn(1);
        JComboBox comboBoxCellMarca = new JComboBox();
        comboBoxCellMarca.setModel(new DefaultComboBoxModel(listamarcas.getListaMarcas().toArray()));
        comboBoxCellMarca.setRenderer(new MarcaListRenderer());
        marcaColumn.setCellEditor(new DefaultCellEditor(comboBoxCellMarca));

        // Introducir ComboBox en tabla piezas con TiposPiezas
        TableColumn tipoColumn = jTable1.getColumnModel().getColumn(3);
        JComboBox comboBoxCellTPieza = new JComboBox();
        comboBoxCellTPieza.setModel(new DefaultComboBoxModel(listatipPieza.getListaTipoPiezas().toArray()));
        comboBoxCellTPieza.setRenderer(new TiposListRenderer());
        tipoColumn.setCellEditor(new DefaultCellEditor(comboBoxCellTPieza));

    }

    public static void setModelo(Modelo idModelo) {
        MostradorPiezas.idModelo = idModelo;
    }

    public static void setMainPanelbyIndex(int index) {
        MostradorPiezas.index = index;

    }

    public void showDetails() {
        int indexSelectedRow = jTable1.getSelectedRow();
        if (indexSelectedRow < 0) {
            areaDetailsPieza.setText("Sin selección");
        } else {

            areaDetailsPieza.setText("\nMarca: " + listapiezas.getListaPieza().get(indexSelectedRow).getIdModelo().getIdMarca().getMarca()
                    + "\nModelo: " + listapiezas.getListaPieza().get(indexSelectedRow).getIdModelo().getModelo() + "\nNombre: "
                    + listapiezas.getListaPieza().get(indexSelectedRow).getNombre() + "\nTipo: "
                    + listapiezas.getListaPieza().get(indexSelectedRow).getIdTipoPieza().getTipo() + "\nDescripción: "
                    + listapiezas.getListaPieza().get(indexSelectedRow).getDescrip());
        }
    }

    private void refreshLists() {
        query1 = e.createQuery("SELECT p FROM Pieza p WHERE p.idModelo = :id").setParameter(
                "id", MostradorPiezas.idModelo);
        listapiezas = new Piezas(query1.getResultList());
        tablemodel = new TableModelPiezas(listapiezas);
        jTable1.setModel(tablemodel);

        query2 = e.createNamedQuery("Marca.findAll");
        listamarcas = new Marcas(query2.getResultList());
        tablemarca = new TableModelMarcas(listamarcas);
        jTable2.setModel(tablemarca);

        query3 = e.createNamedQuery("Modelo.findAll");
        listamodelos = new Modelos(query3.getResultList());
        tablemodelos = new TableModelModelos(listamodelos);
        jTable3.setModel(tablemodelos);
    }

    public void jdbcQuery(String consult) {
        //Conexion Base de datos con JDBC. Probando sacando las marcas
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("Error Driver");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/carparts", "root", null);
        } catch (Exception e1) {
            System.out.println("Error connection");
        }

        Statement sentencia;
        try {
            sentencia = connection.createStatement();
            ResultSet rs = sentencia.executeQuery("SELECT * FROM Marca");
            while (rs.next()) {
                String marcasql = rs.getString("Marca");
                int id = rs.getInt("Id");
                System.out.println(marcasql);
                System.out.println(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarPartsWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SaveChangesPieza = new javax.swing.JButton();
        DeletePieza = new javax.swing.JButton();
        ShowAllPieza = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaDetailsPieza = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        SaveChangesMarca = new javax.swing.JButton();
        DeleteMarca = new javax.swing.JButton();
        showPDF = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        SaveChangesModelo = new javax.swing.JButton();
        DeleteModelo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        SaveChangesPieza.setText("Guardar Cambios");
        SaveChangesPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveChangesPiezaActionPerformed(evt);
            }
        });

        DeletePieza.setText("Eliminar");
        DeletePieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletePiezaActionPerformed(evt);
            }
        });

        ShowAllPieza.setText("Mostrar Todo");
        ShowAllPieza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllPiezaActionPerformed(evt);
            }
        });

        areaDetailsPieza.setEditable(false);
        areaDetailsPieza.setColumns(20);
        areaDetailsPieza.setLineWrap(true);
        areaDetailsPieza.setRows(5);
        jScrollPane2.setViewportView(areaDetailsPieza);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(SaveChangesPieza)
                        .addGap(227, 227, 227)
                        .addComponent(DeletePieza))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ShowAllPieza)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ShowAllPieza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(SaveChangesPieza))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(DeletePieza)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("ListaPiezas", jPanel3);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable2);

        SaveChangesMarca.setText("Guardar Cambios");
        SaveChangesMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveChangesMarcaActionPerformed(evt);
            }
        });

        DeleteMarca.setText("Eliminar marca");
        DeleteMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteMarcaActionPerformed(evt);
            }
        });

        showPDF.setText("Mostrar PDF");
        showPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SaveChangesMarca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DeleteMarca))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(showPDF)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showPDF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveChangesMarca)
                    .addComponent(DeleteMarca))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Listado Marcas", jPanel1);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        SaveChangesModelo.setText("Guardar Cambios");
        SaveChangesModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveChangesModeloActionPerformed(evt);
            }
        });

        DeleteModelo.setText("Eliminar Modelo");
        DeleteModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteModeloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(SaveChangesModelo)
                        .addGap(18, 404, Short.MAX_VALUE)
                        .addComponent(DeleteModelo))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveChangesModelo)
                    .addComponent(DeleteModelo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Listado Modelos", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);

    }//GEN-LAST:event_formWindowClosing

    private void SaveChangesMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChangesMarcaActionPerformed
        //Preguntar con OptionPane su decisión y recogemos el resultado 
        int decision = JOptionPane.showConfirmDialog(this, "Estas seguro que quieres guardar los cambios",
                "Un momento...", JOptionPane.YES_NO_OPTION);
        if (!e.getTransaction().isActive()) {
            e.getTransaction().begin();
        }

        if (decision == JOptionPane.YES_OPTION) {
            if (!jTable2.isEditing()) {
                e.getTransaction().commit();
                tablemarca.fireTableDataChanged();
                this.refreshLists();
                this.setVisible(false);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Confirma cambios con ENTER", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }

        } else {
            if (decision == JOptionPane.NO_OPTION) {
                e.getTransaction().rollback();
                this.refreshLists();
            }
        }

    }//GEN-LAST:event_SaveChangesMarcaActionPerformed

    private void DeleteMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteMarcaActionPerformed
        //Preguntar con OptionPane su decisión y recogemos el resultado
        int result = JOptionPane.showConfirmDialog(this, "¿Estás seguro? Se eliminarán los modelos \ny piezas correspondientes",
                "Eliminar marca: " + listamarcas.getMarcaByIndex(jTable2.getSelectedRow()).getMarca(),
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (!e.getTransaction().isActive()) {
                e.getTransaction().begin();
            }
            Marca marca = listamarcas.getMarcaByIndex(jTable2.getSelectedRow());
            e.remove(marca);
            e.getTransaction().commit();
            tablemodelos.fireTableRowsDeleted(jTable2.getSelectedRow(), jTable2.getSelectedRow());
            tablemarca.fireTableRowsDeleted(jTable2.getSelectedRow(), jTable2.getSelectedRow());
            this.refreshLists();
            this.setVisible(false);
            this.dispose();
        } else {
            if (result == JOptionPane.CANCEL_OPTION) {
                if (e.getTransaction().isActive()) {
                    e.getTransaction().rollback();
                    this.refreshLists();
                }
            }
        }


    }//GEN-LAST:event_DeleteMarcaActionPerformed

    private void SaveChangesModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChangesModeloActionPerformed
        //Preguntar con OptionPane su decisión y recogemos el resultado
        int decision = JOptionPane.showConfirmDialog(this, "Estas seguro que quieres guardar los cambios",
                "Un momento...", JOptionPane.YES_NO_OPTION);
        if (!e.getTransaction().isActive()) {
            e.getTransaction().begin();
        }

        if (decision == JOptionPane.YES_OPTION) {
            if (!jTable3.isEditing()) {
                e.getTransaction().commit();
                tablemodelos.refreshRow(jTable3.getSelectedRow());
                this.setVisible(false);
                this.dispose();
                this.refreshLists();
            } else {
                JOptionPane.showMessageDialog(this, "Confirma cambios con ENTER", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }

        } else {
            if (decision == JOptionPane.NO_OPTION) {
                if (e.getTransaction().isActive()) {
                    e.getTransaction().rollback();
                    this.refreshLists();
                }
            }
        }
    }//GEN-LAST:event_SaveChangesModeloActionPerformed

    private void ShowAllPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllPiezaActionPerformed
        //Muestra todas las piezas
        query4 = e.createNamedQuery("Pieza.findAll");
        listapiezas = new Piezas(query4.getResultList());
        TableModelPiezas tablepiezas2 = new TableModelPiezas(listapiezas);
        jTable1.setModel(tablepiezas2);

        jTable1.getColumnModel().getColumn(0).setCellRenderer(new IdPIEZA_Renderer());
        jTable1.getColumnModel().getColumn(1).setCellRenderer(new ModeloPIEZA_Renderer());
        jTable1.getColumnModel().getColumn(2).setCellRenderer(new nombrePIEZA_Renderer());
        jTable1.getColumnModel().getColumn(3).setCellRenderer(new TPiezaPIEZA_Renderer());
        jTable1.getColumnModel().getColumn(4).setCellRenderer(new DescPIEZA_Renderer());

        // Introducir ComboBox en tabla piezas con TiposPiezas
        TableColumn tipoColumn = jTable1.getColumnModel().getColumn(3);
        JComboBox comboBoxCellTPieza = new JComboBox();
        comboBoxCellTPieza.setModel(new DefaultComboBoxModel(listatipPieza.getListaTipoPiezas().toArray()));
        comboBoxCellTPieza.setRenderer(new TiposListRenderer());
        tipoColumn.setCellEditor(new DefaultCellEditor(comboBoxCellTPieza));

    }//GEN-LAST:event_ShowAllPiezaActionPerformed

    private void DeleteModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteModeloActionPerformed
        //Preguntar con OptionPane su decisión y recogemos el resultado
        int result = JOptionPane.showConfirmDialog(this, "¿Estás seguro?",
                "Eliminar marca: " + listamodelos.getModeloByIndex(jTable3.getSelectedRow()).getModelo(),
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (!e.getTransaction().isActive()) {
                e.getTransaction().begin();
            }
            Modelo modelo = listamodelos.getModeloByIndex(jTable3.getSelectedRow());
            e.remove(modelo);
            e.getTransaction().commit();
            tablemodelos.fireTableRowsDeleted(jTable3.getSelectedRow(), jTable3.getSelectedRow());
            this.setVisible(false);
            this.dispose();
            this.refreshLists();
        } else {
            if (result == JOptionPane.CANCEL_OPTION) {
                if (e.getTransaction().isActive()) {
                    e.getTransaction().rollback();
                    this.refreshLists();
                }
            }
        }
    }//GEN-LAST:event_DeleteModeloActionPerformed

    private void SaveChangesPiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChangesPiezaActionPerformed
        //Preguntar con OptionPane su decisión y recogemos el resultado
        int decision = JOptionPane.showConfirmDialog(this, "Estas seguro que quieres guardar los cambios",
                "Un momento...", JOptionPane.YES_NO_OPTION);
        if (!e.getTransaction().isActive()) {
            e.getTransaction().begin();
        }

        if (decision == JOptionPane.YES_OPTION) {
            if (!jTable3.isEditing()) {
                e.getTransaction().commit();
                tablemodel.refreshRow(jTable1.getSelectedRow());
                this.setVisible(false);
                this.dispose();
                this.refreshLists();
            } else {
                JOptionPane.showMessageDialog(this, "Confirma cambios con ENTER", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }

        } else {
            if (decision == JOptionPane.NO_OPTION) {
                if (e.getTransaction().isActive()) {
                    e.getTransaction().rollback();
                    this.refreshLists();
                }
            }
        }
    }//GEN-LAST:event_SaveChangesPiezaActionPerformed

    private void DeletePiezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletePiezaActionPerformed
        //Preguntar con OptionPane su decisión y recogemos el resultado
        int result = JOptionPane.showConfirmDialog(this, "¿Estás seguro?",
                "Eliminar pieza: " + listapiezas.getListaPieza().get(jTable1.getSelectedRow()).getNombre(),
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (!e.getTransaction().isActive()) {
                e.getTransaction().begin();
            }
            Pieza pieza = listapiezas.getListaPieza().get(jTable1.getSelectedRow());
            e.remove(pieza);
            e.getTransaction().commit();
            tablemodelos.fireTableRowsDeleted(jTable1.getSelectedRow(), jTable1.getSelectedRow());
            this.setVisible(false);
            this.dispose();
            this.refreshLists();
        } else {
            if (result == JOptionPane.CANCEL_OPTION) {
                if (e.getTransaction().isActive()) {
                    e.getTransaction().rollback();
                    this.refreshLists();
                }
            }
        }
    }//GEN-LAST:event_DeletePiezaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        areaDetailsPieza.setVisible(true);
        this.showDetails();
    }//GEN-LAST:event_jTable1MouseClicked

    private void showPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPDFActionPerformed
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("Error Driver");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/carparts", "root", null);
        } catch (Exception e1) {
            System.out.println("Error connection");
        }

        ResultSet rs = null;
        Statement sentencia;
        try {
            sentencia = connection.createStatement();
            rs = sentencia.executeQuery("SELECT * FROM Marca");
            while (rs.next()) {
                String marcasql = rs.getString("marca");
                int id = rs.getInt("Id");
                System.out.println(marcasql);
                System.out.println(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarPartsWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Map parameters = new HashMap();
            JasperReport jasperReport
                    = JasperCompileManager.compileReport(
                            "Informe.jrxml");
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, parameters, new JRResultSetDataSource(rs));
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_showPDFActionPerformed

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
            java.util.logging.Logger.getLogger(MostradorPiezas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MostradorPiezas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MostradorPiezas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MostradorPiezas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MostradorPiezas dialog = new MostradorPiezas(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton DeleteMarca;
    private javax.swing.JButton DeleteModelo;
    private javax.swing.JButton DeletePieza;
    private javax.swing.JButton SaveChangesMarca;
    private javax.swing.JButton SaveChangesModelo;
    private javax.swing.JButton SaveChangesPieza;
    private javax.swing.JButton ShowAllPieza;
    private javax.swing.JTextArea areaDetailsPieza;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton showPDF;
    // End of variables declaration//GEN-END:variables
}
