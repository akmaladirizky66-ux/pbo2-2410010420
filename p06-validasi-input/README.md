# Praktikum PBO 2 Pertemuan 6

## Validasi Input dan Kegunaan

### Identitas

  Keterangan    Detail
  ------------- ------------------------------
  Nama          Akmal Adi Rizky
  NPM           2410010420
  Mata Kuliah   Pemrograman Berbasis Objek 2
  Pertemuan     6
  Materi        Validasi Input dan Kegunaan
  Peninjau      Gusti Muhammad Abdul Ghani
  Proyek        `p06-validasi-input`

------------------------------------------------------------------------

## 1. Peer Review

**Nama Peninjau:** Gusti Muhammad Abdul Ghani\
**Nama Pemilik Form:** Akmal Adi Rizky

Peer review dilakukan untuk mengevaluasi kegunaan dan validasi pada
`FormAnggotaPerpustakaan` berdasarkan heuristik usability Nielsen.

### Temuan dan Perbaikan

  ---------------------------------------------------------------------------------
  No.               Temuan dari       Perbaikan yang    Commit
                    Peninjau          Dilakukan         
  ----------------- ----------------- ----------------- ---------------------------
  1                 Input NPM perlu   Menambahkan       `P06: validasi input`
                    dicegah agar      validasi          
                    tidak menerima    `keyTyped` agar   
                    karakter selain   NPM hanya         
                    angka dan tidak   menerima angka    
                    melebihi 10       dan maksimal 10   
                    digit.            digit.            

  2                 Pesan kesalahan   Menambahkan label `P06: feedback validasi`
                    perlu ditampilkan pesan kesalahan   
                    langsung di bawah dan garis merah   
                    setiap isian.     pada input yang   
                                      tidak valid.      

  3                 Pengguna perlu    Menambahkan       `P06: konfirmasi batal`
                    mendapat          dialog konfirmasi 
                    konfirmasi        pada tombol       
                    sebelum data yang Batal.            
                    sudah diisi                         
                    dihapus.                            

  4                 Pengguna perlu    Menambahkan       `P06: ringkasan validasi`
                    mengetahui jumlah ringkasan         
                    isian yang salah. kesalahan dan     
                                      fokus otomatis ke 
                                      isian pertama     
                                      yang salah.       
  ---------------------------------------------------------------------------------

**Catatan:** Tabel di atas merupakan draf dokumentasi berdasarkan aspek
perbaikan pada form. Cocokkan kembali temuan, saran, skor, dan pesan
commit dengan lembar peer review serta riwayat Git yang sebenarnya
sebelum dikumpulkan.

### Lembar Peer Review

-   Nama peninjau: Gusti Muhammad Abdul Ghani
-   Nama pemilik form: Akmal Adi Rizky
-   Foto atau pindaian lembar peer review: belum dilampirkan.

------------------------------------------------------------------------

## 2. Dokumentasi Pengujian

### A. Screenshot Form dengan Pesan Kesalahan

Screenshot dilewati dan belum dilampirkan.

### B. Screenshot Navigator

Screenshot jendela Navigator yang memperlihatkan label pesan dilewati
dan belum dilampirkan.

------------------------------------------------------------------------

## 3. Jawaban Eksperimen Praktikum 4

### 1. Ketik huruf pada isian NPM. Apa yang terjadi dan baris mana di `npmFieldKeyTyped` yang menyebabkannya?

**Jawaban:**

Ketika pengguna mengetik huruf pada isian NPM, karakter tersebut tidak
akan dimasukkan ke dalam kolom. Hal ini terjadi karena pemeriksaan
`Character.isDigit(evt.getKeyChar())`. Jika karakter bukan angka,
perintah `evt.consume()` membatalkan input tersebut.

Selain itu, terdapat pemeriksaan jumlah karakter agar NPM tidak melebihi
10 digit.

### 2. Isi email dengan `siti@`, lalu tekan Tab. Kapan pesan kesalahan muncul?

**Jawaban:**

Pesan kesalahan muncul ketika pengguna berpindah dari kolom email ke
komponen berikutnya menggunakan tombol Tab. Perpindahan fokus memicu
event `focusLost`, kemudian method `periksaEmail()` dijalankan untuk
memeriksa format email. Karena format `siti@` tidak valid, sistem
menampilkan pesan kesalahan pada label di bawah kolom email.

### 3. Klik isian Nama tanpa mengetik, lalu tekan Tab. Mengapa tidak ada pesan kesalahan?

**Jawaban:**

Pada event `focusLost` terdapat pemeriksaan `isBlank()`. Jika kolom nama
masih kosong, validasi tidak langsung dijalankan. Hal ini bertujuan agar
pengguna tidak mendapatkan pesan kesalahan sebelum mencoba mengisi data.
Isian kosong tetap akan diperiksa ketika tombol Daftar diklik.

### 4. Centang persetujuan, lalu klik Daftar dengan form kosong. Isian mana yang mendapat fokus?

**Jawaban:**

Isian pertama yang mendapat fokus adalah kolom NPM, jika NPM merupakan
komponen pertama yang diperiksa dan dinyatakan tidak valid. Sistem
memeriksa seluruh input melalui method `daftar()`, mengumpulkan komponen
yang tidak valid, lalu menggunakan `salah.get(0).requestFocusInWindow()`
untuk mengarahkan fokus ke kesalahan pertama.

### 5. Tekan tombol Alt bersama huruf N. Isian mana yang mendapat fokus, dan properti apa yang mengaturnya?

**Jawaban:**

