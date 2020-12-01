package database.simpleDBModel;

public class TableField {
    private String fieldName;
    private String fieldText;
    private boolean editable;
    private boolean primaryKey;

    public TableField(String fieldName, String fieldText, boolean Editable) {
        this.fieldName = fieldName;
        this.fieldText = fieldText;
        editable = Editable;
    }
    public TableField(String fieldName, String fieldText) {
        this.fieldName = fieldName;
        this.fieldText = fieldText;
        editable = true;
    }

    public TableField(String fieldName, String fieldText, boolean Editable, boolean primaryKey) {
        this.fieldName = fieldName;
        this.fieldText = fieldText;
        editable = Editable;
        this.primaryKey = primaryKey;
    }


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldText() {
        return fieldText;
    }

    public void setFieldText(String fieldText) {
        this.fieldText = fieldText;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String toString() {
        return fieldName;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
}
