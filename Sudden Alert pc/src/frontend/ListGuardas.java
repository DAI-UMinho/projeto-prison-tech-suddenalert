/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;
import java.text.Normalizer.Form;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import static javax.swing.text.html.HTML.Tag.I;

/**
 *
 * @author Catarina
 */
public class ListGuardas extends javax.swing.JFrame implements Serializable {
    private DefaultTableModel modeloTabela;
    
    /**
     * Creates new form Reclusos
     */
    public ListGuardas() {
        initComponents();
        setIcon();
        jTable1.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD,12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(176,2,37));
        jTable1.getTableHeader().setForeground(new Color(255,255,255));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        sidepane9 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        home = new rsbuttom.RSButtonMetro();
        ent = new rsbuttom.RSButtonMetro();
        doc = new rsbuttom.RSButtonMetro();
        hor = new rsbuttom.RSButtonMetro();
        recl = new rsbuttom.RSButtonMetro();
        jPanel17 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sudden Alert");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label1.setFont(new java.awt.Font("Tw Cen MT", 1, 38)); // NOI18N
        label1.setForeground(new java.awt.Color(204, 204, 204));
        label1.setText("Listagem de Guardas Prisionais");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/ent.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"António Viera", "antonio2@gmail.com", "26"},
                {"Rita Coelho", "rita43@gmail.com", "32"}
            },
            new String [] {
                "Nome", "Email", "Pontos"
            }
        ));
        jTable1.setSelectionBackground(new java.awt.Color(255, 102, 102));
        jTable1.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(jTable1);

        jTextField1.setInheritsPopupMenu(true);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/pesquisar.png"))); // NOI18N

        jLabel1.setText("Filtrar Lista Por:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Email", "Pontos" }));
        jComboBox1.setInheritsPopupMenu(true);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/save.png"))); // NOI18N
        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/delete.png"))); // NOI18N
        jButton1.setText("Eliminar");
        jButton1.setInheritsPopupMenu(true);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setMaximumSize(new java.awt.Dimension(69, 79));
        jButton1.setMinimumSize(new java.awt.Dimension(69, 79));
        jButton1.setPreferredSize(new java.awt.Dimension(69, 79));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        BackButton.setBackground(new java.awt.Color(176, 2, 37));
        BackButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        BackButton.setForeground(new java.awt.Color(255, 255, 255));
        BackButton.setText("Cancelar");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/trofeu.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BackButton)
                .addGap(128, 128, 128))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(91, 91, 91))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        sidepane9.setBackground(new java.awt.Color(243, 243, 243));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/f.png"))); // NOI18N

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/ic_home.png"))); // NOI18N
        home.setText("         Menu Principal");
        home.setColorTextNormal(new java.awt.Color(153, 153, 153));
        home.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        home.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                homeMousePressed(evt);
            }
        });
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        ent.setForeground(new java.awt.Color(153, 153, 153));
        ent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/ic_ent.png"))); // NOI18N
        ent.setText("        Entidades");
        ent.setColorTextNormal(new java.awt.Color(153, 153, 153));
        ent.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                entMousePressed(evt);
            }
        });
        ent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entActionPerformed(evt);
            }
        });

        doc.setForeground(new java.awt.Color(153, 153, 153));
        doc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/ic_doc.png"))); // NOI18N
        doc.setText("         Relatórios");
        doc.setColorTextNormal(new java.awt.Color(153, 153, 153));
        doc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        doc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        doc.setInheritsPopupMenu(true);
        doc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                docMousePressed(evt);
            }
        });
        doc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                docActionPerformed(evt);
            }
        });

        hor.setForeground(new java.awt.Color(153, 153, 153));
        hor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/ic_horario.png"))); // NOI18N
        hor.setText("       Horários");
        hor.setColorTextNormal(new java.awt.Color(153, 153, 153));
        hor.setHideActionText(true);
        hor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        hor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        hor.setIconTextGap(9);
        hor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                horMousePressed(evt);
            }
        });
        hor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horActionPerformed(evt);
            }
        });

        recl.setForeground(new java.awt.Color(153, 153, 153));
        recl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/imagens/ic_recluso.png"))); // NOI18N
        recl.setText("        Reclusos");
        recl.setColorTextNormal(new java.awt.Color(153, 153, 153));
        recl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        recl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        recl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reclMousePressed(evt);
            }
        });
        recl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reclActionPerformed(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setPreferredSize(new java.awt.Dimension(0, 3));
        jPanel17.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout sidepane9Layout = new javax.swing.GroupLayout(sidepane9);
        sidepane9.setLayout(sidepane9Layout);
        sidepane9Layout.setHorizontalGroup(
            sidepane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(doc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(hor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
            .addComponent(recl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidepane9Layout.setVerticalGroup(
            sidepane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidepane9Layout.createSequentialGroup()
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(hor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(doc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(recl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidepane9, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidepane9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMousePressed
        this.recl.setSelected(false);
        //this.lrecl.setSelected(false);
        this.ent.setSelected(false);
        //this.lent.setSelected(false);
        this.doc.setSelected(false);
        this.hor.setSelected(false);
        this.home.setSelected(true);
    }//GEN-LAST:event_homeMousePressed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        Menu xMenu = new Menu();
        xMenu.setLocationRelativeTo(null);
        xMenu.setVisible(true);
        this.dispose();
        if (!this.home.isSelected()) {
            this.home.setColorNormal(new Color(255, 102, 102));
            this.home.setColorHover(new Color(255, 102, 102));
            this.home.setColorPressed(new Color(255, 102, 102));

            this.recl.setColorNormal(new Color(243, 243, 243));
            this.recl.setColorHover(new Color(255, 102, 102));
            this.recl.setColorPressed(new Color(243, 243, 243));

            /*this.lrecl.setColorNormal(new Color(243, 243, 243));
            this.lrecl.setColorHover(new Color(255, 102, 102));
            this.lrecl.setColorPressed(new Color(243, 243, 243));*/

            this.ent.setColorNormal(new Color(243, 243, 243));
            this.ent.setColorHover(new Color(255, 102, 102));
            this.ent.setColorPressed(new Color(243, 243, 243));

            /*this.lent.setColorNormal(new Color(243, 243, 243));
            this.lent.setColorHover(new Color(255, 102, 102));
            this.lent.setColorPressed(new Color(243, 243, 243));*/

            this.doc.setColorNormal(new Color(243, 243, 243));
            this.doc.setColorHover(new Color(255, 102, 102));
            this.doc.setColorPressed(new Color(243, 243, 243));

            this.hor.setColorNormal(new Color(243, 243, 243));
            this.hor.setColorHover(new Color(255, 102, 102));
            this.hor.setColorPressed(new Color(243, 243, 243));
        } else {
            this.home.setColorNormal(new Color(243, 243, 243));
            this.home.setColorHover(new Color(255, 102, 102));
            this.home.setColorPressed(new Color(243, 243, 243));
        }
    }//GEN-LAST:event_homeActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        Menu xMenu = new Menu();
        xMenu.setLocationRelativeTo(null);
        xMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void entMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_entMousePressed
        this.recl.setSelected(false);
        //this.lrecl.setSelected(false);
        this.ent.setSelected(true);
        //this.lent.setSelected(false);
        this.doc.setSelected(false);
        this.hor.setSelected(false);
        this.home.setSelected(false);
    }//GEN-LAST:event_entMousePressed

    private void entActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entActionPerformed
        Entidades_popup xEntidades_popup = new Entidades_popup();
        xEntidades_popup.setLocationRelativeTo(null);
        xEntidades_popup.setVisible(true);
        this.dispose();
        if (!this.ent.isSelected()) {
            this.home.setColorNormal(new Color(243, 243, 243));
            this.home.setColorHover(new Color(255, 102, 102));
            this.home.setColorPressed(new Color(243, 243, 243));

            this.recl.setColorNormal(new Color(243, 243, 243));
            this.recl.setColorHover(new Color(255, 102, 102));
            this.recl.setColorPressed(new Color(243, 243, 243));

            /*this.lrecl.setColorNormal(new Color(243, 243, 243));
            this.lrecl.setColorHover(new Color(255, 102, 102));
            this.lrecl.setColorPressed(new Color(243, 243, 243));*/

            this.ent.setColorNormal(new Color(255, 102, 102));
            this.ent.setColorHover(new Color(255, 102, 102));
            this.ent.setColorPressed(new Color(255, 102, 102));

            /*this.lent.setColorNormal(new Color(243, 243, 243));
            this.lent.setColorHover(new Color(255, 102, 102));
            this.lent.setColorPressed(new Color(243, 243, 243));*/

            this.doc.setColorNormal(new Color(243, 243, 243));
            this.doc.setColorHover(new Color(255, 102, 102));
            this.doc.setColorPressed(new Color(243, 243, 243));

            this.hor.setColorNormal(new Color(243, 243, 243));
            this.hor.setColorHover(new Color(255, 102, 102));
            this.hor.setColorPressed(new Color(243, 243, 243));
        } else {
            this.ent.setColorNormal(new Color(243, 243, 243));
            this.ent.setColorHover(new Color(255, 102, 102));
            this.ent.setColorPressed(new Color(243, 243, 243));
        }
    }//GEN-LAST:event_entActionPerformed

    private void docMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_docMousePressed
        this.recl.setSelected(false);
        //this.lrecl.setSelected(false);
        this.ent.setSelected(false);
        //this.lent.setSelected(false);
        this.doc.setSelected(true);
        this.hor.setSelected(false);
        this.home.setSelected(false);
    }//GEN-LAST:event_docMousePressed

    private void docActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_docActionPerformed
        Relatorios_popup xRelatorios_popup = new Relatorios_popup();
        xRelatorios_popup.setLocationRelativeTo(null);
        xRelatorios_popup.setVisible(true);
        this.dispose();
        if (!this.doc.isSelected()) {
            this.home.setColorNormal(new Color(243, 243, 243));
            this.home.setColorHover(new Color(255, 102, 102));
            this.home.setColorPressed(new Color(243, 243, 243));

            this.recl.setColorNormal(new Color(243, 243, 243));
            this.recl.setColorHover(new Color(255, 102, 102));
            this.recl.setColorPressed(new Color(243, 243, 243));

            /*this.lrecl.setColorNormal(new Color(243, 243, 243));
            this.lrecl.setColorHover(new Color(255, 102, 102));
            this.lrecl.setColorPressed(new Color(243, 243, 243));*/

            this.ent.setColorNormal(new Color(243, 243, 243));
            this.ent.setColorHover(new Color(255, 102, 102));
            this.ent.setColorPressed(new Color(243, 243, 243));

            /*this.lent.setColorNormal(new Color(243, 243, 243));
            this.lent.setColorHover(new Color(255, 102, 102));
            this.lent.setColorPressed(new Color(243, 243, 243));*/

            this.doc.setColorNormal(new Color(255, 102, 102));
            this.doc.setColorHover(new Color(255, 102, 102));
            this.doc.setColorPressed(new Color(255, 102, 102));

            this.hor.setColorNormal(new Color(243, 243, 243));
            this.hor.setColorHover(new Color(255, 102, 102));
            this.hor.setColorPressed(new Color(243, 243, 243));
        } else {
            this.doc.setColorNormal(new Color(243, 243, 243));
            this.doc.setColorHover(new Color(255, 102, 102));
            this.doc.setColorPressed(new Color(243, 243, 243));
        }
    }//GEN-LAST:event_docActionPerformed

    private void horMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_horMousePressed
        this.recl.setSelected(false);
        //this.lrecl.setSelected(false);
        this.ent.setSelected(false);
        //this.lent.setSelected(false);
        this.doc.setSelected(false);
        this.hor.setSelected(true);
        this.home.setSelected(false);
    }//GEN-LAST:event_horMousePressed

    private void horActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horActionPerformed
        ListHorarios xListHorarios = new ListHorarios();
        xListHorarios.setLocationRelativeTo(null);
        xListHorarios.setVisible(true);
        this.dispose();
        if (!this.hor.isSelected()) {
            this.home.setColorNormal(new Color(243, 243, 243));
            this.home.setColorHover(new Color(255, 102, 102));
            this.home.setColorPressed(new Color(243, 243, 243));

            this.recl.setColorNormal(new Color(243, 243, 243));
            this.recl.setColorHover(new Color(255, 102, 102));
            this.recl.setColorPressed(new Color(243, 243, 243));

            /*this.lrecl.setColorNormal(new Color(243, 243, 243));
            this.lrecl.setColorHover(new Color(255, 102, 102));
            this.lrecl.setColorPressed(new Color(243, 243, 243));*/

            this.ent.setColorNormal(new Color(243, 243, 243));
            this.ent.setColorHover(new Color(255, 102, 102));
            this.ent.setColorPressed(new Color(243, 243, 243));

            /*this.lent.setColorNormal(new Color(243, 243, 243));
            this.lent.setColorHover(new Color(255, 102, 102));
            this.lent.setColorPressed(new Color(243, 243, 243));*/

            this.doc.setColorNormal(new Color(243, 243, 243));
            this.doc.setColorHover(new Color(255, 102, 102));
            this.doc.setColorPressed(new Color(243, 243, 243));

            this.hor.setColorNormal(new Color(255, 102, 102));
            this.hor.setColorHover(new Color(255, 102, 102));
            this.hor.setColorPressed(new Color(255, 102, 102));
        } else {
            this.hor.setColorNormal(new Color(243, 243, 243));
            this.hor.setColorHover(new Color(255, 102, 102));
            this.hor.setColorPressed(new Color(243, 243, 243));
        }
    }//GEN-LAST:event_horActionPerformed

    private void reclMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reclMousePressed
        this.recl.setSelected(true);
        //this.lrecl.setSelected(false);
        this.ent.setSelected(false);
        //this.lent.setSelected(false);
        this.doc.setSelected(false);
        this.hor.setSelected(false);
        this.home.setSelected(false);
    }//GEN-LAST:event_reclMousePressed

    private void reclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reclActionPerformed
        Reclusos_popup xReclusos_popup = new Reclusos_popup();
        xReclusos_popup.setLocationRelativeTo(null);
        xReclusos_popup.setVisible(true);
        this.dispose();
        if (!this.recl.isSelected()) {
            this.home.setColorNormal(new Color(243, 243, 243));
            this.home.setColorHover(new Color(255, 102, 102));
            this.home.setColorPressed(new Color(243, 243, 243));

            this.recl.setColorNormal(new Color(255, 102, 102));
            this.recl.setColorHover(new Color(255, 102, 102));
            this.recl.setColorPressed(new Color(255, 102, 102));

            /*this.lrecl.setColorNormal(new Color(243, 243, 243));
            this.lrecl.setColorHover(new Color(255, 102, 102));
            this.lrecl.setColorPressed(new Color(243, 243, 243));*/

            this.ent.setColorNormal(new Color(243, 243, 243));
            this.ent.setColorHover(new Color(255, 102, 102));
            this.ent.setColorPressed(new Color(243, 243, 243));

            /*this.lent.setColorNormal(new Color(243, 243, 243));
            this.lent.setColorHover(new Color(255, 102, 102));
            this.lent.setColorPressed(new Color(243, 243, 243));*/

            this.doc.setColorNormal(new Color(243, 243, 243));
            this.doc.setColorHover(new Color(255, 102, 102));
            this.doc.setColorPressed(new Color(243, 243, 243));

            this.hor.setColorNormal(new Color(243, 243, 243));
            this.hor.setColorHover(new Color(255, 102, 102));
            this.hor.setColorPressed(new Color(243, 243, 243));

        } else {
            this.recl.setColorNormal(new Color(243, 243, 243));
            this.recl.setColorHover(new Color(255, 102, 102));
            this.recl.setColorPressed(new Color(243, 243, 243));
        }
    }//GEN-LAST:event_reclActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private rsbuttom.RSButtonMetro doc;
    private rsbuttom.RSButtonMetro ent;
    private rsbuttom.RSButtonMetro home;
    private rsbuttom.RSButtonMetro hor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label1;
    private rsbuttom.RSButtonMetro recl;
    private javax.swing.JPanel sidepane9;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("alert.png")));
    }
}
