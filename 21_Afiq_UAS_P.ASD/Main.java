import java.util.Scanner;

public class Main {
    private static DoubleLinkedList<BarangRental> barangList = new DoubleLinkedList<>();
    private static DoubleLinkedList<Transaksi> transaksiList = new DoubleLinkedList<>();
    private static int kodeTransaksiCounter = 1;
    private static double totalPendapatan = 0;

    public static void main(String[] args) {
        isiDataBarang();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            tampilkanMenu();
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tampilkanDaftarKendaraan();
                    tambahBarangRental(scanner);
                    break;

                case 2:
                    lakukanPeminjaman(scanner);
                    break;
                case 3:
                    tampilkanRiwayatTransaksi(scanner);
                    break;
                case 4:
                    urutkanTransaksi();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);

        scanner.close();
    }

    private static void isiDataBarang() {
        barangList.add(new BarangRental("4567 VV", "Honda Beat", "Sepeda Motor", 2017, 10000));
        barangList.add(new BarangRental("IN 4511 WS", "Honda Vario", "Sepeda Motor", 2018, 12000));
        barangList.add(new BarangRental("IN 1453 AA", "Toyota Yaris", "Mobil", 2022, 130000));
        barangList.add(new BarangRental("AB 4321 A", "Toyota Innova", "Mobil", 2019, 60000));
        barangList.add(new BarangRental("IB 1234 AG", "Toyota Avanza", "Mobil", 2021, 250000));
    }

    private static void tampilkanMenu() {
        System.out.println("\nMenu");
        System.out.println("1. Daftar Kendaraan");
        System.out.println("2. Peminjaman");
        System.out.println("3. Tampilkan Riwayat Transaksi");
        System.out.println("4. Urutkan Transaksi");
        System.out.println("5. Keluar");
        System.out.print("Pilih (1-5): ");
    }

    private static void tambahBarangRental(Scanner scanner) {
        System.out.println("\nMasukkan Data Kendaraan Baru");
        System.out.print("Nomor TNKB: ");
        String noTNKB = scanner.nextLine();
        System.out.print("Nama Kendaraan: ");
        String namaKendaraan = scanner.nextLine();
        System.out.print("Jenis Kendaraan: ");
        String jenisKendaraan = scanner.nextLine();
        System.out.print("Tahun: ");
        int tahun = scanner.nextInt();
        System.out.print("Biaya Sewa per Jam: ");
        double biayaSewa = scanner.nextDouble();
    
        BarangRental newBarang = new BarangRental(noTNKB, namaKendaraan, jenisKendaraan, tahun, biayaSewa);
        barangList.add(newBarang);
        System.out.println("Kendaraan baru berhasil ditambahkan.");
    }
    

    private static void tampilkanDaftarKendaraan() {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++");
        System.out.println("Daftar Kendaraan Rental Serba Serbi");
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| No TNKB   | Nama Kendaraan     | Jenis        | Tahun | Biaya Sewa per Jam |");
        System.out.println("---------------------------------------------------------------------------");
        Node<BarangRental> current = barangList.getHead();
        while (current != null) {
            System.out.printf("| %-10s| %-18s| %-13s| %-6d| Rp %-17d|\n",
                    current.data.noTNKB, current.data.namaKendaraan,
                    current.data.jenisKendaraan, current.data.tahun, current.data.biayaSewa);
            current = current.next;
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    private static void lakukanPeminjaman(Scanner scanner) {
        System.out.println("\n-----------------------------------");
        System.out.println("Masukkan Data Peminjaman");
        System.out.println("-----------------------------------");
        System.out.print("Masukkan nama peminjam: ");
        String namaPeminjam = scanner.nextLine();
        System.out.print("Masukkan nomor TNKB: ");
        String noTNKB = scanner.nextLine();
        System.out.print("Masukkan lama pinjam (dalam jam): ");
        int lamaPinjam = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Apakah member (ya/tidak): ");
        boolean isMember = scanner.nextLine().equalsIgnoreCase("ya");

        BarangRental barangRental = cariBarangRental(noTNKB);
        if (barangRental != null && !isBarangDipinjam(noTNKB)) {
            Transaksi transaksi = new Transaksi(kodeTransaksiCounter++, namaPeminjam, lamaPinjam, barangRental,
                    isMember);
            transaksiList.add(transaksi);
            totalPendapatan += transaksi.totalBiaya;
            System.out.println("Transaksi berhasil ditambahkan.");
        } else {
            System.out.println("Tidak bisa diproses, kendaraan sudah dipinjam orang lain.");
        }
    }

    private static BarangRental cariBarangRental(String noTNKB) {
        Node<BarangRental> current = barangList.getHead();
        while (current != null) {
            if (current.data.noTNKB.equals(noTNKB)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    private static boolean isBarangDipinjam(String noTNKB) {
        Node<Transaksi> current = transaksiList.getHead();
        while (current != null) {
            if (current.data.br.noTNKB.equals(noTNKB)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private static void tampilkanRiwayatTransaksi(Scanner scanner) {
        System.out.println("\n-----------------------------------");
        System.out.println("Tampilkan Riwayat Transaksi");
        System.out.println("-----------------------------------");
        System.out.print("Masukkan nomor TNKB kendaraan: ");
        String noTNKB = scanner.nextLine();

        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Daftar Transaksi Peminjaman Rental Serba Serbi");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(
                "| Kode  | No TNKB   | Nama Kendaraan     | Nama Peminjam         | Lama Pinjam | Total Biaya      |");
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
        Node<Transaksi> current = transaksiList.getHead();
        while (current != null) {
            if (current.data.br.noTNKB.equals(noTNKB)) {
                System.out.printf("| %-5d | %-10s| %-18s| %-21s| %-11d| Rp %-15.2f|\n",
                        current.data.kodeTransaksi, current.data.br.noTNKB,
                        current.data.br.namaKendaraan, current.data.namaPeminjam,
                        current.data.lamaPinjam, current.data.totalBiaya);
            }
            current = current.next;
        }
        System.out.println(
                "-------------------------------------------------------------------------------------------------");
    }

    private static void urutkanTransaksi() {
        System.out.println("\nSorting transactions is not implemented yet.");
    }
}