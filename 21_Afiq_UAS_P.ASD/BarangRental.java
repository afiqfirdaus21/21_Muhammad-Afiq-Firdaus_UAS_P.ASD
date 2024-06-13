public class BarangRental {
    String noTNKB;
    String namaKendaraan;
    String jenisKendaraan;
    int tahun;
    int biayaSewa;

    public BarangRental(String noTNKB, String namaKendaraan, String jenisKendaraan, int tahun, int biayaSewa2) {
        this.noTNKB = noTNKB;
        this.namaKendaraan = namaKendaraan;
        this.jenisKendaraan = jenisKendaraan;
        this.tahun = tahun;
        this.biayaSewa = biayaSewa2;
    }

    public BarangRental(String noTNKB2, String namaKendaraan2, String jenisKendaraan2, int tahun2, double biayaSewa2) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return String.format("%s dengan nomor registrasi %s, %s tahun %d dengan biaya sewa per jam %d", 
                             namaKendaraan, noTNKB, jenisKendaraan, tahun, biayaSewa);
    }
}
