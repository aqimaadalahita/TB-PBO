import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Repository implements IRepository {
    private final Database database = Database.getInstance();
    private Connection connection = null;

    public Repository() {
        connection = database.getConnection();
        initializeTable();
    }

    @Override
    public void initializeTable() {
        try {
            String query = String.join("\n", new String[] {
                    "CREATE TABLE IF NOT EXISTS mahasiswa (",
                    "id INT AUTO_INCREMENT PRIMARY KEY,",
                    "name VARCHAR(255) NOT NULL,",
                    "address VARCHAR(255) NOT NULL,",
                    "dob DATE NOT NULL,",
                    "nim VARCHAR(255) NOT NULL,",
                    "jurusan VARCHAR(255) NOT NULL",
                    ")"
            });

            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException error) {
            System.out.println("Error initializing table: " + error.getMessage());
        } catch (Exception error) {
            System.out.println("Error initializing table: " + error.getMessage());
        }
    }

    @Override
    public List<Mahasiswa> read() {
        try {
            String query = "SELECT * FROM mahasiswa";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            List<Mahasiswa> mahasiswaList = new ArrayList<Mahasiswa>();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Date dob = resultSet.getDate("dob");
                String nim = resultSet.getString("nim");
                String jurusan = resultSet.getString("jurusan");

                mahasiswaList.add(new Mahasiswa(name, address, dob, nim, jurusan));
            }

            return mahasiswaList;
        } catch (SQLException error) {
            System.out.println("Error loading table: " + error.getMessage());
        } catch (Exception error) {
            System.out.println("Error loading table: " + error.getMessage());
        }

        return null;
    }

    @Override
    public void create(Mahasiswa data) {
        try {
            String query = String.join("\n", new String[] {
                    "INSERT INTO mahasiswa (name, address, dob, nim, jurusan)",
                    "VALUES (?, ?, ?, ?, ?)"
            });

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, data.name);
            statement.setString(2, data.address);
            statement.setDate(3, new java.sql.Date(data.dob.getTime()));
            statement.setString(4, data.nim);
            statement.setString(5, data.jurusan);
            statement.execute();

        } catch (SQLException error) {
            System.out.println("Error creating data: " + error.getMessage());
        } catch (Exception error) {
            System.out.println("Error creating data: " + error.getMessage());
        }
    }

    @Override
    public void update(String nim, Mahasiswa data) {
        try {
            String query = String.join("\n", new String[] {
                    "UPDATE mahasiswa",
                    "SET name = ?, address = ?, dob = ?, nim = ?, jurusan = ?",
                    "WHERE nim = ?"
            });

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, data.name);
            statement.setString(2, data.address);
            statement.setDate(3, new java.sql.Date(data.dob.getTime()));
            statement.setString(4, data.nim);
            statement.setString(5, data.jurusan);
            statement.setString(6, nim);
            statement.execute();

        } catch (SQLException error) {
            System.out.println("Error updating data: " + error.getMessage());
        } catch (Exception error) {
            System.out.println("Error updating data: " + error.getMessage());
        }
    }

    @Override
    public void delete(String nim) {
        try {
            String query = String.join("\n", new String[] {
                    "DELETE FROM mahasiswa",
                    "WHERE nim = ?"
            });

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nim);
            statement.execute();

        } catch (SQLException error) {
            System.out.println("Error deleting data: " + error.getMessage());
        } catch (Exception error) {
            System.out.println("Error deleting data: " + error.getMessage());
        }
    }

    public boolean validateNim(String nim) {
        try {
            String query = String.join("\n", new String[] {
                    "SELECT * FROM mahasiswa",
                    "WHERE nim = ?"
            });

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nim);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException error) {
            System.out.println("Error validating nim: " + error.getMessage());
        } catch (Exception error) {
            System.out.println("Error validating nim: " + error.getMessage());
        }

        return false;
    }
}