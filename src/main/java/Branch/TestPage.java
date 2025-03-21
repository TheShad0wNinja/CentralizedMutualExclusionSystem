/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Branch;

import Model.CoordinatorRequest;
import Model.Endpoint;
import Model.ResourceType;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static Branch.Branch.acquireResource;
import static Branch.Branch.releaseResource;

/**
 *
 * @author shadow
 */
public class TestPage extends javax.swing.JFrame {

    private int delay = 2000;

    /**
     * Creates new form Test
     */
    public TestPage() {
        initComponents();
        delayDuration.setValue(delay);
    }

    private void testAccess(ResourceType resource, CoordinatorRequest.AccessMode accessMode) {
        new Thread(() -> {
            try {
                Endpoint resourceEndpoint = acquireResource(resource, accessMode);
                System.out.println("Granted " + resource + " " + accessMode + " access at " + LocalTime.now());
                Thread.sleep(delay);
                releaseResource(resource, accessMode);
            } catch (IOException | InterruptedException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        readCloth = new javax.swing.JButton();
        writeCloth = new javax.swing.JButton();
        readAccessory = new javax.swing.JButton();
        writeAccessory = new javax.swing.JButton();
        readFootwear = new javax.swing.JButton();
        writeFootwear = new javax.swing.JButton();
        readOrders = new javax.swing.JButton();
        writeOrders = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        delayDuration = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        readCloth.setText("Read Cloth");
        readCloth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readClothActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        getContentPane().add(readCloth, gridBagConstraints);

        writeCloth.setText("Write Cloth");
        writeCloth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeClothActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        getContentPane().add(writeCloth, gridBagConstraints);

        readAccessory.setText("Read Accessory");
        readAccessory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readAccessoryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        getContentPane().add(readAccessory, gridBagConstraints);

        writeAccessory.setText("Write Accessory");
        writeAccessory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeAccessoryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        getContentPane().add(writeAccessory, gridBagConstraints);

        readFootwear.setText("Read Footwear");
        readFootwear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readFootwearActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        getContentPane().add(readFootwear, gridBagConstraints);

        writeFootwear.setText("Write Footwear");
        writeFootwear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeFootwearActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        getContentPane().add(writeFootwear, gridBagConstraints);

        readOrders.setText("Read Orders");
        readOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readOrdersActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        getContentPane().add(readOrders, gridBagConstraints);

        writeOrders.setText("Write Orders");
        writeOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeOrdersActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        getContentPane().add(writeOrders, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Test Page");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(13, 13, 13, 13);
        getContentPane().add(jLabel1, gridBagConstraints);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jButton1, gridBagConstraints);

        jLabel2.setText("Request Delay (Miliseconds): ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        getContentPane().add(jLabel2, gridBagConstraints);

        delayDuration.setModel(new javax.swing.SpinnerNumberModel(0, null, null, 100));
        delayDuration.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                delayDurationStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 7, 0, 7);
        getContentPane().add(delayDuration, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void writeClothActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeClothActionPerformed
        testAccess(ResourceType.INVENTORY_CLOTH, CoordinatorRequest.AccessMode.WRITE);
    }//GEN-LAST:event_writeClothActionPerformed

    private void readClothActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readClothActionPerformed
        testAccess(ResourceType.INVENTORY_CLOTH, CoordinatorRequest.AccessMode.READ);
    }//GEN-LAST:event_readClothActionPerformed

    private void readAccessoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readAccessoryActionPerformed
        testAccess(ResourceType.INVENTORY_ACCESSORIES, CoordinatorRequest.AccessMode.READ);
    }//GEN-LAST:event_readAccessoryActionPerformed

    private void writeAccessoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeAccessoryActionPerformed
        testAccess(ResourceType.INVENTORY_ACCESSORIES, CoordinatorRequest.AccessMode.WRITE);
    }//GEN-LAST:event_writeAccessoryActionPerformed

    private void readFootwearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readFootwearActionPerformed
        testAccess(ResourceType.INVENTORY_FOOTWEAR, CoordinatorRequest.AccessMode.READ);
    }//GEN-LAST:event_readFootwearActionPerformed

    private void writeFootwearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeFootwearActionPerformed
        testAccess(ResourceType.INVENTORY_FOOTWEAR, CoordinatorRequest.AccessMode.WRITE);
    }//GEN-LAST:event_writeFootwearActionPerformed

    private void readOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readOrdersActionPerformed
        testAccess(ResourceType.ORDERS, CoordinatorRequest.AccessMode.READ);
    }//GEN-LAST:event_readOrdersActionPerformed

    private void writeOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeOrdersActionPerformed
        testAccess(ResourceType.ORDERS, CoordinatorRequest.AccessMode.WRITE);
    }//GEN-LAST:event_writeOrdersActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new HomePage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void delayDurationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_delayDurationStateChanged
        JSpinner jspinner = (JSpinner) evt.getSource();
        delay = (int) jspinner.getValue();
    }//GEN-LAST:event_delayDurationStateChanged

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
            java.util.logging.Logger.getLogger(TestPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner delayDuration;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton readAccessory;
    private javax.swing.JButton readCloth;
    private javax.swing.JButton readFootwear;
    private javax.swing.JButton readOrders;
    private javax.swing.JButton writeAccessory;
    private javax.swing.JButton writeCloth;
    private javax.swing.JButton writeFootwear;
    private javax.swing.JButton writeOrders;
    // End of variables declaration//GEN-END:variables
}
