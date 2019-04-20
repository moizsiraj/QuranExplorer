/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.ComponentOrientation;
import static project.mainScreen.q;
import static project.mainScreen.s;
import static project.mainScreen.t;

/**
 *
 * @author moizs
 */
public class topicScreen extends javax.swing.JFrame {

    topic Topic;
    String topicName;
    String subtopic;

    /**
     * Creates new form surahScreen
     */
    public topicScreen() {
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

        topicScreen = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        topicDisplay = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        subTopicCBox = new javax.swing.JComboBox<>();
        subTopicGo = new javax.swing.JButton();
        transEng = new javax.swing.JButton();
        transUrdu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Result");
        setAlwaysOnTop(true);
        setResizable(false);

        topicScreen.setPreferredSize(new java.awt.Dimension(780, 480));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(770, 400));

        topicDisplay.setColumns(20);
        topicDisplay.setFont(topicDisplay.getFont().deriveFont(topicDisplay.getFont().getSize()+10f));
        topicDisplay.setLineWrap(true);
        topicDisplay.setRows(5);
        topicDisplay.setWrapStyleWord(true);
        jScrollPane1.setViewportView(topicDisplay);

        jLabel1.setText("Subtopics");

        subTopicGo.setText("Search");
        subTopicGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subTopicGoActionPerformed(evt);
            }
        });

        transEng.setText("English Translation");
        transEng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transEngActionPerformed(evt);
            }
        });

        transUrdu.setText("Urdu Translation");
        transUrdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transUrduActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topicScreenLayout = new javax.swing.GroupLayout(topicScreen);
        topicScreen.setLayout(topicScreenLayout);
        topicScreenLayout.setHorizontalGroup(
            topicScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topicScreenLayout.createSequentialGroup()
                .addGroup(topicScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topicScreenLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(subTopicCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(subTopicGo))
                    .addGroup(topicScreenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(topicScreenLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(transEng)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(transUrdu)
                .addGap(150, 150, 150))
        );
        topicScreenLayout.setVerticalGroup(
            topicScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topicScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(topicScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(subTopicCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subTopicGo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(topicScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transEng)
                    .addComponent(transUrdu)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topicScreen, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topicScreen, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void subTopicGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subTopicGoActionPerformed
        if (subTopicCBox.getSelectedItem() != null) {
            mainScreen.T = false;
            subtopic = (String) subTopicCBox.getSelectedItem();
            topicName = (String) mainScreen.topicCBox.getSelectedItem();
            Topic = mainScreen.q.getTopic(topicName);
            String print = mainScreen.q.searchSubTopic(Topic, subtopic);
            mainScreen.t.topicDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            mainScreen.t.topicDisplay.setText(print);
            mainScreen.t.topicDisplay.setCaretPosition(0);
        } else {
            mainScreen.t.topicDisplay.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            mainScreen.t.topicDisplay.setText("No Subtopics");
            mainScreen.t.topicDisplay.setCaretPosition(0);
        }
    }//GEN-LAST:event_subTopicGoActionPerformed

    private void transEngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transEngActionPerformed
        if (mainScreen.T == true) {
            topicName = (String) mainScreen.topicCBox.getSelectedItem();
            Topic = mainScreen.q.getTopic(topicName);
            if (Topic.ayatExist()) {
                String result = mainScreen.q.searchTopicEnglish(topicName);
                mainScreen.t.topicDisplay.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                mainScreen.t.topicDisplay.setText(result);
                mainScreen.t.topicDisplay.setCaretPosition(0);
            } else {
                mainScreen.t.topicDisplay.setText("PLEASE SELECT A SUBTOPIC");
            }
        } else {
            String print = mainScreen.q.searchSubTopicEnglish(Topic, subtopic);
            mainScreen.t.topicDisplay.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            mainScreen.t.topicDisplay.setText(print);
            mainScreen.t.topicDisplay.setCaretPosition(0);
        }
    }//GEN-LAST:event_transEngActionPerformed

    private void transUrduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transUrduActionPerformed
        if (mainScreen.T == true) {
            topicName = (String) mainScreen.topicCBox.getSelectedItem();
            Topic = mainScreen.q.getTopic(topicName);
            if (Topic.ayatExist()) {
                String result = mainScreen.q.searchTopicUrdu(topicName);
                mainScreen.t.topicDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                mainScreen.t.topicDisplay.setText(result);
                mainScreen.t.topicDisplay.setCaretPosition(0);
            } else {
                mainScreen.t.topicDisplay.setText("PLEASE SELECT A SUBTOPIC");
            }
        } else {
            String print = mainScreen.q.searchSubTopicUrdu(Topic, subtopic);
            mainScreen.t.topicDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            mainScreen.t.topicDisplay.setText(print);
            mainScreen.t.topicDisplay.setCaretPosition(0);
        }
    }//GEN-LAST:event_transUrduActionPerformed

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
            java.util.logging.Logger.getLogger(topicScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(topicScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(topicScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(topicScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new topicScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JComboBox<String> subTopicCBox;
    public javax.swing.JButton subTopicGo;
    public javax.swing.JTextArea topicDisplay;
    public javax.swing.JPanel topicScreen;
    private javax.swing.JButton transEng;
    private javax.swing.JButton transUrdu;
    // End of variables declaration//GEN-END:variables
}
