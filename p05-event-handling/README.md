# Laporan Praktikum Rekayasa Perangkat Lunak Berorientasi Objek

Akmal Adi Rizky

2410010420

## Sesi Praktikum 04: Perancangan Antarmuka Grafis (GUI) Berbasis Manajemen Tata Letak Komponen

## 📌 Identitas Praktikum

* **Mata Kuliah:** Pemrograman Berorientasi Objek

* **Topik Pembahasan:** Strategi Penataan Komponen GUI Menggunakan Java Swing Layout Managers (`BorderLayout`, `BoxLayout`, `GridBagLayout`, `FlowLayout`, `GridLayout`)

* **Studi Kasus:** Sistem Registrasi Anggota Perpustakaan (`FormAnggotaPerpustakaan`)

* **Hari / Tanggal:** *(Isi tanggal praktikum)*

* Nama **Mahasiswa:** *(Isi nama mahasiswa)*

* **NIM:** *(Isi NIM)*

## 📑 Rangkuman Navigasi Dokumen

1. [Arsitektur & Spesifikasi Tata Letak Komponen](#1-arsitektur--spesifikasi-tata-letak-komponen)

2. [Analisis Konseptual & Teoretis](#2-analisis-konseptual--teoretis)

3. [Log Pengujian & Eksperimen Laboratorium](#3-log-pengujian--eksperimen-laboratorium)

4. [Tinjauan Sejawat (Peer Review)](#4-tinjauan-sejawat-peer-review)

5. [Integritas Akademik & Penggunaan AI](#5-integritas-akademik--penggunaan-ai)

6. [Daftar Periksa Berkas & Lampiran Proyek](#6-daftar-periksa-berkas--lampiran-proyek)

## 1. Arsitektur & Spesifikasi Tata Letak Komponen

### 1.1 Dekomposisi Panel dan Layout Manager

Struktur antarmuka `FormAnggotaPerpustakaan` dirancang secara bertingkat (*nested container*) guna menjaga stabilitas posisi elemen ketika jendela aplikasi diubah dimensinya:

| No | Kontainer / Panel | Nama Variabel | Jenis Layout Manager | Peran & Konfigurasi Teknis | 
| ----- | ----- | ----- | ----- | ----- | 
| 1 | Jendela Induk | `FormAnggotaPerpustakaan` (`JFrame`) | `BorderLayout` | Penampung primer root frame, membagi tata letak menjadi zona North, Center, dan South. | 
| 2 | Area Judul (Header) | `headerPanel` | `BoxLayout` (`Y_AXIS`) | Menumpuk teks tajuk formulir dan instruksi pendaftaran secara vertikal. | 
| 3 | Pembungkus Isi | `isiPanel` | `BorderLayout` | Menampung `formPanel` di zona `BorderLayout.NORTH` guna mengunci baris formulir di sisi atas. | 
| 4 | Panel Isian Data | `formPanel` | `GridBagLayout` | Mengatur penempatan label, sub-kategori, kontrol masukan, combo box, dan area teks secara presisi. | 
| 5 | Grup Jenis Anggota | `jenisAnggotaPanel` | `BoxLayout` (`Y_AXIS`) | Menampung tiga pilihan radio button yang terhubung ke satu `ButtonGroup`. | 
| 6 | Panel Aksi / Kontrol | `tombolPanel` | `FlowLayout` (`RIGHT`) | Mengatur posisi tombol \[Batal\] dan \[Daftar\] merapat ke sudut kanan bawah. | 

### 1.2 Hirarki Pohon Komponen (Component Tree)

Struktur hirarki komponen visual disesuaikan langsung dengan tata letak formulir pendaftaran:

```
FormAnggotaPerpustakaan (JFrame - BorderLayout)
│
├── [NORTH] headerPanel (BoxLayout: Y_AXIS)
│   ├── JLabel ("Form Anggota Perpustakaan")
│   └── JLabel ("Silakan isi data untuk mendaftar sebagai anggota.")
│
├── [CENTER] isiPanel (BorderLayout)
│   └── [NORTH] formPanel (GridBagLayout)
│       ├── [Baris 0] JLabel ("Data Anggota") ────────────────────────────────── (gridwidth = 2, font = bold)
│       ├── [Baris 1] JLabel ("Nama") ─────────────────────────> JTextField (weightx = 1.0, fill = HORIZONTAL)
│       ├── [Baris 2] JLabel ("NPM") ──────────────────────────> JTextField (weightx = 1.0, fill = HORIZONTAL)
│       ├── [Baris 3] JLabel ("Jenis Anggota") ────────────────> jenisAnggotaPanel (BoxLayout: Y_AXIS)
│       │                                                        ├── JRadioButton ("Mahasiswa") [Default Selected]
│       │                                                        ├── JRadioButton ("Dosen")
│       │                                                        └── JRadioButton ("Umum")
│       │                                                        └── (Tergabung dalam ButtonGroup)
│       ├── [Baris 4] JLabel ("Jurusan") ──────────────────────> JComboBox ("Sistem Informasi", ...)
│       ├── [Baris 5] JLabel ("Detail Kontak") ──────────────── (gridwidth = 2, font = bold)
│       ├── [Baris 6] JLabel ("Email") ────────────────────────> JTextField (weightx = 1.0, fill = HORIZONTAL)
│       ├── [Baris 7] JLabel ("Nomor HP") ─────────────────────> JTextField (weightx = 1.0, fill = HORIZONTAL)
│       ├── [Baris 8] JLabel ("Alamat") ───────────────────────> JScrollPane (JTextArea alamat) (fill = BOTH)
│       └── [Baris 9] JCheckBox ("Setuju dengan tata tertib...") (gridwidth = 2)
│
└── [SOUTH] tombolPanel (FlowLayout: RIGHT)
    ├── JButton ("Batal")
    └── JButton ("Daftar") [Aksi Utama]

```

> **Pedoman Konstruksi UI:**
>
> * Tambahkan margin dalam menggunakan `BorderFactory.createEmptyBorder(12, 16, 12, 16)` pada panel kontainer agar komponen tidak menempel rapat ke batas tepi jendela.
>
> * Komponen pembagi seksi (*Data Anggota*, *Detail Kontak*) dan *JCheckBox* menggunakan batasan `gridwidth = 2` serta `gridx = 0` sehingga dapat membentang penuh melintasi kolom label dan kolom input.

## 2. Analisis Konseptual & Teoretis

### 2.1 Mengapa Menghindari Null / Absolute Layout?

* **Kerapuhan Berbasis Resolusi:** Penempatan manual menggunakan koordinat