package symbolTable.record;

import java.util.HashMap;

public class ClassRecord extends Record {
    private final HashMap<String, MethodRecord> methods = new HashMap<>();
    private final HashMap<String, VarRecord> fields = new HashMap<>();
    private ClassRecord parent = null; // New field for inheritance

    public ClassRecord(String id, String type) {
        super(id, type);
    }

    public void setParent(ClassRecord parent) {
        this.parent = parent;
    }

    public ClassRecord getParent() {
        return this.parent;
    }

    public void addMethod(String methodName, MethodRecord methodRecord) {
        this.methods.put(methodName, methodRecord);
    }

    public Record getMethod(String methodName){
        Record method = methods.get(methodName);
        if (method == null && parent != null) {
            return parent.getMethod(methodName); // Look up in parent
        }
        return method;
    }

    public void addField(String fieldName, VarRecord fieldRecord){
        this.fields.put(fieldName, fieldRecord);
    }

    public Record getField(String fieldName){
        Record field = fields.get(fieldName);
        if (field == null && parent != null) {
            return parent.getField(fieldName); // Look up in parent
        }
        return field;
    }
}