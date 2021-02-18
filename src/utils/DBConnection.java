package dBUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() throws IOException, SQLException {
        Connection conn;
        Properties props = new Properties();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("app.properties"), Charset.forName("UTF-8"))) {
            props.load(reader);
        }
        String dbUrl = props.getProperty("dbUrlAddress");
        String userName = props.getProperty("userName");
        String pass = props.getProperty("password");
        conn = DriverManager.getConnection(dbUrl, userName, pass);
        return conn;

    }
}

