# P04: Layout Manager dan Desain Form

Proyek praktikum Pemrograman Berbasis Objek 2 - Pertemuan 4 mengenai penggunaan Layout Manager dan Desain Form pada Java Swing menggunakan FlatLaf.

## Tahap 1: Sketsa Form
Berikut adalah sketsa rancangan form sebelum diimplementasikan ke dalam NetBeans GUI Builder:

![Sketsa Form Anggota Perpustakaan](p04-layout-manager/foto%20form/sketsa.jpeg)

---

## Tahap 2: Tabel Rancangan Layout Form Anggota Perpustakaan

| Bagian | Panel | Layout |
| :--- | :--- | :--- |
| Jendela | `JFrame` | `BorderLayout` |
| Header | `headerPanel` | `BoxLayout` (Y_AXIS) |
| Isi Form | `isiPanel` / `formPanel` | `BorderLayout` / `GridBagLayout` |
| Tombol | `tombolPanel` | `FlowLayout` (RIGHT) |

---

## Tabel Constraint Komponen `formPanel` (GridBagLayout)

| Komponen | Grid X | Grid Y | Grid Width | Fill | Anchor | Weight X | Insets |
| :--- | :---: | :---: | :---: | :--- | :--- | :---: | :--- |
| `dataAnggotaLabel` (Judul Bagian) | 0 | 0 | 2 | Horizontal | West | 1.0 | `0, 0, 0, 0` |
| `namaLabel` (Label Nama) | 0 | 1 | 1 | None | West | 0.0 | `0, 0, 0, 0` |
| `namaField` (Isian Nama) | 1 | 1 | 1 | Horizontal | Center | 0.0 | `2, 0, 2, 0` |
| `npmLabel` (Label NPM) | 0 | 2 | 1 | None | West | 0.0 | `0, 0, 0, 0` |
| `npmField` (Isian NPM) | 1 | 2 | 1 | Horizontal | Center | 0.0 | `2, 0, 2, 0` |
| `jenisAnggotaLabel` (Label Jenis Anggota) | 0 | 3 | 1 | None | West | 0.0 | `0, 0, 0, 0` |
| `mahasiswaRadioButton` (Mahasiswa) | 1 | 3 | 1 | Horizontal | Center | 0.0 | `0, 0, 0, 0` |
| `dosenRadioButton` (Dosen) | 1 | 4 | 1 | Horizontal | Center | 0.0 | `0, 0, 0, 0` |
| `umumRadioButton` (Umum) | 1 | 5 | 1 | Horizontal | Center | 0.0 | `0, 0, 0, 0` |
| `prodiLabel` (Label Jurusan) | 0 | 6 | 1 | None | West | 0.0 | `0, 0, 0, 0` |
| `prodiComboBox` (Pilihan Jurusan) | 1 | 6 | 1 | Horizontal | Center | 0.0 | `2, 0, 2, 0` |
| `kontakLabel` (Judul Detail Kontak) | 0 | 7 | 2 | Horizontal | West | 1.0 | `0, 0, 0, 0` |
| `emailLabel` (Label Email) | 0 | 8 | 1 | None | West | 0.0 | `0, 0, 0, 0` |
| `hpField` (Isian Nomor HP) | 1 | 8 | 1 | Horizontal | Center | 0.0 | `2, 0, 2, 0` |
| `hpLabel` (Label Nomor HP) | 0 | 9 | 1 | None | West | 0.0 | `0, 0, 0, 0` |
| `emailField` (Isian Email) | 1 | 9 | 1 | Horizontal | Center | 0.0 | `2, 0, 2, 0` |
| `alamatLabel` (Label Alamat) | 0 | 10 | 1 | None | West | 0.0 | `0, 0, 0, 0` |
| `jScrollPane1` / `alamatTextArea` (Alamat) | 1 | 10 | 1 | Horizontal | Center | 0.0 | `2, 0, 2, 0` |
| `setujuCheckBox` (Check Box Tata Tertib) | 0 | 11 | 1 | None | West | 0.0 | `0, 0, 0, 0` |