package me.cosmodev.utils.db.mysql;

import me.cosmodev.utils.ListUtils;
import me.cosmodev.utils.db.mysql.DatabaseResourceType;
import org.jetbrains.annotations.Nullable;

import java.sql.*;

public class MySqlManager {
    private Connection connection;
    private String db;
    private String host;
    private String user;
    private String password;

    public MySqlManager(String db, String host, String user, String password){
        String jdbc = "dbc:mysql://" + host + "/" + db;

        this.db = db;
        this.host = host;
        this.user = user;
        this.password = password;

        try {
            connection = DriverManager.getConnection(jdbc, user, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void createTable(String name, String ... params){
        try {
            Statement statement = getConnection().createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS " + name + "(" + ListUtils.listToString(ListUtils.arrayToList(params)) + ")");

            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            if(!connection.isClosed()){
                return connection;
            }

            String jdbc = "jdbc:mysql://" + host + "/" + db;

            connection = DriverManager.getConnection(jdbc, user, password);

            return connection;
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Nullable
    public Object getResource(DatabaseResourceType type, String table, String key, String keyRaw, String findRaw){
        try {
            Statement statement = getConnection().createStatement();
            String sql = "SELECT * FROM " + table + " WHERE " + keyRaw + " = '" + key + "'";
            ResultSet results = statement.executeQuery(sql);

            switch (type){
                case INT:
                    return results.getInt(findRaw);
                case URL:
                    return results.getURL(findRaw);
                case BLOB:
                    return results.getBlob(findRaw);
                case BYTE:
                    return results.getByte(findRaw);
                case DATE:
                    return results.getDate(findRaw);
                case LONG:
                    return results.getLong(findRaw);
                case ARRAY:
                    return results.getArray(findRaw);
                case BYTES:
                    return results.getBytes(findRaw);
                case FLOAT:
                    return results.getFloat(findRaw);
                case SHORT:
                    return results.getShort(findRaw);
                case OBJECT:
                    return results.getObject(findRaw);
                case STRING:
                    return results.getString(findRaw);
                case BOOLEAN:
                    return results.getBoolean(findRaw);
                case BIG_DECIMAL:
                    return results.getBigDecimal(findRaw);
                case BINARY_STREAM:
                    return results.getBinaryStream(findRaw);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void updateResource(String table, String key, String keyRaw, String ... resource){
        try {
            String sql = "UPDATE " + table + " SET " + ListUtils.listToString(ListUtils.arrayToList(resource)) + " WHERE " + keyRaw + " = '" + key + "'";
                    Statement statement = getConnection().createStatement();
                    statement.execute(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setResource(String table, String key, String keyRaw, String ... resource){
        try {
            String sql = "INSERT INTO " + table + "(" + ListUtils.listToString(ListUtils.arrayToList(resource)) + ")";
            Statement statement = getConnection().createStatement();
            statement.execute(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
// Example:
//    public static MySqlManager database;
//
//    database = new MySqlManager("database", "185.103.101.30:3306", "root", "password");
//    database.createTable("authme", "player_name varchar(16) primary key", "color varchar(1000)", "mainColor varchar(1000)");
//    int i = (int) database.getResource(DatabaseResourceType.INT, "authme", "yuiopmju", "player", "registerDate");
//
//    async(() -> {
//        System.out.println("Async message");
//    });
//
//    public static void async(Runnable runnable){
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//
//        executor.execute(() -> {
//            try {
//                Thread.sleep(200);
//                runnable.run();
//                executor.shutdown();
//            } catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        });
//    }
}
