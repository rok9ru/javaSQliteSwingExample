package database.simpleDBModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QueryExecutor {

    private String tableName;
    private Connection conn;

    public QueryExecutor(String tableName, Connection conn) {

        this.tableName = tableName;
        this.conn = conn;
    }

    public ArrayList<ArrayList<Object>> get(List<TableField> columns) throws SQLException {

        String fields = columns.stream().map(Object::toString)
                .collect(Collectors.joining(", "));

        String sql = "SELECT " + fields + " FROM " + tableName;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<ArrayList<Object>> data = new ArrayList<>();

        //Заносим данные с базы
        while (rs.next()) {
            ArrayList<Object> o = new ArrayList<>();
            for (TableField column : columns) {
                o.add(rs.getObject(column.getFieldName()));
            }
            data.add(o);
        }

        return data;
    }

    public boolean update(List<TableField> columns, List<Object> values) throws SQLException {
        if (columns.size() < 1)
            return false;

        StringBuilder val = new StringBuilder();
        ArrayList<Object> sqlValues = new ArrayList<>();

       int primaryKey = -1;


        for (int i = 0; i < columns.size(); i++) {
            Object v = values.get(i);
            if (v == null) {
                continue;
            }
            if (columns.get(i).isPrimaryKey()) {
                primaryKey = i;
                continue;
            }


            val.append(columns.get(i)).append(" = ?");
            sqlValues.add(values.get(i));
            if (i != columns.size() - 1) {
                val.append(", ");
            }
        }
        if (primaryKey < 0) {
            throw new RuntimeException("Primary key not found, Couldn't execute query!");
        }


        String sql = "UPDATE " + tableName + " SET " + val + " WHERE " + columns.get(primaryKey) + "=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        for (int i = 0; i < sqlValues.size(); i++) {
            pstmt.setObject(i+1, sqlValues.get(i));
        }

        pstmt.setObject(sqlValues.size()+1, values.get(primaryKey));

        pstmt.executeUpdate();

        return true;
    }
    public boolean remove(List<TableField> columns, List<Object> values){
        return false;
    }

    public List<Object> create(List<TableField> columns, List<Object> values){
        return null;
    }
}
