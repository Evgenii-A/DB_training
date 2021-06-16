import java.sql.*;

public class Main {

    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";

    static final String USER = "postgres";
    static final String PASSWORD =                                                                                        "Jupiter1";

    public static void main(String[] args) throws SQLException {

        System.out.println("Connecting to database...");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);



        System.out.println("current isolation level: " + connection.getTransactionIsolation());
        String sql = "select * from user_table";


        UserService userService = new UserService(connection, new EmailValidator());
        userService.addUser("name", "familia", 10, "griskjdaflhjha@mail.com");



        userService.showAllUsers();

        connection.close();




    }
}