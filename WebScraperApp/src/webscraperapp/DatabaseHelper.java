package webscraperapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Tshepang Chou
 */
public class DatabaseHelper 
{  
    //private static final String DB_URL = "jdbc:mysql://localhost:3306/scraped_data_db";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/scraped_data_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DB_USERNAME = "root";  // Replace with your username
    private static final String DB_PASSWORD = "123456";  // Replace with your password

    
    // Method to connect to the database
    public static Connection connect()
    {
        try
        {
            // Explicitly load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch (ClassNotFoundException e) 
        {
        System.out.println("JDBC Driver not found: " + e.getMessage());
        } 
        catch (SQLException e) {
        System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return null; // Return null if connection fails
    }
    
    // Method to store the data into the database
    public static void storeData(String url, String title, String content) 
    {
        String sql = "INSERT INTO scraped_info (website_url, title, content) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             var statement = conn.prepareStatement(sql)) 
        {
            statement.setString(1, url);
            statement.setString(2, title);
            statement.setString(3, content);
            statement.executeUpdate();
            System.out.println("Data saved successfully for URL: " + url);
        } 
        catch (SQLException e) 
        {
            System.out.println("Error storing data: " + e.getMessage());
        }
    }
    
    // Method to retrieve data from the database
    public static void retrieveData()
    {
        String sql = "SELECT * FROM scraped_info";
        try (Connection conn = connect(); Statement stmt = conn.createStatement())
        {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                String url = rs.getString("website_url");
                String title = rs.getString("title");
                String content = rs.getString("content");
                
                // Print the retrieved data (or you could store it in a list or process it further)
                System.out.println("Website URL: " + url);
                System.out.println("Title: " + title);
                System.out.println("Content: " + content);
                System.out.println("------------");
            }
        }
        catch (SQLException e) 
        {
            System.out.println("Error retrieving data: " + e.getMessage());
        }
    }
}