/*
 * Nama : Akmal Adi Rizky
 * NPM  : 24109010420
 */
package id.ac.uniska.pbo2.uts;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Model tabel daftar belanja. Selain menyimpan data barang, model ini
 * menghitung total, diskon, dan jumlah bayar.
 */
public class DaftarBelanjaTableModel extends AbstractTableModel {

    private static final String[] NAMA_KOLOM = {
        "Nama Barang", "Kategori", "Harga", "Jumlah", "Subtotal"
    };
    private static final long BATAS_DISKON = 100000;
    private static final int PERSEN_DISKON = 10;

    private final List<Barang> daftarBarang = new ArrayList<>();

    @Override
    public int getRowCount() {
        return daftarBarang.size();
    }

    @Override
    public int getColumnCount() {
        return NAMA_KOLOM.length;
    }

    @Override
    public String getColumnName(int kolom) {
        return NAMA_KOLOM[kolom];
    }

    @Override
    public Object getValueAt(int baris, int kolom) {
        Barang barang = daftarBarang.get(baris);
        switch (kolom) {
            case 0:
                return barang.getNama();
            case 1:
                return barang.getKategori().getLabel();
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

    /** Menambahkan barang ke daftar dan memberi tahu tabel agar menggambar ulang. */
    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
        int barisBaru = daftarBarang.size() - 1;
        fireTableRowsInserted(barisBaru, barisBaru);
    }

    /** Memeriksa apakah nama barang sudah ada, tanpa membedakan huruf besar dan kecil. */
    public boolean sudahAda(String nama) {
        for (Barang barang : daftarBarang) {
            if (barang.getNama().equalsIgnoreCase(nama)) {
                return true;
            }
        }
        return false;
    }

    /** Total = jumlah seluruh subtotal. */
    public long hitungTotal() {
        long total = 0;
        for (Barang barang : daftarBarang) {
            total += barang.getSubtotal();
        }
        return total;
    }

    /** Diskon 10 persen (dibulatkan ke bawah) jika total minimal Rp100.000, selain itu Rp0. */
    public long hitungDiskon() {
        long total = hitungTotal();
        if (total >= BATAS_DISKON) {
            return total * PERSEN_DISKON / 100;
        }
        return 0;
    }

    /** Bayar = total dikurangi diskon. */
    public long hitungBayar() {
        return hitungTotal() - hitungDiskon();
    }

    /** Teks ringkasan, misalnya: Total Rp125.000, diskon Rp12.500, bayar Rp112.500. */
    public String getRingkasan() {
        return "Total " + formatRupiah(hitungTotal())
                + ", diskon " + formatRupiah(hitungDiskon())
                + ", bayar " + formatRupiah(hitungBayar());
    }

    /** Memformat angka menjadi rupiah, misalnya 125000 menjadi Rp125.000. */
    public static String formatRupiah(long nilai) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols();
        simbol.setGroupingSeparator('.');
        DecimalFormat format = new DecimalFormat("#,##0", simbol);
        return "Rp" + format.format(nilai);
    }
}
