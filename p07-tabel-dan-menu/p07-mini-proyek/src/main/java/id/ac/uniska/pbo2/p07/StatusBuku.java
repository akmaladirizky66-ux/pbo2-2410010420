package id.ac.uniska.pbo2.p07;

/**
 * Pilihan status ketersediaan buku.
 *
 * @author Akmal Adi Rizky - 2410010420
 */
public enum StatusBuku {
    TERSEDIA("Tersedia"),
    DIPINJAM("Dipinjam");

    private final String label;

    StatusBuku(String label) { this.label = label; }

    @Override
    public String toString() { return label; }
}
