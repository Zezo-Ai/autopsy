/*
 * Autopsy Forensic Browser
 *
 * Copyright 2018 Basis Technology Corp.
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
package org.sleuthkit.autopsy.casemodule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.corecomponentinterfaces.DataSourceProcessor;
import org.sleuthkit.autopsy.coreutils.MessageNotifyUtil;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.autopsy.coreutils.PathValidator;

/**
 *  A panel which allows the user to select local files and/or directories.
 */
@SuppressWarnings("PMD.SingularField") // UI widgets cause lots of false positives
final class LocalFilesPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private final Set<File> currentFiles = new TreeSet<>(); //keep currents in a set to disallow duplicates per add
    private boolean enableNext = false;
    private static final Logger logger = Logger.getLogger(LocalFilesPanel.class.getName());
    private String displayName = "";

    /**
     * Creates new form LocalFilesPanel
     */
    LocalFilesPanel() {
        initComponents();
        customInit();
    }

    private void customInit() {
        localFileChooser.setMultiSelectionEnabled(true);
        errorLabel.setVisible(false);
        selectedPaths.setText("");
        this.displayNameLabel.setText(NbBundle.getMessage(this.getClass(), "LocalFilesPanel.displayNameLabel.text"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        localFileChooser = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        selectButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        selectedPathsScrollPane = new javax.swing.JScrollPane();
        selectedPaths = new javax.swing.JTextArea();
        errorLabel = new javax.swing.JLabel();
        changeNameButton = new javax.swing.JButton();
        displayNameLabel = new javax.swing.JLabel();

        localFileChooser.setApproveButtonText(org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.localFileChooser.approveButtonText")); // NOI18N
        localFileChooser.setApproveButtonToolTipText(org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.localFileChooser.approveButtonToolTipText")); // NOI18N
        localFileChooser.setDialogTitle(org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.localFileChooser.dialogTitle")); // NOI18N
        localFileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);

        org.openide.awt.Mnemonics.setLocalizedText(selectButton, org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.selectButton.text")); // NOI18N
        selectButton.setToolTipText(org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.selectButton.toolTipText")); // NOI18N
        selectButton.setActionCommand(org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.selectButton.actionCommand")); // NOI18N
        selectButton.setMaximumSize(new java.awt.Dimension(70, 23));
        selectButton.setMinimumSize(new java.awt.Dimension(70, 23));
        selectButton.setPreferredSize(new java.awt.Dimension(70, 23));
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(clearButton, org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.clearButton.text")); // NOI18N
        clearButton.setToolTipText(org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.clearButton.toolTipText")); // NOI18N
        clearButton.setMaximumSize(new java.awt.Dimension(70, 23));
        clearButton.setMinimumSize(new java.awt.Dimension(70, 23));
        clearButton.setPreferredSize(new java.awt.Dimension(70, 23));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        selectedPathsScrollPane.setPreferredSize(new java.awt.Dimension(379, 96));

        selectedPaths.setEditable(false);
        selectedPaths.setColumns(20);
        selectedPaths.setRows(5);
        selectedPaths.setToolTipText(org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.selectedPaths.toolTipText")); // NOI18N
        selectedPathsScrollPane.setViewportView(selectedPaths);

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(errorLabel, org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.errorLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(changeNameButton, org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.changeNameButton.text")); // NOI18N
        changeNameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeNameButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(displayNameLabel, org.openide.util.NbBundle.getMessage(LocalFilesPanel.class, "LocalFilesPanel.displayNameLabel.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(selectedPathsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(displayNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changeNameButton))
                            .addComponent(errorLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearButton, selectButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(selectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(selectedPathsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeNameButton)
                    .addComponent(displayNameLabel))
                .addGap(13, 13, 13)
                .addComponent(errorLabel)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed
        int returnVal = localFileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File[] files = localFileChooser.getSelectedFiles();
            StringBuilder allPaths = new StringBuilder();
            for (File f : files) {
                currentFiles.add(f);
            }
            for (File f : currentFiles) { 
                //loop over set of all files to ensure list is accurate
                //update label
                allPaths.append(f.getAbsolutePath()).append("\n");
            }
            this.selectedPaths.setText(allPaths.toString());
            this.selectedPaths.setToolTipText(allPaths.toString());
        }

        enableNext = !currentFiles.isEmpty();

        try {
            firePropertyChange(DataSourceProcessor.DSP_PANEL_EVENT.UPDATE_UI.toString(), false, true);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "LocalFilesPanel listener threw exception", e); //NON-NLS
            MessageNotifyUtil.Notify.show(NbBundle.getMessage(this.getClass(), "LocalFilesPanel.moduleErr"),
                    NbBundle.getMessage(this.getClass(), "LocalFilesPanel.moduleErr.msg"),
                    MessageNotifyUtil.MessageType.ERROR);
        }
    }//GEN-LAST:event_selectButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        reset();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void changeNameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeNameButtonActionPerformed
        final String selectedDisplayName = JOptionPane.showInputDialog("New Display Name: ");
        if (selectedDisplayName != null && !selectedDisplayName.isEmpty()) {
            this.displayName = selectedDisplayName;
            this.displayNameLabel.setText("Display Name: " + this.displayName);
        }
    }//GEN-LAST:event_changeNameButtonActionPerformed

    /**
     * Clear the fields and undo any selection of files.
     */
    void reset() {
        currentFiles.clear();
        selectedPaths.setText("");
        enableNext = false;
        errorLabel.setVisible(false);
        displayName = "";
        this.displayNameLabel.setText(NbBundle.getMessage(this.getClass(), "LocalFilesPanel.displayNameLabel.text"));
        try {
            firePropertyChange(DataSourceProcessor.DSP_PANEL_EVENT.UPDATE_UI.toString(), false, true);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "LocalFilesPanel listener threw exception", e); //NON-NLS
            MessageNotifyUtil.Notify.show(NbBundle.getMessage(this.getClass(), "LocalFilesPanel.moduleErr"),
                    NbBundle.getMessage(this.getClass(), "LocalFilesPanel.moduleErr.msg"),
                    MessageNotifyUtil.MessageType.ERROR);
        }
    }
    /**
     * Get the path(s) which have been selected on this panel
     *
     * @return a List of Strings representing the path(s) for the selected files or directories
     */
    List<String> getContentPaths() {
        List<String> pathsList = new ArrayList<>();
        if (currentFiles == null) {
            return pathsList;
        }
        for (File f : currentFiles) {
            pathsList.add(f.getAbsolutePath());
        }
        return pathsList;
    }

    /**
     * Validates path to selected data source and displays warning if it is
     * invalid.
     *
     * @return enableNext - true if the panel is valid, false if invalid
     */
    boolean validatePanel() {
        // display warning if there is one (but don't disable "next" button)
        warnIfPathIsInvalid(getContentPaths());
        return enableNext;
    }

    /**
     * Validates path to selected data source and displays warning if it is
     * invalid.
     *
     * @param paths Absolute paths to the selected data source
     */
    @NbBundle.Messages({
        "LocalFilesPanel.pathValidation.dataSourceOnCDriveError=Warning: Path to multi-user data source is on \"C:\" drive",
        "LocalFilesPanel.pathValidation.getOpenCase=WARNING: Exception while gettting open case."
    })
    private void warnIfPathIsInvalid(final List<String> pathsList) {
        errorLabel.setVisible(false);

        try {
            final Case.CaseType currentCaseType = Case.getCurrentCaseThrows().getCaseType();

            for (String currentPath : pathsList) {
                if (!PathValidator.isValidForCaseType(currentPath, currentCaseType)) {
                    errorLabel.setVisible(true);
                    errorLabel.setText(Bundle.LocalFilesPanel_pathValidation_dataSourceOnCDriveError());
                    return;
                }
            }
        } catch (NoCurrentCaseException ex) {
            errorLabel.setVisible(true);
            errorLabel.setText(Bundle.LocalFilesPanel_pathValidation_getOpenCase());
        }
    }

    /**
     * Get the name given to this collection of local files and directories
     *
     * @return a String which is the name for the file set.
     */
    String getFileSetName() {
        return this.displayName;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changeNameButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel displayNameLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFileChooser localFileChooser;
    private javax.swing.JButton selectButton;
    private javax.swing.JTextArea selectedPaths;
    private javax.swing.JScrollPane selectedPathsScrollPane;
    // End of variables declaration//GEN-END:variables
}
