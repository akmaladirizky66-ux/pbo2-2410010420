package id.ac.uniska.pbo2.p07;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table model untuk menampilkan data katalog buku.
 *
 * @author Akmal adi Rizky - 2410010420
 */
public class BukuTableModel extends AbstractTableModel {
    private final String[] kolom = {"Kode", "Judul Buku", "Penulis", "Tahun Terbit", "Status"};
    private final List<Buku> data = new ArrayList<>();

    @Override public int getRowCount() { return data.size(); }
    @Override public int getColumnCount() { return kolom.length; }
    @Override public String getColumnName(int column) { return kolom[column]; }
    @Override public Object getValueAt(int row, int column) {
        Buku buku = data.get(row);
        return switch (column) {
            case 0 -> buku.getKode();
            case 1 -> buku.getJudul();
            case 2 -> buku.getPenulis();
            case 3 -> buku.getTahunTerbit();
            case 4 -> buku.getStatus().toString();
            default -> null;
        };
    }

    public void tambah(Buku buku) {
        int row = data.size();
        data.add(buku);
        fireTableRowsInserted(row, row);
    }

    public Buku getBuku(int row) { return data.get(row); }

    public void ubah(int row, Buku buku) {
        data.set(row, buku);
        fireTableRowsUpdated(row, row);
    }

    public void hapus(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public List<Buku> getSemuaBuku() { return new ArrayList<>(data); }
}
