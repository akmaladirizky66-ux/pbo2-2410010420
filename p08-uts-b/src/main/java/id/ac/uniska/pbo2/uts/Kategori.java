/*
 * Nama : Akmal Adi Rizky
 * NPM  : 2410010420
 */
package id.ac.uniska.pbo2.uts;

/**
 * Kategori barang belanja.
 */
public enum Kategori {
    ALAT_TULIS("Alat Tulis"),
    MAKANAN("Makanan"),
    MINUMAN("Minuman"),
    LAINNYA("Lainnya");

    private final String label;

    Kategori(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Mencari kategori berdasarkan label yang tampil di combo box.
     * Jika tidak ditemukan, dikembalikan LAINNYA.
     */
    public static Kategori dariLabel(String label) {
        for (Kategori kategori : values()) {
            if (kategori.label.equals(label)) {
                return kategori;
            }
        }
        return LAINNYA;
    }

    @Override
    public String toString() {
        return label;
    }
}
