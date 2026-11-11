/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package id.ac.uniska.pbo2.p07;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Akmal adi Rizky - 2410010420
 * 
 */
public class KatalogBuku extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(KatalogBuku.class.getName());

    private final BukuTableModel tableModel = new BukuTableModel();
    private final TableRowSorter<BukuTableModel> sorter = new TableRowSorter<>(tableModel);

    public KatalogBuku() {
        initComponents();
        aturTampilan();
        isiDataAwal();
        pasangAksi();
        perbaruiStatus();
    }
    
    private void aturTampilan() {
        setTitle("Katalog Buku Perpustakaan");
        setMinimumSize(new Dimension(760, 460));
        setSize(900, 560);
        setLocationRelativeTo(null);

        bukuTable.setModel(tableModel);
        bukuTable.setRowSorter(sorter);
        bukuTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bukuTable.setRowHeight(26);
        bukuTable.getTableHeader().setReorderingAllowed(false);
        bukuTable.setFillsViewportHeight(true);

        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jLabel3.setFont(jLabel3.getFont().deriveFont(java.awt.Font.BOLD, 16f));
        atasPAnel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        tablePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 10, 10));
        statusLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12));
        cariField.setToolTipText("Cari berdasarkan kode, judul, atau penulis");
        cariField.setColumns(24);
        ubahButton.setText("Ubah");
        ubahButton.setEnabled(false);
        hapusButton.setEnabled(false);
        ubahItem.setEnabled(false);
        hapusItem.setEnabled(false);
    }

    private void isiDataAwal() {
        tableModel.tambah(new Buku("B1", "Matematika", "Kemendikbud", 2022, StatusBuku.TERSEDIA));
        tableModel.tambah(new Buku("B2", "Bahasa Indonesia", "Kemendikbud", 2021, StatusBuku.TERSEDIA));
        tableModel.tambah(new Buku("B3", "Bahasa Inggris", "Widiati", 2017, StatusBuku.DIPINJAM));
        tableModel.tambah(new Buku("B4", "Pendidikan Pancasila", "Kemendikbud", 2023, StatusBuku.TERSEDIA));
        tableModel.tambah(new Buku("B5", "Informatika", "Kemendikbud", 2022, StatusBuku.TERSEDIA));
        tableModel.tambah(new Buku("B6", "Algoritma dan Pemrograman", "Rinaldi Munir", 2016, StatusBuku.DIPINJAM));
    }

    private void pasangAksi() {
        tambahButton.addActionListener(e -> tambahBuku());
        tambahItem.addActionListener(e -> tambahBuku());
        ubahButton.addActionListener(e -> ubahBuku());
        ubahItem.addActionListener(e -> ubahBuku());
        hapusButton.addActionListener(e -> hapusBuku());
        hapusItem.addActionListener(e -> hapusBuku());
        keluarItem.addActionListener(e -> dispose());

        bukuTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                boolean dipilih = bukuTable.getSelectedRow() >= 0;
                ubahButton.setEnabled(dipilih);
                hapusButton.setEnabled(dipilih);
                ubahItem.setEnabled(dipilih);
                hapusItem.setEnabled(dipilih);
            }
        });

        cariField.getDocument().addDocumentListener(new DocumentListener() {
            private void filter() {
                String kata = cariField.getText().trim();
                sorter.setRowFilter(kata.isEmpty() ? null : RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(kata)));
            }
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) { filter(); }
        });
    }

    private void tambahBuku() {
        DialogBuku dialog = new DialogBuku(this, true, tableModel.getSemuaBuku(), null);
        dialog.setVisible(true);
        if (dialog.getHasil() != null) {
            tableModel.tambah(dialog.getHasil());
            perbaruiStatus();
        }
    }

    private void ubahBuku() {
        int barisView = bukuTable.getSelectedRow();
        if (barisView < 0) {
            JOptionPane.showMessageDialog(this, "Pilih buku yang ingin diubah.");
            return;
        }
        int barisModel = bukuTable.convertRowIndexToModel(barisView);
        Buku bukuLama = tableModel.getBuku(barisModel);
        DialogBuku dialog = new DialogBuku(this, true, tableModel.getSemuaBuku(), bukuLama);
        dialog.setVisible(true);
        if (dialog.getHasil() != null) {
            tableModel.ubah(barisModel, dialog.getHasil());
            perbaruiStatus();
        }
    }

    private void hapusBuku() {
        int barisView = bukuTable.getSelectedRow();
        if (barisView < 0) {
            JOptionPane.showMessageDialog(this, "Pilih buku yang ingin dihapus.");
            return;
        }
        int pilihan = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus data buku ini?", "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pilihan == JOptionPane.YES_OPTION) {
            tableModel.hapus(bukuTable.convertRowIndexToModel(barisView));
            perbaruiStatus();
        }
    }

    private void perbaruiStatus() {
        List<Buku> semua = tableModel.getSemuaBuku();
        long tersedia = semua.stream().filter(b -> b.getStatus() == StatusBuku.TERSEDIA).count();
        long dipinjam = semua.size() - tersedia;
        statusLabel.setText("Jumlah Buku: " + semua.size()
                + "   |   Tersedia: " + tersedia + "   |   Dipinjam: " + dipinjam);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        atasPAnel = new javax.swing.JPanel();
        tombolPanel = new javax.swing.JPanel();
        tambahButton = new javax.swing.JButton();
        ubahButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        cariField = new javax.swing.JTextField();
        tablePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tableScroll = new javax.swing.JScrollPane();
        bukuTable = new javax.swing.JTable();
        statusLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        keluarItem = new javax.swing.JMenuItem();
        bukuMenu = new javax.swing.JMenu();
        tambahItem = new javax.swing.JMenuItem();
        ubahItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        hapusItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        atasPAnel.setLayout(new java.awt.BorderLayout());

        tombolPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        tambahButton.setText("Tambah");
        tombolPanel.add(tambahButton);

        ubahButton.setText("ubah");
        tombolPanel.add(ubahButton);

        hapusButton.setText("Hapus");
        tombolPanel.add(hapusButton);

        atasPAnel.add(tombolPanel, java.awt.BorderLayout.LINE_START);

        cariField.addActionListener(this::cariFieldActionPerformed);
        atasPAnel.add(cariField, java.awt.BorderLayout.CENTER);

        getContentPane().add(atasPAnel, java.awt.BorderLayout.PAGE_START);

        tablePanel.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Daftar Buku");
        tablePanel.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        bukuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableScroll.setViewportView(bukuTable);

        tablePanel.add(tableScroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(tablePanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(statusLabel, java.awt.BorderLayout.PAGE_END);

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");

        keluarItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        keluarItem.setText("Keluar");
        fileMenu.add(keluarItem);

        menuBar.add(fileMenu);

        bukuMenu.setMnemonic('B');
        bukuMenu.setText("Buku");

        tambahItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        tambahItem.setText("Tambah");
        bukuMenu.add(tambahItem);

        ubahItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ubahItem.setText("Ubah");
        bukuMenu.add(ubahItem);
        bukuMenu.add(jSeparator1);

        hapusItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        hapusItem.setText("Hapus");
        bukuMenu.add(hapusItem);

        menuBar.add(bukuMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cariFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariFieldActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new KatalogBuku().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel atasPAnel;
    private javax.swing.JMenu bukuMenu;
    private javax.swing.JTable bukuTable;
    private javax.swing.JTextField cariField;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton hapusButton;
    private javax.swing.JMenuItem hapusItem;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem keluarItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JButton tambahButton;
    private javax.swing.JMenuItem tambahItem;
    private javax.swing.JPanel tombolPanel;
    private javax.swing.JButton ubahButton;
    private javax.swing.JMenuItem ubahItem;
    // End of variables declaration//GEN-END:variables
}
