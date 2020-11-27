package models;


import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Table1Model extends AbstractTableModel {

    public Table1Model(LinkedHashMap<String, String> columns, Connection conn) {
        this.columns = columns;
        this.conn = conn;
    }

    private final LinkedHashMap<String, String> columns;
    private final Connection conn;

    public ArrayList<ArrayList<Object>> data = new ArrayList<>();


    public String getColumnName(int column) {
        List<String> l = new ArrayList<>(columns.values());
        return l.get(column);

      //  return (String) columns.entrySet().toArray()[column];
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columns.size();
    }

    public Object getValueAt(int row, int col) {
        return data.get(row).get(col);
    }

    public boolean isCellEditable(int row, int column) {
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        data.get(row).set(col, value);
        //data.get(row)[col] = value;
        this.fireTableCellUpdated(row, col);
    }

    public void reloadData() throws SQLException {

        String sql = "SELECT * FROM demo";
        Statement stmt = conn.createStatement();
        ResultSet rs =  stmt.executeQuery(sql);

        data.clear();
        while (rs.next()) {
           ArrayList<Object> o = new ArrayList<>();

            for(Map.Entry<String, String> entry : columns.entrySet()) {
                String key = entry.getKey();
                o.add(rs.getString(key));
            }
            data.add(o);
        }
        this.fireTableDataChanged();
    }


    public Map<String, String> getColumns() {
        return columns;
    }


}
