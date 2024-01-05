import java.util.Date;

public abstract class Person {
    public Date dob;
    public String name;
    public String address;

    public Person(String name, String address, Date dob) {
        this.name = name;
        this.address = address;
        this.dob = dob;
    }

    public int getAge() {
        Date now = new Date();
        long diff = now.getTime() - dob.getTime();
        return (int) (diff / (24 * 60 * 60 * 1000 * 365.25)); // in years
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%-30s: %s\n", "Nama", name));
        builder.append(String.format("%-30s: %s\n", "Alamat", address));
        builder.append(String.format("%-30s: %s (%d tahun)", "Tanggal Lahir", dob, getAge()));

        return builder.toString();
    }
}