package symbolTable.record;

public class Record {

    protected String id;
    protected String type;

    public Record(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[id]:[type] -> " + id + " : " + type;
    }
}
