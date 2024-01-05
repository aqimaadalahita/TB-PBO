import java.util.List;

interface IRepository {
    public void initializeTable();

    public List<Mahasiswa> read();

    public void create(Mahasiswa data);

    public void update(String nim, Mahasiswa data);

    public void delete(String nim);
}