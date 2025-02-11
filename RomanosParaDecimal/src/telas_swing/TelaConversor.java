package telas_swing;

/**
 *
 * @author Bruno Henrique de Pinho
 */

public class TelaConversor extends javax.swing.JFrame {

    public TelaConversor() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(420, 290);  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtField2 = new javax.swing.JTextField();
        btnSwap = new javax.swing.JButton();
        lblField2 = new javax.swing.JLabel();
        txtField1 = new javax.swing.JTextField();
        lblField1 = new javax.swing.JLabel();
        lblTituloPrograma = new javax.swing.JLabel();
        lblLinha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(420, 290));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtField2ActionPerformed(evt);
            }
        });
        txtField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtField2KeyPressed(evt);
            }
        });
        getContentPane().add(txtField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 140, 30));

        btnSwap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telas_swing/icones/SETA SWAP.png"))); // NOI18N
        btnSwap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwapActionPerformed(evt);
            }
        });
        getContentPane().add(btnSwap, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 50, 50));

        lblField2.setText("Romanos");
        getContentPane().add(lblField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        txtField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtField1ActionPerformed(evt);
            }
        });
        txtField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtField1KeyPressed(evt);
            }
        });
        getContentPane().add(txtField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 130, 30));

        lblField1.setText("Decimal");
        getContentPane().add(lblField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        lblTituloPrograma.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTituloPrograma.setText("CONVERSOR NUMÉRICO");
        getContentPane().add(lblTituloPrograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        lblLinha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLinha.setText("_____________________________________________________");
        getContentPane().add(lblLinha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSwapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwapActionPerformed
        String temp = lblField1.getText();
        lblField1.setText(lblField2.getText());
        lblField2.setText(temp);
    }//GEN-LAST:event_btnSwapActionPerformed

    private void txtField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtField1ActionPerformed
        
    }//GEN-LAST:event_txtField1ActionPerformed

    private void txtField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtField2ActionPerformed
        
    }//GEN-LAST:event_txtField2ActionPerformed

    private void txtField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtField1KeyPressed
        
    }//GEN-LAST:event_txtField1KeyPressed

    private void txtField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtField2KeyPressed
        
    }//GEN-LAST:event_txtField2KeyPressed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaConversor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSwap;
    private javax.swing.JLabel lblField1;
    private javax.swing.JLabel lblField2;
    private javax.swing.JLabel lblLinha;
    private javax.swing.JLabel lblTituloPrograma;
    private javax.swing.JTextField txtField1;
    private javax.swing.JTextField txtField2;
    // End of variables declaration//GEN-END:variables
}
