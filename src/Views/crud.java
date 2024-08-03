/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Class.CivilStatus;
import Class.Document;
import Class.Status;
import Class.Student;
import DAO.studentDAO;
import conection.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thoms
 */
public class crud extends javax.swing.JFrame {

    /**
     * Creates new form crud
     */
    public crud() {
        initComponents();
        getData();
    }

    public void getData() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            studentDAO studentDao = new studentDAO(connection);
            
            List<Status> estados = studentDao.getAllEstados();
            List<CivilStatus> estadosCiviles = studentDao.getAllEstadosCiviles();
            List<Document> documentos = studentDao.getAllDocumentos();
      
            
            for (Status estado : estados) {
                jComboBox1.addItem(estado.getName());
            }

            for (CivilStatus estadoCivil : estadosCiviles) {
                jComboBox2.addItem(estadoCivil.getStatus());
            }

            for (Document documento : documentos) {
                jComboBox3.addItem(documento.getType());
            }
            
        
            // Limpiar la tabla antes de agregar los registros
             javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
             model.setRowCount(0);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validarEntradas() {
        if (jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío");
            return false;
        }
        if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El apellido no puede estar vacío");
            return false;
        }
        if (jFormattedTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La fecha de nacimiento no puede estar vacía");
            return false;
        }
        // Validar el formato de la fecha
        try {
            String fechaStr = jFormattedTextField1.getText().trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "El formato de la fecha no es adecuado. Ejemplo de formato correcto: 2023-07-20");
            return false;
        }
        if (jComboBox1.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un estado");
            return false;
        }
        if (jComboBox2.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un estado civil");
            return false;
        }
        if (jComboBox3.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de documento");
            return false;
        }
        return true;
    }
    
    private void limpiarCampos() {
        jTextField1.setText("");
        jTextField2.setText("");
        jFormattedTextField1.setText("");
        jComboBox1.setSelectedIndex(-1);
        jComboBox2.setSelectedIndex(-1); 
        jComboBox3.setSelectedIndex(-1); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setText("Estado Civil");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Documento");

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("ID");

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Listar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");
        jLabel3.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setText("BIENVENIDO");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "Fecha de nacimiento", "Estado", "Estado Civil", "Documento"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setText("Fecha de naciento (yyyy-mm-dd) ");

        jLabel5.setText("Estado");

        jButton6.setText("Generar Reportes");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, 157, Short.MAX_VALUE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField3)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                        .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (validarEntradas()) {
            try(Connection connection = DatabaseConnection.getConnection()) {
                String nombre = jTextField1.getText();
                String apellido = jTextField2.getText();
                Date fechaNacimiento = Date.valueOf(jFormattedTextField1.getText());
                int estadoId = jComboBox1.getSelectedIndex() + 1;
                int estadoCivilId = jComboBox2.getSelectedIndex() + 1;
                int documentoId = jComboBox3.getSelectedIndex() + 1;

                studentDAO studenDAO = new studentDAO(connection);
                Student estudiante = new Student(0, nombre, apellido, fechaNacimiento, estadoId, estadoCivilId, documentoId);

                studenDAO.insertEstudiante(estudiante);
                
                 List<Student> estudiantes = studenDAO.getAllEstudiantes();

                // Limpiar la tabla antes de agregar los registros
                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                for (Student row : estudiantes) {
                    model.addRow(new Object[]{row.getId(),row.getName(), row.getLastName(), row.getBirthDate(), row.getState(), row.getMaritalStatus(), row.getDocument()});
                }

                // Obtener el modelo de la tabla y agregar una nueva fila
              //  javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
                //model.addRow(new Object[]{nombre, apellido, fechaNacimiento, jComboBox1.getSelectedItem(), jComboBox2.getSelectedItem(), jComboBox3.getSelectedItem()});

                JOptionPane.showMessageDialog(null, "Estudiante insertado correctamente");
                // Limpiar campos después de guardar
                limpiarCampos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al insertar estudiante: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el ID del estudiante para editar");
            return;
        }

        if (validarEntradas()) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                int id = Integer.parseInt(jTextField3.getText());
                String nombre = jTextField1.getText();
                String apellido = jTextField2.getText();
                Date fechaNacimiento = Date.valueOf(jFormattedTextField1.getText().trim());
                int estadoId = jComboBox1.getSelectedIndex() + 1;
                int estadoCivilId = jComboBox2.getSelectedIndex() + 1;
                int documentoId = jComboBox3.getSelectedIndex() + 1;

                studentDAO studenDAO = new studentDAO(connection);
                Student estudiante = new Student(id, nombre, apellido, fechaNacimiento, estadoId, estadoCivilId, documentoId);

                studenDAO.updateEstudiante(estudiante);

                JOptionPane.showMessageDialog(null, "Estudiante editado correctamente");
                // Limpiar campos después de editar
                limpiarCampos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al editar estudiante: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el ID del estudiante para eliminar");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            int id = Integer.parseInt(jTextField3.getText());

            studentDAO studenDAO = new studentDAO(connection);
            studenDAO.deleteEstudiante(id);

            JOptionPane.showMessageDialog(null, "Estudiante eliminado correctamente");
            // Limpiar campos después de eliminar
            limpiarCampos();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar estudiante: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try (Connection connection = DatabaseConnection.getConnection()) {
            studentDAO studenDAO = new studentDAO(connection);
            List<Student> estudiantes = studenDAO.getAllEstudiantes();

            // Limpiar la tabla antes de agregar los registros
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            for (Student estudiante : estudiantes) {
                model.addRow(new Object[]{estudiante.getId(),estudiante.getName(), estudiante.getLastName(), estudiante.getBirthDate(), estudiante.getState(), estudiante.getMaritalStatus(), estudiante.getDocument()});
            }

            JOptionPane.showMessageDialog(null, "Listado de estudiantes actualizado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar estudiantes: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (jTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el ID del estudiante para buscar");
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            int id = Integer.parseInt(jTextField3.getText());

            studentDAO studenDAO = new studentDAO(connection);
            Student estudiante = studenDAO.getEstudianteById(id);

            if (estudiante != null) {
                jTextField1.setText(estudiante.getName());
                jTextField2.setText(estudiante.getLastName());
                jFormattedTextField1.setText(estudiante.getBirthDate().toString());
                jComboBox1.setSelectedIndex(estudiante.getStateId()- 1);
                jComboBox2.setSelectedIndex(estudiante.getMaritalStatusId()- 1);
                jComboBox3.setSelectedIndex(estudiante.getDocumentId() - 1);
                JOptionPane.showMessageDialog(null, "Estudiante encontrado");
            } else {
                JOptionPane.showMessageDialog(null, "Estudiante no encontrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar estudiante: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       ViewReports ventana = new ViewReports();
       ventana.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new crud().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
