package IntroDB;

import java.sql.*;

public class IntroDatabase {
    
    private static final String DB_URL = "jdbc:sqlite:intros.db";

    public IntroDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = """
                CREATE TABLE IF NOT EXISTS user_intros (
                    user_id TEXT PRIMARY KEY,
                    name TEXT,
                    hobbies TEXT,
                    intoRightNow TEXT,
                    favFood TEXT,
                    funFact TEXT,
                    birthday TEXT
                )
            """;
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveIntro(String userId, String name, String hobbies, String intoRightNow,
                          String favFood, String funFact, String birthday) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT OR REPLACE INTO user_intros VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, name);
            ps.setString(3, hobbies);
            ps.setString(4, intoRightNow);
            ps.setString(5, favFood);
            ps.setString(6, funFact);
            ps.setString(7, birthday);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getIntro(String userId) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            String sql = "SELECT * FROM user_intros WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
