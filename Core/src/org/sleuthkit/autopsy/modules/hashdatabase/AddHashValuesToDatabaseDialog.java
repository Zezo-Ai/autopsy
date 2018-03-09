/*
 * Autopsy Forensic Browser
 * 
 * Copyright 2013-2018 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.modules.hashdatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.modules.hashdatabase.HashDbManager.HashDb;
import org.sleuthkit.datamodel.HashEntry;

/**
 *
 * @author sidhesh
 */
public class AddHashValuesToDatabaseDialog extends javax.swing.JDialog {

    HashDb hashDb;
    Pattern md5Pattern = Pattern.compile("^[a-fA-F0-9]{32}$");
    List<HashEntry> hashes = new ArrayList<>();
    List<String> invalidHashes = new ArrayList<>();

    /**
     * Displays a dialog that allows a user to add hash values to the selected
     * database.
     */
    AddHashValuesToDatabaseDialog(HashDb hashDb) {
        super((JFrame) WindowManager.getDefault().getMainWindow(),
                NbBundle.getMessage(AddHashValuesToDatabaseDialog.class, "AddHashValuesToDatabaseDialog.JDialog.Title", hashDb.getDisplayName()),
                true);
        this.hashDb = hashDb;
        initComponents();
        display();
    }

    private void display() {
        setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
        setVisible(true);
    }

    /**
     * Toggle the buttons and default close operation.
     *
     * @param enable Set true to enable buttons and DISPOSE_ON_CLOSE. Set false
     *               to disable buttons and DO_NOTHING_ON_CLOSE
     */
    void enableAddHashValuesToDatabaseDialog(boolean enable) {
        if (enable) {
            setDefaultCloseOperation(2);
        } else {
            setDefaultCloseOperation(0);
        }
        AddValuesToHashDatabaseButton.setEnabled(enable);
        cancelButton.setEnabled(enable);
        pasteFromClipboardButton.setEnabled(enable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        instructionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hashValuesTextArea = new javax.swing.JTextArea();
        pasteFromClipboardButton = new javax.swing.JButton();
        AddValuesToHashDatabaseButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(AddHashValuesToDatabaseDialog.class, "AddHashValuesToDatabaseDialog.title")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(instructionLabel, org.openide.util.NbBundle.getMessage(AddHashValuesToDatabaseDialog.class, "AddHashValuesToDatabaseDialog.instructionLabel.text_1")); // NOI18N

        hashValuesTextArea.setColumns(20);
        hashValuesTextArea.setRows(5);
        hashValuesTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hashValuesTextAreaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(hashValuesTextArea);

        org.openide.awt.Mnemonics.setLocalizedText(pasteFromClipboardButton, org.openide.util.NbBundle.getMessage(AddHashValuesToDatabaseDialog.class, "AddHashValuesToDatabaseDialog.pasteFromClipboardButton.text_2")); // NOI18N
        pasteFromClipboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteFromClipboardButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(AddValuesToHashDatabaseButton, org.openide.util.NbBundle.getMessage(AddHashValuesToDatabaseDialog.class, "AddHashValuesToDatabaseDialog.AddValuesToHashDatabaseButton.text_2")); // NOI18N
        AddValuesToHashDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddValuesToHashDatabaseButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(cancelButton, org.openide.util.NbBundle.getMessage(AddHashValuesToDatabaseDialog.class, "AddHashValuesToDatabaseDialog.cancelButton.text_2")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
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
                        .addComponent(instructionLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddValuesToHashDatabaseButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pasteFromClipboardButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instructionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AddValuesToHashDatabaseButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pasteFromClipboardButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pasteFromClipboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteFromClipboardButtonActionPerformed
        hashValuesTextArea.paste();
        hashValuesTextArea.append("\n");
        // TODO - Avoid unnecessary \n appending in case nothing is pasted.
    }//GEN-LAST:event_pasteFromClipboardButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void hashValuesTextAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hashValuesTextAreaMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            JPopupMenu popup = new JPopupMenu();

            JMenuItem cutMenu = new JMenuItem("Cut"); // NON-NLS
            cutMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hashValuesTextArea.cut();
                }
            });

            JMenuItem copyMenu = new JMenuItem("Copy"); // NON-NLS
            copyMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hashValuesTextArea.copy();
                }
            });

            JMenuItem pasteMenu = new JMenuItem("Paste"); // NON-NLS
            pasteMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hashValuesTextArea.paste();
                    hashValuesTextArea.append("\n");
                }
            });

            popup.add(cutMenu);
            popup.add(copyMenu);
            popup.add(pasteMenu);
            popup.show(hashValuesTextArea, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_hashValuesTextAreaMouseClicked

    private void AddValuesToHashDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddValuesToHashDatabaseButtonActionPerformed
        AddHashValuesToDatabaseProgressDialog progressDialog = new AddHashValuesToDatabaseProgressDialog(this, hashDb, hashValuesTextArea.getText());
        progressDialog.addHashValuesToDatabase();
    }//GEN-LAST:event_AddValuesToHashDatabaseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddValuesToHashDatabaseButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextArea hashValuesTextArea;
    private javax.swing.JLabel instructionLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pasteFromClipboardButton;
    // End of variables declaration//GEN-END:variables
}
