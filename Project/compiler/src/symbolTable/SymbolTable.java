package symbolTable;

import symbolTable.record.Record;
import symbolTable.record.ClassRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private final Scope root;
    private Scope curr;

    public SymbolTable() {
        this.root = new Scope(null);
        this.curr = root;
    }

    public String getCurrentClassName(){
        return this.curr.getContainingClass().getId();
    }

    public String getCurrentScopeName(){
        return this.curr.getScopeName();
    }

    public String getCurrentScopeType() {
        return this.curr.getScopeType();
    }

    public void setCurrentScopeNameAndType(String scopeName, String scopeType){
        this.curr.setScopeNameAndType(scopeName, scopeType);
    }

    // create a new scope if necessary
    public void enterScope() {
        curr = curr.nextChild();
    }

    public void setCurrentScopeClass(ClassRecord containingClass) {
        this.curr.setContainingClass(containingClass);
    }

    public void exitScope() {
        curr = curr.getParent();
    }

    public void put(String key, Record item) {
        curr.put(key, item);
    }

    public Record lookup(String key) {
        return curr.lookup(key);
    }

    private void printLine(String id, String record, String scope) {
        System.out.printf("|" + "%" + 15 + "s %" + 15 + "s %" + 30 + "s %" + 7 + "s|%n", id, record, scope, ' ');
    }

    public void printTable() {
        System.out.println("\nSymbol Table:\n");
        System.out.printf("%s %n", "+======================================================================+");
        System.out.printf("|" + "%" + 15 + "s %" + 15 + "s %" + 30 + "s %" + 7 + "s|%n", "ID", "TYPE", "SCOPE", "");
        System.out.printf("%s %n", "+======================================================================+");
        root.printScope();
        System.out.printf("%s %n", "+======================================================================+");
    }

    public void resetTable() {
        root.resetScope();
    }

    private class Scope {
        private int next = 0;
        private final Scope parent;

        private final ArrayList<Scope> children = new ArrayList<Scope>();
        private final Map<String, Record> records = new HashMap<String, Record>();

        private ClassRecord containingClass = new ClassRecord("prog", "program");

        private String scopeName = "", scopeType = "";

        public Scope(Scope parent) {
            this.parent = parent;
        }

        public void setScopeNameAndType(String scopeName, String scopeType) {
            this.scopeName = scopeName;
            this.scopeType = scopeType;
        }

        public String getScopeName(){
            return this.scopeName;
        }

        public String getScopeType(){
            return scopeType;
        }

        public ClassRecord getContainingClass() {
            return containingClass;
        }

        public void setContainingClass(ClassRecord containingClass) {
            this.containingClass = containingClass;
        }

        public Record getMethod(String methodName) {
            return this.containingClass.getMethod(methodName);
        }

        public Scope getParent() {
            return this.parent;
        }

        public void printScope() {
            for (Map.Entry<String, Record> stringRecordEntry : records.entrySet()) {
                Record record = stringRecordEntry.getValue();
                printLine(stringRecordEntry.getKey(), record.getType(), scopeName + " (" + scopeType + ") ");
            }
            for (Scope i : children)
                i.printScope();
        }

        public void put(String key, Record record) {
            records.put(key, record);
        }

        public Scope nextChild() {
            Scope nextChild;
            if (next >= children.size()) {
                nextChild = new Scope(this);
                children.add(nextChild);
            } else {
                nextChild = children.get(next);
            }
            next++;
            return nextChild;
        }

        public void resetScope() {
            next = 0;
            for (Scope child : children) {
                child.resetScope();
            }
        }

        public Record lookup(String id) {
            if (id.equals("this")) {
                return containingClass;
            }
            if (records.containsKey(id)) {
                return records.get(id);
            }
            if (parent == null)
                    return null;
            return parent.lookup(id);
        }

    }
}
