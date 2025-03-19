/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Branch;

import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author shadow
 */
public class ListInventoryPage extends javax.swing.JFrame {
    //    private HashMap<ResourceItem, Integer> items;
    private ArrayList<InventoryItem> items;
    private final ResourceType resourceType;
    private InventoryItem selectedItem;

    /**
     * Creates new form ListPage
     */
    public ListInventoryPage(ResourceType resourceType) {
        this.resourceType = resourceType;
        if (resourceType == ResourceType.ORDERS) {
            new HomePage();
            this.dispose();
        }

        initComponents();
        disableAll();
        setTableHeaders();
        loadTableData();
    }

    private void setTableHeaders() {
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
        model.setRowCount(0);

        switch (resourceType) {
            case INVENTORY_CLOTH, INVENTORY_FOOTWEAR ->
                    model.setColumnIdentifiers(new Object[]{"ID", "Name", "Price", "Quantity"});
            case INVENTORY_ACCESSORIES ->
                    model.setColumnIdentifiers(new Object[]{"ID", "Name", "Type", "Price", "Quantity"});
        }
    }

    private void loadTableData() {
        disableAll();
        title.setText("LOADING");
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
        model.setRowCount(0);

        new Thread(() -> {
            this.items = Branch.getResourceList(resourceType);
            if (items == null)
                return;

            DecimalFormat df = new DecimalFormat("#.00");

            String titleText = "";

            switch (resourceType) {
                case INVENTORY_CLOTH -> {
                    titleText = "Clothing List";
                    items.forEach((k) -> {
                        Cloth c = (Cloth) k;
                        model.addRow(new Object[]{c.getId(), c.getName(), df.format(c.getPrice()) + "$", c.getTotalQuantity()});
                    });
                }
                case INVENTORY_FOOTWEAR -> {
                    titleText = "Footwear List";
                    items.forEach((k) -> {
                        Footwear f = (Footwear) k;
                        model.addRow(new Object[]{f.getId(), f.getName(), df.format(f.getPrice()) + "$", f.getTotalQuantity()});
                    });
                }
                case INVENTORY_ACCESSORIES -> {
                    titleText = "Accessories List";
                    items.forEach((k) -> {
                        Accessory a = (Accessory) k;
                        model.addRow(new Object[]{a.getId(), a.getName(), a.getType(), df.format(a.getPrice()) + "$",  a.getQuantity()});
                    });
                }
            }

            String finalTitleText = titleText;
            SwingUtilities.invokeLater(() -> {
                inventoryTable.getTableHeader().repaint();
                title.setText(finalTitleText);
                inventoryTable.setModel(model);
            });
        }).start();
    }

    private void itemSelected(int id) {
        Optional<InventoryItem> c = items.stream().filter(k -> k.getId() == id).findFirst();
        if (c.isEmpty())
            return;
        selectedItem = c.get();
        enableAll();
        setSizeTable();
    }

    private void disableAll() {
        selectedItem = null;
        SwingUtilities.invokeLater(() -> {
            sellBtn.setEnabled(false);
            restockBtn.setEnabled(false);
            removeBtn.setEnabled(false);
            priceChangeBtn.setEnabled(false);
            extraInfoPanel.setVisible(false);
        });
    }

    private void enableAll() {
        SwingUtilities.invokeLater(() -> {
            sellBtn.setEnabled(true);
            restockBtn.setEnabled(true);
            removeBtn.setEnabled(true);
            priceChangeBtn.setEnabled(true);
        });
    }

