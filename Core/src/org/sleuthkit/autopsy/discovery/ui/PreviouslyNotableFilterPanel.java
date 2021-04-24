/*
 * Autopsy
 *
 * Copyright 2020 Basis Technology Corp.
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
package org.sleuthkit.autopsy.discovery.ui;

import org.sleuthkit.autopsy.discovery.search.AbstractFilter;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import org.sleuthkit.autopsy.centralrepository.datamodel.CentralRepository;
import org.sleuthkit.autopsy.coreutils.ThreadConfined;
import org.sleuthkit.autopsy.discovery.search.SearchFiltering;

/**
 * Panel to allow configuration of the previously notable (in CR) filter.
 */
final class PreviouslyNotableFilterPanel extends AbstractDiscoveryFilterPanel {

    private static final long serialVersionUID = 1L;

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    PreviouslyNotableFilterPanel() {
        initComponents();
        if (!CentralRepository.isEnabled()) {
            previouslyNotableCheckbox.setEnabled(false);
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

        previouslyNotableCheckbox = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(previouslyNotableCheckbox, org.openide.util.NbBundle.getMessage(PreviouslyNotableFilterPanel.class, "PreviouslyNotableFilterPanel.text_1")); // NOI18N
        previouslyNotableCheckbox.setMaximumSize(new java.awt.Dimension(255, 25));
        previouslyNotableCheckbox.setMinimumSize(new java.awt.Dimension(0, 25));
        previouslyNotableCheckbox.setName(""); // NOI18N
        previouslyNotableCheckbox.setOpaque(false);
        previouslyNotableCheckbox.setPreferredSize(new java.awt.Dimension(255, 25));
        previouslyNotableCheckbox.setRequestFocusEnabled(false);

        setMinimumSize(new java.awt.Dimension(0, 30));
        setPreferredSize(new java.awt.Dimension(255, 30));
        setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    @Override
    void configurePanel(boolean selected, List<?> selectedItems) {
        previouslyNotableCheckbox.setSelected(selected);
    }

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    @Override
    JCheckBox getCheckbox() {
        return previouslyNotableCheckbox;
    }

    @Override
    JLabel getAdditionalLabel() {
        return null;
    }

    @Override
    String checkForError() {
        //this filter currently has no errors it generates
        return "";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox previouslyNotableCheckbox;
    // End of variables declaration//GEN-END:variables

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    @Override
    AbstractFilter getFilter() {
        if (previouslyNotableCheckbox.isSelected()) {
            return new SearchFiltering.PreviouslyNotableFilter();
        }
        return null;
    }

    @Override
    boolean hasPanel() {
        return false;
    }

    @Override
    void addListSelectionListener(ListSelectionListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    boolean isFilterSupported() {
        return true;
    }
    
}
