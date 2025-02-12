package telas_swing;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import processamento_conversor.NumerosRomanos;

/**
 *
 * @author Bruno Henrique de Pinho
 */
public class TelaConversor extends javax.swing.JFrame {

    static String modo_conversao = "DECIMAL-ROMANOS";

    public TelaConversor() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(420, 290);
        // DEFININDO O COMPONENTE DOCUMENT FILTER DO TXTFIELD PARA O UPPTERCASEDTEXTFIELDTESTER,
        // UM DOCUMENT FILTER QUE FORÇA O TXTFIELD A ESCREVER APENAS EM UPPERCASE.
        AbstractDocument document = (AbstractDocument) txtField1
                .getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset,
                    String string, AttributeSet attr)
                    throws BadLocationException {
                super.insertString(fb, offset, string.toUpperCase(), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length,
                    String text, AttributeSet attrs)
                    throws BadLocationException {
                super.insertString(fb, offset, text.toUpperCase(), attrs);
            }

        });
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

        txtField2.setEditable(false);
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

        lblField2.setForeground(new java.awt.Color(51, 51, 51));
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtField1KeyTyped(evt);
            }
        });
        getContentPane().add(txtField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 130, 30));

        lblField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
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

        if (modo_conversao.equals("DECIMAL-ROMANOS")) {
            modo_conversao = "ROMANOS-DECIMAL";
        } else {
            modo_conversao = "DECIMAL-ROMANOS";
        }

    }//GEN-LAST:event_btnSwapActionPerformed

    private void txtField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtField1ActionPerformed

    }//GEN-LAST:event_txtField1ActionPerformed

    private void txtField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtField2ActionPerformed

    }//GEN-LAST:event_txtField2ActionPerformed

    private void txtField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtField1KeyPressed

    }//GEN-LAST:event_txtField1KeyPressed

    private void txtField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtField2KeyPressed

    }//GEN-LAST:event_txtField2KeyPressed

    private void txtField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtField1KeyReleased
        // PASSO 1: AQUI ELE COLOCA O TEXTO INSERIDO EM CAIXA ALTA
        // PASSO 2: VERIFICAR SE ESTÁ EM ROMANOS, E MANDAR PARA A CONVERSÃO

        String numero_digitado = txtField1.getText();
        if (modo_conversao.equals("ROMANOS-DECIMAL")) {

            if (!numero_digitado.equals("")) {
                if (NumerosRomanos.simbolos_sao_romanos(numero_digitado) && NumerosRomanos.esta_em_romanos(numero_digitado)) {
                    int numero_em_decimais = NumerosRomanos.converter_para_decimais(numero_digitado);
                    txtField2.setText(Integer.toString(numero_em_decimais));
                } else {
                    JOptionPane.showMessageDialog(rootPane, "ERRO: O NÚMERO DIGITADO NÃO ESTÁ EM ROMANOS OU É INVÁLIDO. ");
                    JTextField txtPadrao = new javax.swing.JTextField();
                    txtField1.setDocument(txtPadrao.getDocument());
                    txtField1.setText("");
                    txtField2.setText("");
                    AbstractDocument document = (AbstractDocument) txtField1
                            .getDocument();
                    document.setDocumentFilter(new DocumentFilter() {
                        @Override
                        public void insertString(DocumentFilter.FilterBypass fb, int offset,
                                String string, AttributeSet attr)
                                throws BadLocationException {
                            super.insertString(fb, offset, string.toUpperCase(), attr);
                        }

                        @Override
                        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
                                String text, AttributeSet attrs)
                                throws BadLocationException {
                            super.insertString(fb, offset, text.toUpperCase(), attrs);
                        }

                    });
                }
            } else{
                txtField2.setText("");
            }
        } else {

        }
    }//GEN-LAST:event_txtField1KeyReleased

    private void txtField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtField1KeyTyped

    }//GEN-LAST:event_txtField1KeyTyped

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
