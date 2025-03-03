package stallitium;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Db {
    String path;
    String tableName;
    public Db(String fileName,String tableName) {
        path = "data/"+fileName;
        this.tableName = tableName;
        //フォルダ作成
        File sqliteFolder = new File("data");
        if (!sqliteFolder.exists()) {
            sqliteFolder.mkdirs();
        }
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:"+path);
                Statement stmt = connection.createStatement();
        ){
            stmt.execute("CREATE TABLE IF NOT EXISTS "+tableName+" ('key' TEXT, 'value' TEXT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> read(int page) {
        //読み込みスタート地点
        int offset = page*5;
        List<String> res = new ArrayList<>();
        String selectQuery = "SELECT key, value FROM "+tableName+" LIMIT ? OFFSET ?";

        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:"+path);
                PreparedStatement pstmt = connection.prepareStatement(selectQuery);
                ){
            pstmt.setInt(1,5);
            pstmt.setInt(2,offset);
            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    String key = rs.getString("key");
                    String value = rs.getString("value");
                    res.add(value);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    //updateでtrue insertでfalse
    public boolean write(String name, String id, String type) {
        String updateQuery = "UPDATE "+tableName+" SET value = ? WHERE key = ?";
        String insertQuery = "INSERT INTO "+tableName+" (key, value) VALUES (?, ?)";

        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:"+path);
                PreparedStatement pstmtUpdate = connection.prepareStatement(updateQuery);
        ){
            //アップデートクエリ
            pstmtUpdate.setString(1,name+"#"+id+"#"+type);
            pstmtUpdate.setString(2,id);
            int rows = pstmtUpdate.executeUpdate();
            if (rows == 0) {
                //アップデートが行われなかった場合insert
                try (PreparedStatement pstmtInsert = connection.prepareStatement(insertQuery)){
                    pstmtInsert.setString(1,id);
                    pstmtInsert.setString(2,name+"#"+id+"#"+type);
                    pstmtInsert.executeUpdate();
                    return false;
                }
            } else {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean exists(String key) {
        String selectQuery = "SELECT 1 FROM "+tableName+" WHERE key = ? LIMIT 1";
        try (
                Connection connection = DriverManager.getConnection("jdbc:sqlite:"+path);
                PreparedStatement pstmt = connection.prepareStatement(selectQuery);
        ) {
            pstmt.setString(1,key);
            try (ResultSet rs = pstmt.executeQuery()){
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
