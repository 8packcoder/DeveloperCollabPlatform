import java.sql.*;
import java.util.Scanner;

public class DeveloperCollabPlatform {
    // Database URL, username, and password (Change as per your setup)
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/dev_collab?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    // Method to establish a database connection
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database Connected Successfully");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // User Registration
    public static void registerUser(String name, String email) {
        String query = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("User Registered Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View All Users
    public static void viewUsers() {
        String query = "SELECT * FROM users";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name") + " | Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create Project
    public static void createProject(String projectName, String description) {
        String query = "INSERT INTO projects (name, description) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, projectName);
            pstmt.setString(2, description);
            pstmt.executeUpdate();
            System.out.println("Project Created Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Projects
    public static void viewProjects() {
        String query = "SELECT * FROM projects";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Project Name: " + rs.getString("name") + " | Description: " + rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Join Project
    public static void joinProject(int userId, int projectId) {
        String query = "INSERT INTO team_members (user_id, project_id) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, projectId);
            pstmt.executeUpdate();
            System.out.println("User Joined Project Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View Members of a Project
    public static void viewProjectMembers(int projectId) {
        String query = "SELECT u.id, u.name, u.email " +
                "FROM users u " +
                "JOIN team_members tm ON u.id = tm.user_id " +
                "WHERE tm.project_id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, projectId);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Members of Project ID " + projectId + ":");
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("id") +
                        " | Name: " + rs.getString("name") +
                        " | Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main Method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Register User");
            System.out.println("2. View Users");
            System.out.println("3. Create Project");
            System.out.println("4. View Projects");
            System.out.println("5. Join Project");
            System.out.println("6. View Project Members");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    registerUser(name, email);
                    break;
                case 2:
                    viewUsers();
                    break;
                case 3:
                    System.out.print("Enter Project Name: ");
                    String projectName = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    createProject(projectName, description);
                    break;
                case 4:
                    viewProjects();
                    break;
                case 5:
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter Project ID: ");
                    int projectId = scanner.nextInt();
                    joinProject(userId, projectId);
                    break;
                case 6:
                    System.out.print("Enter Project ID to view members: ");
                    int viewProjectId = scanner.nextInt();
                    viewProjectMembers(viewProjectId);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        }
    }
}
