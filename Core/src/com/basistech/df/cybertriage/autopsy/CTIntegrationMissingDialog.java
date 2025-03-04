/*
 * Autopsy Forensic Browser
 *
 * Copyright 2023 Basis Technology Corp.
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
package com.basistech.df.cybertriage.autopsy;

import org.sleuthkit.autopsy.coreutils.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import javax.swing.JComponent;
import org.sleuthkit.autopsy.coreutils.Logger;

/**
 * Provides directions with how to enable CT integration with Autopsy when
 * trying to open a CT exported case.
 */
public class CTIntegrationMissingDialog extends javax.swing.JDialog {

    private static final String DOCS_PAGE_URL = "https://docs.cybertriage.com/en/latest/chapters/integrations/autopsy.html";

    private static final Logger LOGGER = Logger.getLogger(CTIntegrationMissingDialog.class.getName());

    /**
     * Creates new form CTIntegrationMissingDialog
     */
    public CTIntegrationMissingDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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

        javax.swing.JLabel descriptionLabel = new javax.swing.JLabel();
        javax.swing.JLabel docsLabel = new javax.swing.JLabel();
        link = new javax.swing.JLabel();
        javax.swing.JPanel paddingPanel = new javax.swing.JPanel();
        javax.swing.JButton okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(CTIntegrationMissingDialog.class, "CTIntegrationMissingDialog.title")); // NOI18N
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(descriptionLabel, org.openide.util.NbBundle.getMessage(CTIntegrationMissingDialog.class, "CTIntegrationMissingDialog.descriptionLabel.text")); // NOI18N
        descriptionLabel.setMinimumSize(new java.awt.Dimension(483, 116));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(descriptionLabel, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(docsLabel, org.openide.util.NbBundle.getMessage(CTIntegrationMissingDialog.class, "CTIntegrationMissingDialog.docsLabel.text")); // NOI18N
        docsLabel.setMinimumSize(new java.awt.Dimension(312, 16));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        getContentPane().add(docsLabel, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(link, "<html><span style=\"color: blue; text-decoration: underline\">" + DOCS_PAGE_URL + "</span></html>");
        link.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        link.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(link, gridBagConstraints);

        javax.swing.GroupLayout paddingPanelLayout = new javax.swing.GroupLayout(paddingPanel);
        paddingPanel.setLayout(paddingPanelLayout);
        paddingPanelLayout.setHorizontalGroup(
            paddingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        paddingPanelLayout.setVerticalGroup(
            paddingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(paddingPanel, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(okButton, org.openide.util.NbBundle.getMessage(CTIntegrationMissingDialog.class, "CTIntegrationMissingDialog.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        getContentPane().add(okButton, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void linkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkMouseClicked
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(DOCS_PAGE_URL));
            } catch (IOException | URISyntaxException e) {
                LOGGER.log(Level.SEVERE, "Error opening link to: " + DOCS_PAGE_URL, e);
            }
        } else {
            LOGGER.log(Level.WARNING, "Desktop API is not supported.  Link cannot be opened.");
        }
    }//GEN-LAST:event_linkMouseClicked

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    public void showDialog(JComponent parentComp) {
        setLocationRelativeTo(parentComp == null ? getParent() : parentComp);
        pack();
        setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel link;
    // End of variables declaration//GEN-END:variables
}
