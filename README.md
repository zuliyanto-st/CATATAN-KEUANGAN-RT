# Catatan Keuangan RT

Project Android sederhana yang berisi form:
- Pemasukan
- Pengeluaran
- Sisa
- Keterangan

Tombol **Simpan ke Excel** akan membuat file `catatan_keuangan.xlsx` di folder Download.

## Cara pakai (build online via GitHub Actions)

1. Buat repository baru di GitHub dan upload seluruh isi folder ini.
2. Di repository GitHub, pastikan **Actions** diaktifkan.
3. File workflow `.github/workflows/android.yml` sudah disertakan. Workflow ini menggunakan Gradle untuk build.
4. Jika kamu ingin menghasilkan **signed release APK**, ikuti instruksi di bawah untuk membuat keystore dan menambahkan secrets:
   - Buat keystore:
     ```
     keytool -genkeypair -v -keystore mykeystore.jks -keyalg RSA -keysize 2048 -validity 10000 -alias mykey
     ```
   - Di GitHub → Settings → Secrets, tambahkan:
     - `KEYSTORE_BASE64` (isi file keystore dalam base64)
     - `KEYSTORE_PASSWORD`
     - `KEY_ALIAS`
     - `KEY_PASSWORD`
5. Jalankan workflow dari tab Actions, pilih workflow `android.yml` → Run workflow atau push commit.
6. Setelah berhasil, artefak (APK) akan tersedia di job Actions -> Artifacts.

## Catatan penting
- Build di GitHub Actions memerlukan konfigurasi Android SDK. Workflow yang disertakan mencoba menginstal komponen SDK yang diperlukan, tetapi pada beberapa akun/runner mungkin perlu penyesuaian.
- Jika perlu, aku bisa bantu langkah demi langkah untuk menyesuaikan workflow agar kompatibel dengan repo-mu.
