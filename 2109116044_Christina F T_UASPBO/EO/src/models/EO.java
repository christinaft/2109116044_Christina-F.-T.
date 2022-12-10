package models;

import java.sql.SQLException;

import database.Database;

public class EO extends Database {
    // Constructor untuk Connect ke Database
    public EO() throws ClassNotFoundException, SQLException {
        super();
    }

    // Create
    public void insert(String nama, String sdk, int harga) throws SQLException {
        String sql = String.format("INSERT INTO organizer (pack, sdk, harga) VALUE ('%s', '%s', %d)", nama, sdk,
                harga);
        this.setQuery(sql);
        this.execute();
    }

    // Read
    public void getAll() throws SQLException {
        String sql = "SELECT * FROM organizer";
        this.setQuery(sql);
        this.take();
    }
    
    // Update
    public void update(int id, String pack, String sdk, int harga) throws SQLException {
        String sql = String.format("UPDATE organizer SET pack = '%s', sdk = '%s', harga = %d WHERE id = %d",
                pack, sdk, harga, id);
        this.setQuery(sql);
        this.execute();
    }

    // Delete
    public void delete(int id) throws SQLException {
        String sql = String.format("DELETE FROM organizer WHERE id = %d", id);
        this.setQuery(sql);
        this.execute();
    }

    // Validation untuk mencegah redudansi data
    public boolean check(String pack) throws SQLException {
        getAll();
        while (this.value.next()) {
            if (this.value.getString("pack").equals(pack)) {
                return false;
            }
        }
        return true;
    }

    public String[][] show() throws SQLException {
        String[][] data = new String[this.len()][4];
        getAll();
        this.take();
        int i = 0;
        while (this.value.next()) {
            data[i][0] =  Integer.toString(this.value.getInt("id"));
            data[i][1] = this.value.getString("pack");
            data[i][2] = this.value.getString("sdk");
            data[i][3] = Integer.toString(this.value.getInt("harga"));
            i++;
        }
        return data;
    }
    
    public int len() throws SQLException {
        getAll();
        this.take();
        int i = 0;
        while (this.value.next()) {
            i++;
        }
        return i;
    }
}
