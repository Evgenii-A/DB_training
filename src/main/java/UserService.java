import java.sql.*;

public class UserService {
    private Connection connection;
    private EmailValidator validator;

    public UserService(Connection connection) {
        this.connection = connection;
    }

    public UserService(Connection connection, EmailValidator validator) {
        this.connection = connection;
        this.validator = validator;
    }

    public void addUser(String first_name, String last_name, int age, String email) {
        String sql = "INSERT INTO user_table (first_name, last_name, age, email) values (?,?,?,?);";
        if (validator.validate(email)) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, first_name);
                preparedStatement.setString(2, last_name);
                preparedStatement.setInt(3, age);
                preparedStatement.setString(4, email);
                int count = preparedStatement.executeUpdate();
                System.out.println("count:" + count);

                preparedStatement.close();
            } catch (SQLException throwables) {
                System.out.println("Insertion failure");
                throwables.printStackTrace();
            }
        } else System.out.println("not valid email");
    }

    public void delUser(int id) {
        if (id >= 1) {
            String sql = "DELETE FROM user_table WHERE id = ?;";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                System.out.println("deleted id " + id + ": " + preparedStatement.execute());
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException throwables) {
                System.out.println("Delete failure");
                throwables.printStackTrace();
            }
        }
    }

    public boolean updateUser(String first_name, String last_name, int age, int id) {
        String sql = "UPDATE user_table SET first_name = ?, last_name = ?, age = ?  WHERE id = ?";
        PreparedStatement preparedStatement = null;
        boolean execute = false;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, id);
            execute = preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return execute;
    }

    public void showAllUsers() {
        String sql = "SELECT * FROM user_table;";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + " ");
                System.out.print(resultSet.getString(2) + " ");
                System.out.print(resultSet.getInt(3) + " ");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
