package me.cosmodev.utils.db.sql;

import java.sql.*;
import java.util.HashMap;

public class SQLiteManager {
    private Connection connection;

    private SQLiteManager() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:server-db.sqlite");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName, HashMap<String, String> columns) {
        StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");

        for (HashMap.Entry<String, String> entry : columns.entrySet()) {
            query.append(entry.getKey()).append(" ").append(entry.getValue()).append(",");
        }

        query.setLength(query.length() - 1);
        query.append(")");

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query.toString());
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }
    public void setValue(String tableName, String columnName, Object value, String column, Object valuee) {
        String query = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE " + column + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, value);
            preparedStatement.setObject(2, valuee);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }
    }

    public Object getValue(String table, String name, String condition, Object value) {
        String query = "SELECT " + name + " FROM " + table + " WHERE " + condition + " = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, value);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getObject(name);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка: " + e.getMessage());
        }

        return null;
    }
//    public static void main(String[] args) {
//        SQLiteManager sqliteManager = new SQLiteManager();
//
//        // Пример создания таблицы
//        HashMap<String, String> columns = new HashMap<>();
//        columns.put("id", "INTEGER PRIMARY KEY");
//        columns.put("name", "TEXT");
//        columns.put("age", "INTEGER");
//        sqliteManager.createTable("people", columns);
//
//        // Пример добавления данных в таблицу
//        sqliteManager.setValue("people", "name", "John Doe", "id", 1);
//        sqliteManager.setValue("people", "age", 25, "id", 1);
//
//        // Пример получения данных из таблицы
//        String name = (String) sqliteManager.getValue("people", "name", "id", 1);
//        int age = (int) sqliteManager.getValue("people", "age", "id", 1);
//
//        System.out.println("Name: " + name);
//        System.out.println("Age: " + age);
//    }
}
