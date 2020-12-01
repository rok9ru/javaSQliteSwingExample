package database.simpleDBModel;


import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBModel extends AbstractTableModel {

    public DBModel(List<TableField> columns, QueryExecutor queryExecutor, boolean autoSave) {
        this.columns = columns;
        this.queryExecutor = queryExecutor;
        this.autoSave = autoSave;
    }

    public DBModel(List<TableField> columns, QueryExecutor queryExecutor) {
        this.columns = columns;
        this.queryExecutor = queryExecutor;
        this.autoSave = true;
    }

    private final List<TableField> columns;
    private QueryExecutor queryExecutor;
    private boolean autoSave;






    private ArrayList<ArrayList<Object>> data = new ArrayList<>();

    private ArrayList<Integer> changedRows = new ArrayList<>();


    public String getColumnName(int column) {
        return columns.get(column).getFieldText();
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
        return columns.get(column).isEditable();
    }

    public void setValueAt(Object value, int row, int col) {
        data.get(row).set(col, value);
        changedRows.add(row);
        if(autoSave){
            try {
                saveData();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        this.fireTableCellUpdated(row, col);
    }

    public void reloadData() throws SQLException {
        data = queryExecutor.get(columns);

        this.fireTableDataChanged();
    }

    public void saveData() throws SQLException {
        for (Integer row: changedRows) {
            queryExecutor.update(columns, data.get(row));
        }
        changedRows.clear();
    }

    public void removeRow(int row){
        queryExecutor.remove(columns, data.get(row));
       // ArrayList<Object> r = data.get(row);
    }


    public List<TableField> getColumns() {
        return columns;
    }

    public boolean isAutoSave() {
        return autoSave;
    }

    public void setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
    }

}
