package id.ac.uniska.pbo2.p07;

import java.time.Year;
import java.util.Collection;

/**
 * Validasi data yang dimasukkan melalui dialog buku.
 *
 * @author Akmal Adi Rizky - 2410010420
 */
public final class BukuValidator {
    private BukuValidator() { }

    public static String validasi(String kode, String judul, String penulis,
                                  String tahun, Collection<Buku> data, String kodeLama) {
        if (kode == null || kode.trim().isEmpty()) return "Kode buku wajib diisi.";
        if (!kode.trim().matches("B[1-9][0-9]*")) return "Format kode harus B1, B2, B3, dan seterusnya.";
        if (judul == null || judul.trim().isEmpty()) return "Judul buku wajib diisi.";
        if (penulis == null || penulis.trim().isEmpty()) return "Nama penulis wajib diisi.";
        if (tahun == null || tahun.trim().isEmpty()) return "Tahun terbit wajib diisi.";

        final int nilaiTahun;
        try {
            nilaiTahun = Integer.parseInt(tahun.trim());
        } catch (NumberFormatException ex) {
            return "Tahun terbit harus berupa angka.";
        }

        int tahunSekarang = Year.now().getValue();
        if (nilaiTahun < 1900 || nilaiTahun > tahunSekarang) {
            return "Tahun terbit harus antara 1900 dan " + tahunSekarang + ".";
        }

        String kodeBersih = kode.trim();
        for (Buku buku : data) {
            if (buku.getKode().equalsIgnoreCase(kodeBersih)
                    && (kodeLama == null || !buku.getKode().equalsIgnoreCase(kodeLama))) {
                return "Kode buku sudah digunakan.";
            }
        }
        return null;
    }
}