    private void setSizeTable() {
        if (selectedItem == null || resourceType == ResourceType.INVENTORY_ACCESSORIES) {
            extraInfoPanel.setVisible(false);
            return;
        }
        extraInfoPanel.setVisible(true);
        DefaultTableModel model = (DefaultTableModel) sizesTable.getModel();
        model.setRowCount(0);

        switch (resourceType) {
            case INVENTORY_CLOTH -> {
                Cloth c = (Cloth) selectedItem;
                for (Cloth.Size size : Cloth.Size.values()) {
                    model.addRow(new Object[]{size, c.getQuantity(size)});
                }
            }
            case INVENTORY_FOOTWEAR -> {
                Footwear f = (Footwear) selectedItem;
                for (Footwear.Size size : Footwear.Size.values()) {
                    model.addRow(new Object[]{size, f.getQuantity(size)});
                }
            }
        }

        SwingUtilities.invokeLater(() -> sizesTable.setModel(model));
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

        title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inventoryTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        extraInfoPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sizesTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sellBtn = new javax.swing.JButton();
        restockBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        priceChangeBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        title.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        title.setText("Inventory");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(9, 18, 9, 18);
        getContentPane().add(title, gridBagConstraints);

        inventoryTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "ID", "Name", "Size", "Price", "Quantity"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        inventoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(inventoryTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 404;
        gridBagConstraints.ipady = 419;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 18, 12, 18);
        getContentPane().add(jScrollPane1, gridBagConstraints);

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

        extraInfoPanel.setLayout(new java.awt.GridBagLayout());

        sizesTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Size", "Amount"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        sizesTable.setMinimumSize(new java.awt.Dimension(80, 0));
        jScrollPane2.setViewportView(sizesTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 0, 0);
        extraInfoPanel.add(jScrollPane2, gridBagConstraints);

        jLabel1.setText("Sizes:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        extraInfoPanel.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 18);
        getContentPane().add(extraInfoPanel, gridBagConstraints);

        jLabel2.setText("Actions:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 18);
        getContentPane().add(jLabel2, gridBagConstraints);

        sellBtn.setText("Sell Item");
        sellBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 18);
        getContentPane().add(sellBtn, gridBagConstraints);

        restockBtn.setText("Restock Item");
        restockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 18);
        getContentPane().add(restockBtn, gridBagConstraints);

        addBtn.setText("Add New Item");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 18);
        getContentPane().add(addBtn, gridBagConstraints);

