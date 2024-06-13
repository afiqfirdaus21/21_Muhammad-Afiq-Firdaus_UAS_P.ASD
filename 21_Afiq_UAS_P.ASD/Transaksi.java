public class Transaksi {
    int kodeTransaksi;
    String namaPeminjam;
    int lamaPinjam;
    double totalBiaya;
    BarangRental br;

    public Transaksi(int kodeTransaksi, String namaPeminjam, int lamaPinjam, BarangRental br, boolean isMember) {
        this.kodeTransaksi = kodeTransaksi;
        this.namaPeminjam = namaPeminjam;
        this.lamaPinjam = lamaPinjam;
        this.br = br;
        this.totalBiaya = calculateTotalBiaya(br.biayaSewa, lamaPinjam, isMember, br.jenisKendaraan);
    }

    private double calculateTotalBiaya(int biayaSewa, int lamaPinjam, boolean isMember, String jenisKendaraan) {
        double total = biayaSewa * lamaPinjam;
        if (isMember) {
            total -= 25000;
        }
        if (lamaPinjam >= 48 && lamaPinjam < 78) {
            total *= 0.90;
        } else if (lamaPinjam >= 78) {
            total *= 0.80;
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %d, %.2f", 
                             kodeTransaksi, br.noTNKB, br.namaKendaraan, namaPeminjam, lamaPinjam, totalBiaya);
    }
}
