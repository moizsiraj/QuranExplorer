/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.basic.*;

/**
 *
 * @author moizs
 */
class MyComboPopup extends BasicComboPopup {

    public MyComboPopup(JComboBox comboBox) {
        super(comboBox);
    }

    protected JScrollPane createScroller() {
        JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scroller;
    }

    public Dimension getPreferredSize() {
        Dimension d = new Dimension();
        d.setSize(200, super.getPreferredSize().getHeight());
        return d;
    }

    public void show() {
        Dimension popupSize = getPreferredSize();
        popupSize.setSize(popupSize.width,
                getPopupHeightForRowCount(comboBox.getMaximumRowCount()));
        Rectangle popupBounds = computePopupBounds(0, comboBox.getBounds().height,
                popupSize.width, popupSize.height);
        scroller.setMaximumSize(popupBounds.getSize());
        scroller.setPreferredSize(popupBounds.getSize());
        scroller.setMinimumSize(popupBounds.getSize());
        list.invalidate();
        int selectedIndex = comboBox.getSelectedIndex();
        if (selectedIndex == -1) {
            list.clearSelection();
        } else {
            list.setSelectedIndex(selectedIndex);
        }
        list.ensureIndexIsVisible(list.getSelectedIndex());
        setLightWeightPopupEnabled(comboBox.isLightWeightPopupEnabled());
        show(comboBox, popupBounds.x, popupBounds.y);
    }

}

class MyBasicComboBoxUI extends BasicComboBoxUI {

    BasicComboPopup popup;

    protected ComboPopup createPopup() {
        popup = new MyComboPopup(comboBox);
        popup.getAccessibleContext().setAccessibleParent(comboBox);
        return popup;
    }

    public BasicComboPopup getPopup() {
        return popup;
    }
}
