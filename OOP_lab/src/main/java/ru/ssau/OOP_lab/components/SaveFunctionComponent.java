package ru.ssau.OOP_lab.components;


import java.util.List;

public class SaveFunctionComponent {
    private String whichFunction;
    private String functionName;
    private List<String> listOfSavedFunctions;

    public String getWhichFunction() {
        return whichFunction;
    }

    public void setWhichFunction(String whichFunction) {
        this.whichFunction = whichFunction;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<String> getListOfSavedFunctions() {
        return listOfSavedFunctions;
    }

    public void setListOfSavedFunctions(List<String> listOfSavedFunctions) {
        this.listOfSavedFunctions = listOfSavedFunctions;
    }
}
