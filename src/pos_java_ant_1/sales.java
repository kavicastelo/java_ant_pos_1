/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package pos_java_ant_1;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KAVI
 */
public class sales extends javax.swing.JPanel {

    public static String bar_code;
    public static String customer_ID;
    public static String product_id;
    public static String stock;
    public static String available;
    
    public static Double rest_qty;
    /**
     * Creates new form sales
     */
    public sales() {
        initComponents();
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(30);
        data_load();
    }
    
    public void data_load(){
        //customer
        try {
            Statement s = db.myCon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM customer");
            
            Vector v = new Vector();
            
            while (rs.next()) {                
                v.add(rs.getString("name"));
                
                DefaultComboBoxModel combo = new DefaultComboBoxModel(v);
                customer_combo.setModel(combo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //product
        try {
            Statement s = db.myCon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM product");
            
            Vector v = new Vector();
            
            while (rs.next()) {                
                v.add(rs.getString("name"));
                
                DefaultComboBoxModel combo = new DefaultComboBoxModel(v);
                product_combo.setModel(combo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //set invoice id and increment
        try {
            Statement s = db.myCon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM extra WHERE xid=1");
            
            if (rs.next()) {
                inid.setText(rs.getString("val"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        int i = Integer.valueOf(inid.getText());
        i++;
        inid.setText(String.valueOf(i));
    }

    public void calculate_total(){
        Double qt = Double.valueOf(qty.getText());
        Double price = Double.valueOf(unit_price.getText());
        Double tot = qt * price;
            
        tot_price.setText(tot.toString());
    }
    
    public void total(){
        int rowcount = jTable1.getRowCount();
        double total = 0;
        int qty = 0;
        
        for (int i = 0; i < rowcount; i++) {
            double val = Double.valueOf(jTable1.getValueAt(i, 5).toString());
            total += val;
        }
        
        for (int i = 0; i < rowcount; i++) {
            double val = Double.valueOf(jTable1.getValueAt(i, 3).toString());
            qty += val;
        }
        
        tot.setText(Double.toString(total));
        tot_qty.setText(Integer.toString(qty));
    }
    
    public void pay(){
        DecimalFormat df = new DecimalFormat("000.00");
        
        double paid = Double.valueOf(payment.getText());
        double total = Double.valueOf(tot.getText());
        
        double ship = Double.valueOf(shipping.getText());
        double tx = Double.valueOf(tax.getText());
        double disc = Double.valueOf(dis.getText());
        
        double tax_amnt = total * tx /100;
        double dis_amnt = total * disc /100;
        double grand_tot = (total + ship + tax_amnt) - dis_amnt;
        double bal = paid - grand_tot;
            
        balance.setText(df.format(bal));
        topbal.setText(df.format(bal));
        shiplbl.setText(df.format(ship));
        taxlbl.setText(df.format(tax_amnt));
        dislbl.setText(df.format(dis_amnt));
        grandtot.setText(df.format(grand_tot));
    }
    
    public void add_to_cart(){
        
        List<String> formFields = new ArrayList<>();
        formFields.add(citi.getText());
        formFields.add(bar.getText());
        formFields.add(qty.getText());
        
        boolean allFieldsFilled = true;

        for (String field : formFields) {
            if (field.isEmpty()) {
                allFieldsFilled = false;
                break;
            }
        }
        
        if (allFieldsFilled) {
            double qt = Double.valueOf(qty.getText());
        double stk = Double.valueOf(stock);
        if (stk >= qt) {
            try {
                rest_qty = stk-qt;
                
                DefaultTableModel tm = (DefaultTableModel)jTable1.getModel();
                Vector v = new Vector();
        
                v.add(inid.getText());
                v.add(product_combo.getSelectedItem());
                v.add(bar_code);
                v.add(qty.getText());
                v.add(unit_price.getText());
                v.add(tot_price.getText());
                v.add(product_id);
                v.add(String.valueOf(rest_qty));
        
                tm.addRow(v);
                
                available = String.valueOf(rest_qty);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Somethings wrong! try again");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Only available "+available+" products!");
        }
        total();
        pay();
        } else {
            JOptionPane.showMessageDialog(null, "Please fill in the all fields correctly!");
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        inid = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        unit_price = new javax.swing.JLabel();
        customer_combo = new javax.swing.JComboBox<>();
        product_combo = new javax.swing.JComboBox<>();
        qty = new javax.swing.JTextField();
        tot_price = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        bar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        citi = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        topbal = new javax.swing.JLabel();
        barlbl = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        tot = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        shiplbl = new javax.swing.JLabel();
        taxlbl = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dislbl = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        grandtot = new javax.swing.JLabel();
        balance = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tot_qty = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        shipping = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tax = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dis = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        payment = new javax.swing.JTextField();
        pat_cmb = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        ref = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        inid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        inid.setText("01");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("INVOICE NO :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inid)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(inid))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer.png"))); // NOI18N
        jLabel3.setText("Customer :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/product.png"))); // NOI18N
        jLabel4.setText("Product :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/qty.png"))); // NOI18N
        jLabel5.setText("Qty :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.png"))); // NOI18N
        jLabel6.setText("Unit :");

        unit_price.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        unit_price.setText("00.00");

        customer_combo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        customer_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        customer_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_comboActionPerformed(evt);
            }
        });

        product_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        product_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_comboActionPerformed(evt);
            }
        });
        product_combo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                product_comboKeyReleased(evt);
            }
        });

        qty.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        qty.setText("1");
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
        });

        tot_price.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tot_price.setText("00.00");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.png"))); // NOI18N
        jLabel7.setText("Total :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barcode.png"))); // NOI18N
        jLabel12.setText("Barcode :");

        bar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                barKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sales_menu.png"))); // NOI18N
        jLabel10.setText("City :");

        citi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.png"))); // NOI18N
        jLabel14.setText("Balance :");

        topbal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        topbal.setForeground(new java.awt.Color(255, 0, 51));
        topbal.setText("0");

        barlbl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        barlbl.setText("0");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/barcode.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(customer_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(product_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unit_price)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tot_price)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(citi, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(topbal, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(barlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(customer_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topbal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(citi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(unit_price)
                        .addComponent(jLabel7)
                        .addComponent(tot_price))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bar)
                            .addComponent(jLabel4)
                            .addComponent(product_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Sub Total :");

        tot.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tot.setText("00.00");
        tot.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("Shipping Cost :");

        shiplbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        shiplbl.setText("00.00");
        shiplbl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        taxlbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        taxlbl.setText("00.00");
        taxlbl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel20.setText("Tax Amount :");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel21.setText("Discount Amount :");

        dislbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        dislbl.setText("00.00");
        dislbl.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setBackground(new java.awt.Color(0, 153, 51));
        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Grand Total Amount :");
        jLabel22.setOpaque(true);

        grandtot.setBackground(new java.awt.Color(0, 153, 51));
        grandtot.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        grandtot.setForeground(new java.awt.Color(255, 255, 255));
        grandtot.setText("00.00");
        grandtot.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        grandtot.setOpaque(true);

        balance.setBackground(new java.awt.Color(255, 51, 0));
        balance.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        balance.setForeground(new java.awt.Color(255, 255, 255));
        balance.setText("00.00");
        balance.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        balance.setOpaque(true);

        jLabel23.setBackground(new java.awt.Color(255, 51, 0));
        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Balance/Due :");
        jLabel23.setOpaque(true);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tot, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(shiplbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(taxlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dislbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(grandtot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(shiplbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(taxlbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(dislbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(grandtot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Total QTY :");

        tot_qty.setText("00");

        shipping.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        shipping.setText("0");
        shipping.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                shippingKeyReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Shipping Cost :");

        tax.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tax.setText("2.5");
        tax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                taxKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Tax :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Discount :");

        dis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dis.setText("5");
        dis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disActionPerformed(evt);
            }
        });
        dis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                disKeyReleased(evt);
            }
        });

        jLabel13.setText("%");

        jLabel25.setText("%");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(shipping))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dis, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shipping, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(tax, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(dis, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Payed Amount :");

        payment.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        payment.setText("0");
        payment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paymentKeyReleased(evt);
            }
        });

        pat_cmb.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pat_cmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASH", "CREDIT", "CHECK", "ONLINE TRANSFER" }));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Method :");

        ref.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("Ref No :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(payment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pat_cmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ref)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel19)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pat_cmb)
                    .addComponent(payment)
                    .addComponent(ref))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tot_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(tot_qty)))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setText("Pay & Print");
        jButton4.setMaximumSize(new java.awt.Dimension(31, 31));
        jButton4.setMinimumSize(new java.awt.Dimension(31, 31));
        jButton4.setPreferredSize(new java.awt.Dimension(31, 31));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INID", "Name", "Bar Code ", "QTY", "Unit Price", "Total Price", "(P_ID)", "(Available)"
            }
        ));
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jButton1.setText("Add to Cart");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton2.setText("Remove All");
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove.png"))); // NOI18N
        jButton3.setText("Remove");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel tm = (DefaultTableModel)jTable1.getModel();
            tm.setRowCount(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Your Item list is already empty");
        }
        total();
        pay();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel tm = (DefaultTableModel)jTable1.getModel();
            int row = jTable1.getSelectedRow();
            
            tm.removeRow(row);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
        total();
        pay();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void product_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_comboActionPerformed
        // TODO add your handling code here:
        String name = product_combo.getSelectedItem().toString();
       
        try {
            Statement s = db.myCon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM product WHERE name='"+name+"'");
            
            if (rs.next()) {
                product_id = rs.getString("pid");
                unit_price.setText(rs.getString("sell_p"));
                bar_code = rs.getString("bar");
                stock = rs.getString("qty");
                available = rs.getString("qty");
                barlbl.setText(rs.getString("bar"));
                
                bar.setText(rs.getString("bar"));
            }
            else{
                data_load();
            }
            
//            available.setText(stock);
            calculate_total();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //focus qty
        qty.selectAll();
        qty.requestFocus();
    }//GEN-LAST:event_product_comboActionPerformed

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
        // TODO add your handling code here:
        try {
            calculate_total();
            
            if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
                add_to_cart();
                bar.selectAll();
                bar.requestFocus();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Quantity is can not be empty");
        }
    }//GEN-LAST:event_qtyKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        add_to_cart();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void paymentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paymentKeyReleased
        // TODO add your handling code here:
        try {
            pay();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Payment can not be empty");
        }
    }//GEN-LAST:event_paymentKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getRowCount();
        if (row != 0) {
            try {
            //`cid`, `inid`, `prod_name`, `bar`, `qty`, `unit_price`, `total_price`
            DefaultTableModel tm = (DefaultTableModel)jTable1.getModel();
            int row_count = jTable1.getRowCount();
            
            for (int i = 0; i < row_count; i++) {
                String inid = tm.getValueAt(i, 0).toString();
                String name = tm.getValueAt(i, 1).toString();
                String bar = tm.getValueAt(i, 2).toString();
                String qty = tm.getValueAt(i, 3).toString();
                String u_price = tm.getValueAt(i, 4).toString();
                String tot_price = tm.getValueAt(i, 5).toString();
                
                   Statement s = db.myCon().createStatement();
                    //cart table
                    s.executeUpdate("INSERT INTO cart (inid, prod_name, bar, qty, unit_price, total_price) VALUES ('"+inid+"','"+name+"','"+bar+"','"+qty+"','"+u_price+"','"+tot_price+"')"); 
            }
            
            //`sid`, `inid`, `cid`, `c_name`, `t_qty`, `bill`, `status`, `bal`
            try {
                String iid = inid.getText();
                String name = customer_combo.getSelectedItem().toString();
                String t_qty = tot_qty.getText();
                String bill = tot.getText();
                String bal = shiplbl.getText();
                String status = null;
                String method = pat_cmb.getSelectedItem().toString();
                
                Double total = Double.valueOf(tot.getText());
                Double paid = Double.valueOf(payment.getText());
                if (paid.equals(0.0)) {
                    status = "unpaid";
                } else if (total>paid) {
                    status = "partial";
                } else if (total<=paid) {
                    status = "paid";
                }
                
                Statement s = db.myCon().createStatement();
                //sales table
                s.executeUpdate("INSERT INTO sales (inid, cid, c_name, t_qty, bill, status, bal, method) VALUES ('"+iid+"','"+customer_ID+"','"+name+"','"+t_qty+"','"+bill+"','"+status+"','"+bal+"','"+method+"')");
            } catch (SQLException e) {
                try {
                Statement st = db.myCon().createStatement();
                st.executeUpdate("CREATE TABLE `sales` (\n" +
"  `sid` INT NOT NULL AUTO_INCREMENT,\n" +
"  `inid` INT NOT NULL,\n" +
"  `cid` INT NOT NULL,\n" +
"  `c_name` VARCHAR(20) NOT NULL,\n" +
"  `t_qty` VARCHAR(15) NOT NULL,\n" +
"  `bill` DECIMAL(10, 2) NOT NULL,\n" +
"  `status` VARCHAR(10) NOT NULL,\n" +
"  `bal` DECIMAL(10, 2) NOT NULL,\n" +
"  `method` VARCHAR(25) NOT NULL\n" +
")");
                JOptionPane.showMessageDialog(null, "Please try again in first attemt when application launch");
            } catch (Exception ex) {
                System.out.println(ex);
            }
                JOptionPane.showMessageDialog(null, "Select customer again and retry!");
            }
            
            try {
                String current_id = inid.getText();
                
                Statement s = db.myCon().createStatement();
                s.executeUpdate("UPDATE extra SET val = '"+current_id+"' WHERE xid = 1");
            } catch (SQLException e) {
                System.out.println(e);
                try {
                Statement st = db.myCon().createStatement();
                st.executeUpdate("CREATE TABLE `extra` (\n" +
"  `xid` INT NOT NULL AUTO_INCREMENT,\n" +
"  `val` VARCHAR(10) NOT NULL\n" +
")");
                st.executeUpdate("INSERT INTO extra (val) VALUES ('"+inid.getText()+"')");
                JOptionPane.showMessageDialog(null, "Please try again in first attemt when application launch");
            } catch (Exception ex) {
                System.out.println(ex);
            }
            }
            
            
            try {
                for (int i = 0; i < row_count; i++) {
                    
                    String p_id = tm.getValueAt(i, 6).toString();
                    String qt = tm.getValueAt(i, 7).toString();
                    
                    Statement s = db.myCon().createStatement();
                    s.executeUpdate("UPDATE product SET qty = '"+qt+"' WHERE pid = '"+p_id+"'");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            
            JOptionPane.showMessageDialog(null, "Payment Added");
            data_load();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            try {
                Statement st = db.myCon().createStatement();
                st.executeUpdate("CREATE TABLE `cart` (\n" +
"  `cid` INT NOT NULL AUTO_INCREMENT,\n" +
"  `inid` INT NOT NULL,\n" +
"  `prod_name` VARCHAR(10) NOT NULL,\n" +
"  `bar` VARCHAR(20) NOT NULL,\n" +
"  `qty` INT NOT NULL,\n" +
"  `unit_price` DECIMAL(10, 2) NOT NULL,\n" +
"  `total_price` DECIMAL(10, 2) NOT NULL\n" +
")");
                JOptionPane.showMessageDialog(null, "Please try again in first attempt when application launch");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        
        //print bill
        try {
            int i = Integer.valueOf(inid.getText());
            int id_val = i-1;
                    
            HashMap map = new HashMap();
            map.put("cart_id_para", id_val);
        
            ReportView rw = new ReportView("C:\\Flexiart POS\\reports\\print.jasper",map);
            rw.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Somethings wrong in your system!");
        }
        } else {
            JOptionPane.showMessageDialog(null, "No Items to found in table!");
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void customer_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customer_comboActionPerformed
        // TODO add your handling code here:
        String name = customer_combo.getSelectedItem().toString();
       
        try {
            Statement s = db.myCon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM customer WHERE name='"+name+"'");
            
            if (rs.next()) {
                customer_ID = rs.getString("cid");
                citi.setText(rs.getString("city"));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_customer_comboActionPerformed

    private void barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barKeyReleased
        // TODO add your handling code here:
        String barc = bar.getText();
       
        try {
            Statement s = db.myCon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM product WHERE bar = '"+barc+"'");
            
            if (rs.next()) {
                product_id = rs.getString("pid");
                unit_price.setText(rs.getString("sell_p"));
                bar_code = rs.getString("bar");
                stock = rs.getString("qty");
                available = rs.getString("qty");
                barlbl.setText(rs.getString("bar"));
                
                Vector v = new Vector();
                v.add(rs.getString("name"));
                DefaultComboBoxModel cmb = new DefaultComboBoxModel(v);
                product_combo.setModel(cmb);
            }
            else{
                data_load();
            }
            
//            available.setText(stock);
            calculate_total();
            
        } catch (Exception e) {
            data_load();
            System.out.println(e);
        }
        
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            qty.selectAll();
            qty.requestFocus();
        }
    }//GEN-LAST:event_barKeyReleased

    private void shippingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shippingKeyReleased
        // TODO add your handling code here:
        try {
            pay();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Field can not be empty");
        }
    }//GEN-LAST:event_shippingKeyReleased

    private void taxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taxKeyReleased
        // TODO add your handling code here:
        try {
            pay();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Field can not be empty");
        }
    }//GEN-LAST:event_taxKeyReleased

    private void disKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_disKeyReleased
        // TODO add your handling code here:
        try {
            pay();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Field can not be empty");
        }
    }//GEN-LAST:event_disKeyReleased

    private void disActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_disActionPerformed

    private void product_comboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_product_comboKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_product_comboKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel balance;
    private javax.swing.JTextField bar;
    private javax.swing.JLabel barlbl;
    private javax.swing.JLabel citi;
    private javax.swing.JComboBox<String> customer_combo;
    private javax.swing.JTextField dis;
    private javax.swing.JLabel dislbl;
    private javax.swing.JLabel grandtot;
    private javax.swing.JLabel inid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> pat_cmb;
    private javax.swing.JTextField payment;
    private javax.swing.JComboBox<String> product_combo;
    private javax.swing.JTextField qty;
    private javax.swing.JTextField ref;
    private javax.swing.JLabel shiplbl;
    private javax.swing.JTextField shipping;
    private javax.swing.JTextField tax;
    private javax.swing.JLabel taxlbl;
    private javax.swing.JLabel topbal;
    private javax.swing.JLabel tot;
    private javax.swing.JLabel tot_price;
    private javax.swing.JLabel tot_qty;
    private javax.swing.JLabel unit_price;
    // End of variables declaration//GEN-END:variables
}
