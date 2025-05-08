# 💻 DeveloperCollabPlatform

## 📌 Project Description

**DeveloperCollabPlatform** is a Java console-based application that allows developers to collaborate on software projects. It supports user registration, project creation, team management, and collaboration features — all backed by a MySQL database using JDBC.

---

## 💻 Tech Stack

- **Language:** Java (JDK 8+)
- **Database:** MySQL
- **Connector:** MySQL JDBC (mysql-connector-java)
- **IDE Recommended:** IntelliJ IDEA, Eclipse, or VS Code
- **Build Tool:** Manual / Maven

---

## 🧩 MySQL Database Setup

### 📁 1. Create the Database and Tables

Run the following SQL commands:

```sql
CREATE DATABASE dev_collab;

USE dev_collab;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

CREATE TABLE projects (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT
);

CREATE TABLE team_members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    project_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);
```

---

## 🔌 Add JDBC Dependency

### If using **Maven**, add this to your `pom.xml`:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

> If you're not using Maven, download the JAR manually from the [MySQL Connector/J website](https://dev.mysql.com/downloads/connector/j/) and add it to your classpath.

---

## ⚙️ Database Connection Code (Java)

```java
private static final String URL = "jdbc:mysql://127.0.0.1:3306/dev_collab?useSSL=false&allowPublicKeyRetrieval=true";
private static final String USER = "root";
private static final String PASSWORD = "your_password";

public static Connection getConnection() {
    Connection conn = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Database Connected Successfully");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    return conn;
}
```

> 📝 **Note:** Replace `your_password` with your actual MySQL password.

---

## 📦 Features Implemented

- ✅ User registration  
- ✅ View all users  
- ✅ Create project  
- ✅ View all projects  
- ✅ Join a project  
- ✅ View project members  

---

## 🏁 Running the Application

1. Compile the program:

```bash
javac DeveloperCollabPlatform.java
```

2. Run the program:

```bash
java DeveloperCollabPlatform
```

3. Use the menu-driven interface to interact with the system.

---

## 📎 Contribution

This is a beginner-friendly, console-based Java project.  
Feel free to fork this repository and contribute by adding:

- GUI (JavaFX or Swing)
- Authentication system
- Project search functionality
- Export/Import features
- REST API support

---

## 📫 Contact

**Saurabh Kumar Bajpai**  
📧 Email: [saurabhbajpai03@outlook.com](mailto:saurabhbajpai03@outlook.com)  
🔗 LinkedIn: [linkedin.com/in/saurabhbajpai03](https://www.linkedin.com/in/saurabhbajpai03/)  
🐙 GitHub: [8packcoder](https://github.com/8packcoder)

---

## 📄 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for more details.
