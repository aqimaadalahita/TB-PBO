import java.util.Date;

public class Mahasiswa extends Person {
    public String nim;
    public String jurusan;

    public Mahasiswa(String name, String address, Date dob, String nim, String jurusan) {
        super(name, address, dob);
        this.nim = nim;
        this.jurusan = jurusan;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%-30s: %s\n", "NIM", nim));
        builder.append(String.format("%-30s: %s\n", "Jurusan", jurusan));
        builder.append(super.toString());

        return builder.toString();
    }
}