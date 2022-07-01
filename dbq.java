import java.sql.Connection;  
import java.sql.DatabaseMetaData;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.sql.PreparedStatement;   
import java.sql.ResultSet; 


public class DBQuery {
	public static void createNewDatabase(String fileName) {  

        String url = "jdbc:sqlite:C:/sqlite/" + fileName;  

        try {  
            Connection conn = DriverManager.getConnection(url);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
                System.out.println("The driver name is " + meta.getDriverName());  
                System.out.println("A new database has been created.");  
            }  

        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  

	public static void createNewTable() {  
        // SQLite connection string  
        String url = "jdbc:sqlite:C://sqlite/MuleSoft.db";  

        // SQL statement for creating a new table  
        String sql =  "CREATE TABLE IF NOT EXISTS Movies (\n"  
                + " id integer PRIMARY KEY,\n"  
                + " movie_name text NOT NULL,\n"
                + " actor text NOT NULL,\n"
                + " actress text NOT NULL,\n"
                + " year integer NOT NULL,\n"
                + " director text NOT NULL\n"
                + ");"; 

        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    } 

	public static void insert(int id, String movie_name, String actor, String actress, int year, String director) {  

		String url = "jdbc:sqlite:C://sqlite/MuleSoft.db";

		String sql = "INSERT INTO Movies(id, movie_name, actor, actress, year, director) VALUES(?,?,?,?,?,?)";  

        try{  
        	Connection conn = DriverManager.getConnection(url);   
            PreparedStatement pstmt = conn.prepareStatement(sql);  
            pstmt.setInt(1, id);
            pstmt.setString(2, movie_name);  
            pstmt.setString(3, actor);
            pstmt.setString(4, actress);
            pstmt.setInt(5, year);
            pstmt.setString(6, director);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }


	public static void selectAll(){  

		String url = "jdbc:sqlite:C://sqlite/MuleSoft.db";  

        String sql = "SELECT * FROM Movies";  

        try {  
        	Connection conn = DriverManager.getConnection(url);   
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql); 
            // loop through the result set  
            while (rs.next()) {  
                System.out.println("Movie " + rs.getInt("id") + ":"); 
                System.out.println("\t" + "Name: " + rs.getString("movie_name") + "\n" +
                					"\t" + "Actor: " + rs.getString("actor") + "\n" +
                					"\t" + "Actress: " + rs.getString("actress") + "\n" +
                					"\t" + "Year Of Release: " + rs.getInt("year") + "\n" +
                					"\t" + "Director: " + rs.getString("director") + "\n---------------------------------------------"); 
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }


	public static void selectQuery(){

		String url = "jdbc:sqlite:C://sqlite/MuleSoft.db";  

        String sql = "SELECT * FROM Movies WHERE actor = 'NTR'";  

        try {  
        	Connection conn = DriverManager.getConnection(url); 
            Statement stmt  = conn.createStatement();  
            ResultSet rs    = stmt.executeQuery(sql); 
            // loop through the result set  
            while (rs.next()) {  
                System.out.println("Movie " + rs.getInt("id") + ":"); 
                System.out.println("\t" + "Name: " + rs.getString("movie_name") + "\n" +
                					"\t" + "Actor: " + rs.getString("actor") + "\n" +
                					"\t" + "Actress: " + rs.getString("actress") + "\n" +
                					"\t" + "Year Of Release: " + rs.getInt("year") + "\n" +
                					"\t" + "Director: " + rs.getString("director") + "\n---------------------------------------------"); 
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }


    public static void main(String[] args) {  
        createNewDatabase("MuleSoft.db");

        createNewTable();

        insert(1, "RRR", "NTR", "Alia Bhatt", 2022, "Rajamouli");  
        insert(2, "KGF2", "Yash", "Srindhi Shetty", 2022, "Prashanth Neel");
	insert(3, "Bahubali", "Prabhas", "Anushka", 2015, "Rajamouli");  
        insert(4, "Bajirao Mastani", "Ranveer Singh", "Deepika Padukone", 2015, "Sanjay Leela Bhansali"); 
        insert(5, "Shershaah", "Sidharth Malhotra", "Kiara Advani", 2021, "Vishnu Vardhan");
        insert(6, "Jersey", "Nani", "Shraddha Srinath", 2019, "Gowtam Tinnanuri");
        insert(7, "Dilwale", "Shah Rukh Khan", "Kajol Devgan", 2015, "James Cameron");
        insert(8, "Dhruva", "Ram Charan", "Rakul Preet Singh", 2016, "Surender");
        insert(9, "Saaho", "prabhas", "Shraddha Kapoor", 2015, " Rohit Shetty");
        insert(10, "Pink", "Amitabh Bachchan", "Taapsee Pannu", 2016, "Aniruddha Roy Chowdhury");

        selectAll();
        System.out.println("\n\n=====================================================\n\n");

        selectQuery();
    } 
}