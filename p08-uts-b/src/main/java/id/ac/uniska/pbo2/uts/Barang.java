/*
 * Nama : Akmal Adi Rizky
 * NPM  : 2410010420
 */
package id.ac.uniska.pbo2.uts;

/**
 * Data satu barang belanja beserta perhitungan subtotalnya.
 */
public class Barang {

    private final String nama;
    private final Kategori kategori;
    private final long harga;
    private final int jumlah;

    public Barang(String nama, Kategori kategori, long harga, int jumlah) {
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public String getNama() {
        return nama;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public long getHarga() {
        return harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    /** Subtotal = harga dikali jumlah. */
    public long getSubtotal() {
        return harga * jumlah;
    }
}
