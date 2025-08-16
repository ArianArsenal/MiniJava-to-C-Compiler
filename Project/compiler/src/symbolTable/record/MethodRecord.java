package symbolTable.record;

import java.util.*;

public class MethodRecord extends Record {

    private final HashMap<Integer, VarRecord> params = new HashMap<Integer, VarRecord>();
    int paramNum = 0;

    public MethodRecord(String id, String type) {
        super(id, type);
    }

    public void addParameter(VarRecord param) {
        this.params.put(paramNum, param);
        paramNum++;
    }

    public List<String> getLocals() {
        List<String> locals = new ArrayList<>();
        for (Map.Entry<Integer, VarRecord> integerVarRecordEntry : params.entrySet()) {
            locals.add(integerVarRecordEntry.getValue().getId());
        }
        return locals;
    }

    public boolean containParam(int paramNum, Record param) {
        Record paramRec = params.get(paramNum);
        if (param == null || paramRec == null) {
            return false;
        }
        return paramRec.getType().equals(param.getType());
    }

    public int numberOfParams() {
        return params.size();
    }

    public void print() {
        System.out.println("{");
        for (Map.Entry<Integer, VarRecord> integerVarRecordEntry : params.entrySet()) {
            System.out.print(integerVarRecordEntry.getValue());
        }
        System.out.println(" }\n");
    }

    public boolean containParameter(int paramNumber, Record parameter) {
        Record paramRec = params.get(paramNumber);
        if (parameter == null || paramRec == null)
            return false;
        return params.get(paramNumber).getType().equals(parameter.getType());
    }

    public int numberOfParameters() {
        return params.size();
    }

}
