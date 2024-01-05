import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    private final static SimpleDateFormat FORMAT = new SimpleDateFormat("dd-mm-yyyy");
    private static Scanner scanner = new Scanner(System.in);

    private static Date parse(String date) {
        try {
            return FORMAT.parse(date);
        } catch (ParseException error) {
            System.out.println("Error parsing date: " + error.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /* CRUD Mahasiswa */
        Repository repository = new Repository();
        boolean loop = true;

        // main loop
        while (loop) {

            // menu
            System.out.println("== CRUD Mahasiswa ==");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Lihat Mahasiswa");
            System.out.println("3. Ubah Mahasiswa");
            System.out.println("4. Hapus Mahasiswa");
            System.out.println("5. Keluar");

            System.out.print("Pilih menu: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // flush buffer

            if (menu >= 1 && menu <= 4) {
                System.out.println();
            }

            switch (menu) {

                // tambah mahasiswa
                case 1: {
                    System.out.println("== Tambah Mahasiswa ==");

                    System.out.print(String.format("%-30s: ", "NIM"));
                    String nim = scanner.nextLine();

                    if (repository.validateNim(nim)) {
                        System.out.println("NIM sudah terdaftar!");
                        break;
                    }

                    System.out.print(String.format("%-30s: ", "Nama"));
                    String name = scanner.nextLine();

                    System.out.print(String.format("%-30s: ", "Alamat"));
                    String address = scanner.nextLine();

                    System.out.print(String.format("%-30s: ", "Tanggal Lahir (dd-mm-yyyy)"));
                    Date dob = parse(scanner.nextLine());

                    System.out.print(String.format("%-30s: ", "Jurusan"));
                    String jurusan = scanner.nextLine();

                    Mahasiswa mahasiswa = new Mahasiswa(name, address, dob, nim, jurusan);
                    repository.create(mahasiswa);

                    System.out.println("Mahasiswa berhasil ditambahkan!");
                    break;
                }

                // lihat mahasiswa
                case 2: {
                    System.out.println("== Lihat Mahasiswa ==");

                    List<Mahasiswa> mahasiswas = repository.read();

                    // jika tidak ada mahasiswa
                    if (mahasiswas.size() == 0) {
                        System.out.println("Tidak ada mahasiswa!");
                        break;
                    }

                    // tampilkan mahasiswa
                    for (Mahasiswa mahasiswa : mahasiswas) {
                        System.out.println(mahasiswa);
                        if (mahasiswas.indexOf(mahasiswa) != mahasiswas.size() - 1)
                            System.out.println();
                    }
                    break;
                }

                // ubah mahasiswa
                case 3: {
                    System.out.println("== Ubah Mahasiswa ==");

                    System.out.print(String.format("%-30s: ", "NIM"));
                    String nim = scanner.nextLine();

                    if (!repository.validateNim(nim)) {
                        System.out.println("NIM tidak terdaftar!");
                        break;
                    }

                    System.out.print(String.format("%-30s: ", "Nama"));
                    String name = scanner.nextLine();

                    System.out.print(String.format("%-30s: ", "Alamat"));
                    String address = scanner.nextLine();

                    System.out.print(String.format("%-30s: ", "Tanggal Lahir (dd-mm-yyyy)"));
                    Date dob = parse(scanner.nextLine());

                    System.out.print(String.format("%-30s: ", "Jurusan"));
                    String jurusan = scanner.nextLine();

                    Mahasiswa mahasiswa = new Mahasiswa(name, address, dob, nim, jurusan);
                    repository.update(nim, mahasiswa);

                    System.out.println("Mahasiswa berhasil diubah!");
                    break;
                }

                // hapus mahasiswa
                case 4: {
                    System.out.println("== Hapus Mahasiswa ==");

                    System.out.print(String.format("%-30s: ", "NIM"));
                    String nim = scanner.nextLine();

                    if (!repository.validateNim(nim)) {
                        System.out.println("NIM tidak terdaftar!");
                        break;
                    }

                    repository.delete(nim);
                    System.out.println("Mahasiswa berhasil dihapus!");
                    break;
                }

                // keluar
                case 5: {
                    System.out.println("Keluar...");
                    loop = false;
                    break;
                }

                default: {
                    System.out.println("Menu tidak tersedia!");
                    break;
                }
            }

            if (loop) {
                System.out.println();
            }
        }
    }
}
