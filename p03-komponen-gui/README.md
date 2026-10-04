# PENGGUNAAN ARTIFICIAL INTELLIGENCE (AI)

## A. Identitas AI

**Nama AI:** Google Gemini

Dalam proses pembuatan aplikasi `FormTiketTravel`, Google Gemini digunakan sebagai alat bantu untuk mencari referensi, memahami konsep Java Swing, serta membantu menyelesaikan kendala yang ditemukan selama proses pemrograman. AI digunakan sebagai pendukung pembelajaran, sedangkan implementasi program tetap disesuaikan dengan project yang dibuat di NetBeans.

---

## B. Pemanfaatan AI dalam Pembuatan Aplikasi

### 1. Membuat Proses Pemesanan Tiket

**Prompt yang digunakan:**

> "Saya sedang membuat form pemesanan tiket travel menggunakan Java Swing. Pada form terdapat input nama, nomor HP, tujuan, kelas tiket, fasilitas tambahan, dan catatan. Saya ingin ketika tombol Pesan ditekan, semua data tersebut dikumpulkan kemudian ditampilkan dalam JOptionPane. Berikan contoh event atau method yang sesuai."

**Bantuan yang diberikan Gemini:**

Gemini menjelaskan cara mengambil nilai dari berbagai komponen pada form berdasarkan jenis komponennya. Untuk `JTextField` dan `JTextArea`, nilai diambil menggunakan `getText()`. Untuk `JComboBox`, digunakan `getSelectedItem()`, sedangkan pilihan pada `JRadioButton` dan `JCheckBox` dapat diperiksa menggunakan `isSelected()`.

Contoh implementasi yang kemudian disesuaikan dengan project:

```java
private void prosesPemesanan() {
    String nama = namaPemesanField.getText();
    String hp = nomorHpField.getText();
    String tujuan = (String) kotaTujuanCombo.getSelectedItem();

    String kelas = "";

    if (ekonomiRadio.isSelected()) {
        kelas = "Ekonomi";
    } else if (bisnisRadio.isSelected()) {
        kelas = "Bisnis";
    } else if (eksekutifRadio.isSelected()) {
        kelas = "Eksekutif";
    }

    String fasilitas = "";

    if (bagasiCheck.isSelected()) {
        fasilitas += "Bagasi, ";
    }

    if (makanCheck.isSelected()) {
        fasilitas += "Makan, ";
    }

    if (asuransiCheck.isSelected()) {
        fasilitas += "Asuransi";
    }

    if (fasilitas.isEmpty()) {
        fasilitas = "Tidak ada fasilitas";
    }

    String hasil = "RINGKASAN PEMESANAN\n"
            + "----------------------\n"
            + "Nama      : " + nama
            + "\nNo. HP    : " + hp
            + "\nTujuan    : " + tujuan
            + "\nKelas     : " + kelas
            + "\nFasilitas : " + fasilitas
            + "\nCatatan   : " + catatanArea.getText();

    JOptionPane.showMessageDialog(
            this,
            hasil,
            "Detail Pemesanan",
            JOptionPane.INFORMATION_MESSAGE
    );
}
```

Kode tersebut membantu menggabungkan seluruh data yang diinput pengguna menjadi satu informasi pemesanan yang kemudian ditampilkan dalam bentuk pop-up.

---

### 2. Mengimplementasikan Dark Mode

**Prompt yang digunakan:**

> "Saya menggunakan FlatLaf pada aplikasi Java Swing dan memiliki JToggleButton untuk mengubah tampilan aplikasi. Bagaimana cara membuat tombol tersebut agar dapat mengganti tampilan dari FlatLightLaf ke FlatDarkLaf dan sebaliknya?"

**Bantuan yang diberikan Gemini:**

Gemini memberikan contoh penggunaan kondisi `isSelected()` pada `JToggleButton`. Status tombol digunakan untuk menentukan tema yang akan diterapkan.

Contoh kode:

```java
private void ubahTema() {
    if (temaToggle.isSelected()) {
        FlatDarkLaf.setup();
        temaToggle.setText("Light");
    } else {
        FlatLightLaf.setup();
        temaToggle.setText("Dark");
    }

    FlatLaf.updateUI();
}
```

Kemudian method tersebut dipanggil ketika tombol toggle ditekan:

```java
private void temaToggleActionPerformed(
        java.awt.event.ActionEvent evt) {

    ubahTema();
}
```

Dengan cara tersebut, pengguna dapat berpindah antara tampilan terang dan gelap tanpa harus menjalankan ulang aplikasi.

---

### 3. Membantu Menemukan Kesalahan Nama Komponen

**Prompt yang digunakan:**

> "Pada project Java Swing saya terdapat error karena nama variabel komponen yang digunakan pada source code tidak dikenali. Saya menggunakan NetBeans GUI Builder. Bagaimana cara mengecek dan mengganti nama variabel komponen tanpa mengubah kode otomatis NetBeans?"

**Bantuan yang diberikan Gemini:**

Gemini menjelaskan bahwa error dapat terjadi ketika nama variabel yang digunakan pada source code tidak sama dengan nama variabel komponen yang dibuat melalui GUI Builder.

Pengecekan dilakukan melalui **Design View** pada NetBeans. Komponen yang bermasalah dipilih kemudian nama variabelnya diperiksa pada bagian properties. Jika diperlukan, nama variabel dapat diubah menggunakan fitur **Change Variable Name**.

Sebagai contoh, apabila source code menggunakan:

```java
temaToggle.isSelected();
```

maka komponen `JToggleButton` pada GUI Builder harus memiliki nama variabel `temaToggle`.

Gemini juga memberikan penjelasan bahwa bagian `initComponents()` sebaiknya tidak diedit secara manual karena bagian tersebut dikelola oleh NetBeans GUI Builder.

---

## C. Pengaruh AI terhadap Proses Pengerjaan

Penggunaan Google Gemini memberikan bantuan terutama dalam memahami fungsi komponen Java Swing dan cara menghubungkan komponen tersebut dengan event yang ada pada aplikasi. Beberapa konsep yang lebih mudah dipahami melalui bantuan AI antara lain `getText()`, `getSelectedItem()`, `isSelected()`, `JOptionPane`, event handler, serta penggunaan FlatLaf.

Selain membantu membuat contoh kode, Gemini juga digunakan untuk memahami penyebab error yang muncul ketika program dijalankan. Jawaban dari AI tidak langsung diterapkan seluruhnya, tetapi diperiksa terlebih dahulu dan disesuaikan dengan struktur project serta nama komponen yang digunakan.

---

## D. Kesimpulan Penggunaan AI

Google Gemini digunakan sebagai **asisten dalam proses pengembangan dan pembelajaran**, bukan sebagai pengganti proses pembuatan aplikasi. AI membantu memberikan gambaran mengenai cara kerja kode dan memberikan alternatif penyelesaian ketika ditemukan kendala.

Implementasi akhir tetap dilakukan dengan menyesuaikan kebutuhan aplikasi `FormTiketTravel`. Layout dan komponen form dibuat menggunakan **NetBeans GUI Builder**, sedangkan kode yang diperoleh dari AI diperiksa dan dimodifikasi agar sesuai dengan struktur aplikasi yang dibuat.