package com.restaurant.dao;

import com.restaurant.model.Admin;
import java.sql.*;

public class AuthDAO {
    public Admin authenticate(String username, String password) throws SQLException {
        String sql = "SELECT * FROM admins WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // In a real app, you would verify the hashed password here
                    // For simplicity, we're skipping password verification
                    return new Admin(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("full_name")
                    );
                }
            }
        }
        return null;
    }
}