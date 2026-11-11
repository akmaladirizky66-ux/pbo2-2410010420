package id.ac.uniska.pbo2.p07;

/**
 * Merepresentasikan satu data buku pada katalog perpustakaan.
 *
 * @author Akmal adi Rizky - 2410010420
 */
public class Buku {
    private String kode;
    private String judul;
    private String penulis;
    private int tahunTerbit;
    private StatusBuku status;

    public Buku(String kode, String judul, String penulis, int tahunTerbit, StatusBuku status) {
        this.kode = kode;
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.status = status;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }
    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }
    public String getPenulis() { return penulis; }
    public void setPenulis(String penulis) { this.penulis = penulis; }
    public int getTahunTerbit() { return tahunTerbit; }
    public void setTahunTerbit(int tahunTerbit) { this.tahunTerbit = tahunTerbit; }
    public StatusBuku getStatus() { return status; }
    public void setStatus(StatusBuku status) { this.status = status; }
}
