/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.uniska.pbo2.p08.uts.b;

/**
 *
 * @author Akmal
 */
// Nama: ISI NAMA ANDA
// NPM: ISI NPM ANDA

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.Locale;

public class TabelBelanjaMode extends AbstractTableModel {

    private final List<Barang> daftarBarang = new ArrayList<>();

    private final String[] kolom = {
        "Nama Barang",
        "Kategori",
        "Harga",
        "Jumlah",
        "Subtotal"
    };

    private final NumberFormat rupiah =
        NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    @Override
    public int getRowCount() {
        return daftarBarang.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolom[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Barang barang = daftarBarang.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return barang.getNama();

            case 1:
                return barang.getKategori();

            case 2:
                return formatRupiah(barang.getHarga());

            case 3:
                return barang.getJumlah();

            case 4:
                return formatRupiah(barang.getSubtotal());

            default:
                return null;
        }
    }

    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);

        int baris = daftarBarang.size() - 1;

        fireTableRowsInserted(baris, baris);
    }

    public boolean sudahAda(String nama) {

        for (Barang barang : daftarBarang) {

            if (barang.getNama().equalsIgnoreCase(nama)) {
                return true;
            }
        }

        return false;
    }

    public long getTotal() {

        long total = 0;

        for (Barang barang : daftarBarang) {
            total += barang.getSubtotal();
        }

        return total;
    }

    public long getDiskon() {

        if (getTotal() >= 100000) {
            return getTotal() / 10;
        }

        return 0;
    }

    public long getBayar() {
        return getTotal() - getDiskon();
    }

    public String formatRupiah(long nilai) {
        return "Rp" + String.format("%,d", nilai)
                .replace(',', '.');
    }
}
