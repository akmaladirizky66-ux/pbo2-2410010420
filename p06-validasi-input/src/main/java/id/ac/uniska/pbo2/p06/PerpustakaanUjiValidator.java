/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.uniska.pbo2.p06;

/**
 *
 * @author Akmal
 */
public class PerpustakaanUjiValidator {
     private static int gagal = 0;

    private static void cek(String nama, String hasil, boolean harusValid) {
        boolean valid = (hasil == null);

        if (valid != harusValid) {
            gagal++;
        }

        String status = (valid == harusValid) ? "SESUAI " : "SALAH ";
        System.out.println(status + nama + ": "
                + (valid ? "valid" : hasil));
    }

    public static void main(String[] args) {

        cek("NPM kosong", Validator.npm(""), false);
        cek("NPM kurang digit", Validator.npm("12345"), false);
        cek("NPM benar", Validator.npm("2410010021"), true);

        cek("Nama kosong", Validator.nama(""), false);
        cek("Nama terlalu pendek", Validator.nama("Al"), false);
        cek("Nama mengandung angka", Validator.nama("Akmal123"), false);
        cek("Nama benar", Validator.nama("Akmal Adi Rizky"), true);

        cek("Email salah", Validator.email("akmal@"), false);
        cek("Email benar", Validator.email("akmal@gmail.com"), true);

        cek("HP terlalu pendek", Validator.nomorHp("0812"), false);
        cek("HP benar", Validator.nomorHp("081234567890"), true);

        System.out.println();

        if (gagal == 0) {
            System.out.println("Semua aturan bekerja sesuai harapan");
        } else {
            System.out.println(gagal + " aturan belum sesuai");
        }
    }
}