Fokus berpindah ke kolom Nama apabila mnemonic N dipasang pada label
Nama dan label tersebut dihubungkan ke `namaField` melalui properti
`labelFor`. Pengaturannya menggunakan properti `displayedMnemonic` pada
label dan `labelFor` untuk menghubungkan label dengan komponen isian.

### 6. Isi beberapa isian, lalu klik Batal dan pilih No. Apakah data tetap ada?

**Jawaban:**

Ya, data yang sudah diisi tetap ada. Ketika pengguna memilih No pada
dialog konfirmasi, proses pembatalan dihentikan menggunakan `return`.
Dengan demikian, method `kosongkan()` tidak dijalankan dan data tetap
berada di dalam form.

------------------------------------------------------------------------

## 4. Pertanyaan Refleksi

### 1. Mengapa aturan validasi dipisahkan ke kelas Validator, bukan ditulis langsung di event handler?

**Jawaban:**

Agar kode program lebih terstruktur, mudah dibaca, dan mudah dipelihara.
Dengan memisahkan validasi ke kelas Validator, aturan pemeriksaan dapat
digunakan kembali oleh berbagai komponen tanpa harus menuliskan logika
yang sama berulang kali di setiap event handler.

### 2. Mengapa isian kosong tidak langsung ditandai merah saat pengguna hanya melewatinya?

**Jawaban:**

Karena pengguna mungkin belum sempat mengisi kolom tersebut. Jika setiap
kolom kosong langsung ditandai merah, hal tersebut dapat mengganggu
kenyamanan pengguna. Oleh karena itu, validasi saat `focusLost` hanya
dilakukan apabila kolom sudah berisi data, sedangkan pemeriksaan isian
wajib dilakukan ketika tombol Daftar ditekan.

### 3. Sebutkan tiga heuristik Nielsen yang diterapkan pada form Anda beserta buktinya.

**Jawaban:**

1.  **Visibility of System Status (Visibilitas Status Sistem):** Sistem
    memberikan informasi kesalahan melalui label pesan, garis merah, dan
    ringkasan jumlah isian yang perlu diperbaiki.
2.  **Error Prevention (Pencegahan Kesalahan):** Kolom NPM dan nomor HP
    dibatasi agar hanya menerima angka. Selain itu, NPM dibatasi
    maksimal 10 digit.
3.  **User Control and Freedom (Kendali dan Kebebasan Pengguna):**
    Sistem menyediakan tombol Batal dengan konfirmasi sebelum menghapus
    data sehingga pengguna dapat membatalkan tindakan tersebut.

### 4. Mengapa pesan kesalahan tidak boleh hanya berupa warna merah?

**Jawaban:**

Karena tidak semua pengguna dapat membedakan informasi hanya berdasarkan
warna. Pengguna yang memiliki gangguan penglihatan atau kesulitan
membedakan warna mungkin tidak memahami kesalahan tersebut. Oleh karena
itu, warna merah harus disertai pesan teks yang menjelaskan kesalahan
dan cara memperbaikinya.

### 5. Temuan peer review mana yang paling bermanfaat bagi Anda dan mengapa?

**Jawaban:**

Temuan mengenai pencegahan input karakter selain angka pada kolom NPM
merupakan salah satu perbaikan yang paling bermanfaat. Dengan
menambahkan event `keyTyped`, kesalahan dapat dicegah sejak awal
sehingga data yang dimasukkan menjadi lebih konsisten.

------------------------------------------------------------------------

## 5. Deklarasi Penggunaan AI

Dalam pengerjaan Praktikum PBO 2 Pertemuan 6 ini, saya menggunakan
bantuan Artificial Intelligence (AI) dengan rincian sebagai berikut:

  -----------------------------------------------------------------------
  Alat                                Kegunaan
  ----------------------------------- -----------------------------------
  ChatGPT                             Membantu memahami materi validasi
                                      input, menyusun logika validasi,
                                      memberikan arahan perbaikan kode
                                      Java, membantu pengujian, serta
                                      menyusun dokumentasi README.md.

  -----------------------------------------------------------------------

Kode dan saran yang digunakan tetap diperiksa serta disesuaikan dengan
struktur proyek dan ketentuan modul praktikum.

------------------------------------------------------------------------

## 6. Kesimpulan

Berdasarkan praktikum yang telah dikerjakan, dapat disimpulkan bahwa
validasi input merupakan bagian penting dalam pengembangan aplikasi
berbasis GUI. Validasi membantu memastikan data yang dimasukkan pengguna
sesuai dengan aturan yang telah ditentukan.

Penerapan event handler, kelas Validator, pesan kesalahan, mnemonic,
tooltip, serta konfirmasi tindakan dapat meningkatkan kualitas dan
kemudahan penggunaan aplikasi `FormAnggotaPerpustakaan`.

Melalui praktikum ini, saya mempelajari penerapan validasi input
sekaligus pentingnya aspek usability dalam pengembangan aplikasi Java
Swing.

------------------------------------------------------------------------

## 7. Catatan Sebelum Pengumpulan

-   [ ] Pastikan foto atau pindaian lembar peer review dari Gusti
    Muhammad Abdul Ghani dilampirkan.
-   [ ] Cocokkan tabel temuan dan perbaikan dengan hasil peer review
    yang sebenarnya.
-   [ ] Ganti pesan commit pada tabel dengan commit aktual dari Git.
-   [ ] Tambahkan screenshot form yang menampilkan pesan kesalahan.
-   [ ] Tambahkan screenshot Navigator yang memperlihatkan label pesan.
-   [ ] Pastikan repository memuat proyek `p06-validasi-input`, serta
    file `.java` dan `.form`.