        priceChangeBtn.setText("Change Price");
        priceChangeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceChangeBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 18);
        getContentPane().add(priceChangeBtn, gridBagConstraints);

        removeBtn.setText("Remove Item");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 18);
        getContentPane().add(removeBtn, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new HomePage().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void inventoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryTableMouseClicked
        int row = inventoryTable.rowAtPoint(evt.getPoint());

        if (row != -1) {
            itemSelected(Integer.parseInt(inventoryTable.getValueAt(row, 0).toString()));
        }
    }//GEN-LAST:event_inventoryTableMouseClicked

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        JPanel panel = new JPanel(new GridLayout(0, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel priceLabel = new JLabel("Price:");
        SpinnerModel priceModel = new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.01);
        JSpinner priceSpinner = new JSpinner(priceModel);
        ((JSpinner.DefaultEditor) priceSpinner.getEditor()).getTextField().setColumns(10);
        panel.add(priceLabel);
        panel.add(priceSpinner);

        switch (resourceType) {
            case INVENTORY_CLOTH -> {
                int result = JOptionPane.showConfirmDialog(this, panel, "Add New Clothing Item", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String name = nameField.getText().trim();
                    double price = (double) priceSpinner.getValue();

                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Cloth newCloth = new Cloth(name, price);

                    new Thread(() -> {
                        Branch.createResource(ResourceType.INVENTORY_CLOTH, newCloth);
                        JOptionPane.showMessageDialog(this, "New clothing item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadTableData();
                        disableAll();
                    }).start();
                }
            }
            case INVENTORY_FOOTWEAR -> {
                int result = JOptionPane.showConfirmDialog(this, panel, "Add New Footwear Item", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String name = nameField.getText().trim();
                    double price = (double) priceSpinner.getValue();

                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Footwear newFootwear = new Footwear(name, price);

                    new Thread(() -> {
                        Branch.createResource(ResourceType.INVENTORY_FOOTWEAR, newFootwear);
                        JOptionPane.showMessageDialog(this, "New footwear item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadTableData();
                        disableAll();
                    }).start();
                }
            }
            case INVENTORY_ACCESSORIES -> {
                JLabel typeLabel = new JLabel("Type:");
                JComboBox<Accessory.Type> typeComboBox = new JComboBox<>(Accessory.Type.values());
                panel.add(typeLabel);
                panel.add(typeComboBox);

                int result = JOptionPane.showConfirmDialog(this, panel, "Add New Accessory", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String name = nameField.getText().trim();
                    double price = (double) priceSpinner.getValue();
                    Accessory.Type type = (Accessory.Type) typeComboBox.getSelectedItem();

                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Accessory newAccessory = new Accessory(name, price, type, 0);

                    new Thread(() -> {
                        Branch.createResource(ResourceType.INVENTORY_ACCESSORIES, newAccessory);
                        JOptionPane.showMessageDialog(this, "New accessory added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadTableData();
                        disableAll();
                    }).start();
                }
            }
        }

    }//GEN-LAST:event_addBtnActionPerformed

    private void sellBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellBtnActionPerformed
        JPanel panel = new JPanel(new GridLayout(0, 2));

        switch (resourceType) {
            case INVENTORY_CLOTH -> {
                Cloth item = (Cloth) selectedItem;

                if (item.getTotalQuantity() == 0) {
                    JOptionPane.showMessageDialog(this, "This item is out of stock", "Out of Stock", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Add input fields for each size
                for (Cloth.Size size : Cloth.Size.values()) {
                    JLabel sizeLabel = new JLabel(size.toString() + ":");
                    SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, item.getQuantity(size), 1);
                    JSpinner spinner = new JSpinner(spinnerModel);
                    panel.add(sizeLabel);
                    panel.add(spinner);
                }

                // Show the dialog
                int result = JOptionPane.showConfirmDialog(this, panel, "Enter Quantities of " + item.getName() + " to Sell", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    for (Cloth.Size size : Cloth.Size.values()) {
                        JSpinner quantitySpinner = (JSpinner) panel.getComponent(2 * size.ordinal() + 1);
                        int quantity = (int) quantitySpinner.getValue();
                        item.removeQuantity(size, quantity);
                    }
                    setSizeTable();
                    new Thread(() -> Branch.updateResource(ResourceType.INVENTORY_CLOTH, item)).start();
                }
            }
            case INVENTORY_FOOTWEAR -> {
                Footwear item = (Footwear) selectedItem;

                if (item.getTotalQuantity() == 0) {
                    JOptionPane.showMessageDialog(this, "This item is out of stock", "Out of Stock", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (Footwear.Size size : Footwear.Size.values()) {
                    JLabel sizeLabel = new JLabel(size.toString() + ":");
                    SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, item.getQuantity(size), 1);
                    JSpinner spinner = new JSpinner(spinnerModel);
                    panel.add(sizeLabel);
                    panel.add(spinner);
                }

                int result = JOptionPane.showConfirmDialog(this, panel, "Enter Quantities of " + item.getName() + " to Sell", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    for (Footwear.Size size : Footwear.Size.values()) {
                        JSpinner quantitySpinner = (JSpinner) panel.getComponent(2 * size.ordinal() + 1);
                        int quantity = (int) quantitySpinner.getValue();
                        item.removeQuantity(size, quantity);
                    }
                    setSizeTable();
                    new Thread(() -> Branch.updateResource(ResourceType.INVENTORY_FOOTWEAR, item)).start();
                }
            }
            case INVENTORY_ACCESSORIES -> {
                Accessory item = (Accessory) selectedItem;

                if (item.getQuantity() == 0) {
                    JOptionPane.showMessageDialog(this, "This item is out of stock", "Out of Stock", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JLabel sizeLabel = new JLabel("Amount: ");
                SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, item.getQuantity(), 1);
                JSpinner spinner = new JSpinner(spinnerModel);
                ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setColumns(10);
                panel.add(sizeLabel);
                panel.add(spinner);

                int result = JOptionPane.showConfirmDialog(this, panel, "Enter Quantities of " + item.getName() + " to Sell", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    int quantity = (int) spinner.getValue();
                    item.removeQuantity(quantity);

                    new Thread(() -> {
                        Branch.updateResource(ResourceType.INVENTORY_ACCESSORIES, item);
                        loadTableData();
                    }).start();
                }
            }
        }
    }//GEN-LAST:event_sellBtnActionPerformed

    private void restockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockBtnActionPerformed
        JPanel panel = new JPanel(new GridLayout(0, 2));

        switch (resourceType) {
            case INVENTORY_CLOTH -> {
                Cloth item = (Cloth) selectedItem;

                for (Cloth.Size size : Cloth.Size.values()) {
                    JLabel sizeLabel = new JLabel(size.toString() + ":");
                    SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
                    JSpinner spinner = new JSpinner(spinnerModel);
                    panel.add(sizeLabel);
                    panel.add(spinner);
                }

                int result = JOptionPane.showConfirmDialog(this, panel, "Enter Quantities of " + item.getName() + " to restock", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    for (Cloth.Size size : Cloth.Size.values()) {
                        JSpinner quantitySpinner = (JSpinner) panel.getComponent(2 * size.ordinal() + 1);
                        int quantity = (int) quantitySpinner.getValue();
                        item.addQuantity(size, quantity);
                    }
                    setSizeTable();
                    new Thread(() -> Branch.updateResource(resourceType, item)).start();
                }
            }
            case INVENTORY_FOOTWEAR -> {
                Footwear item = (Footwear) selectedItem;

                for (Footwear.Size size : Footwear.Size.values()) {
                    JLabel sizeLabel = new JLabel(size.toString() + ":");
                    SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
                    JSpinner spinner = new JSpinner(spinnerModel);
                    panel.add(sizeLabel);
                    panel.add(spinner);
                }

                int result = JOptionPane.showConfirmDialog(this, panel, "Enter Quantities of " + item.getName() + " to restock", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    for (Footwear.Size size : Footwear.Size.values()) {
                        JSpinner quantitySpinner = (JSpinner) panel.getComponent(2 * size.ordinal() + 1);
                        int quantity = (int) quantitySpinner.getValue();
                        item.addQuantity(size, quantity);
                    }
                    setSizeTable();
                    new Thread(() -> Branch.updateResource(resourceType, item)).start();
                }
            }
            case INVENTORY_ACCESSORIES -> {
                Accessory item = (Accessory) selectedItem;

                JLabel sizeLabel = new JLabel("Amount: ");
                SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
                JSpinner spinner = new JSpinner(spinnerModel);
                ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setColumns(10);
                panel.add(sizeLabel);
                panel.add(spinner);

                int result = JOptionPane.showConfirmDialog(this, panel, "Enter Quantities of " + item.getName() + " to restock", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    int quantity = (int) spinner.getValue();
                    item.addQuantity(quantity);

                    new Thread(() -> {
                        Branch.updateResource(ResourceType.INVENTORY_ACCESSORIES, item);
                        loadTableData();
                    }).start();
                }
            }
        }
    }//GEN-LAST:event_restockBtnActionPerformed

    private void priceChangeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceChangeBtnActionPerformed
        JPanel panel = new JPanel(new GridLayout(0, 2));
        JLabel sizeLabel = new JLabel("New Price: ");
        SpinnerModel spinnerModel = new SpinnerNumberModel(0.0, 0.0, Double.MAX_VALUE, 0.1);
        JSpinner priceSpinner = new JSpinner(spinnerModel);
        ((JSpinner.DefaultEditor) priceSpinner.getEditor()).getTextField().setColumns(10);

        panel.add(sizeLabel);
        panel.add(priceSpinner);

        // Show the dialog
        int result = JOptionPane.showConfirmDialog(this, panel, "Enter new price of" + selectedItem.getName(), JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            selectedItem.setPrice((double) priceSpinner.getValue());
            new Thread(() -> {
                Branch.updateResource(resourceType, selectedItem);
                loadTableData();
                JOptionPane.showMessageDialog(this, "Price changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }).start();
        }
    }//GEN-LAST:event_priceChangeBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new Thread(() -> {
                Branch.removeResource(resourceType, selectedItem);
                loadTableData();
            }).start();
        }
    }//GEN-LAST:event_removeBtnActionPerformed

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
            java.util.logging.Logger.getLogger(ListInventoryPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListInventoryPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListInventoryPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListInventoryPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListInventoryPage(ResourceType.INVENTORY_ACCESSORIES).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel extraInfoPanel;
    private javax.swing.JTable inventoryTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton priceChangeBtn;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton restockBtn;
    private javax.swing.JButton sellBtn;
    private javax.swing.JTable sizesTable;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
