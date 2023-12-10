package ru.ssau.OOP_lab.components;


import ru.ssau.OOP_lab.functions.MathFunction;

public class MathFunctionComponent {
    private String function;
    private Integer xFrom;
    private Integer xTo;
    private Integer size;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Integer getxFrom() {
        return xFrom;
    }

    public void setxFrom(Integer xFrom) {
        this.xFrom = xFrom;
    }

    public Integer getxTo() {
        return xTo;
    }

    public void setxTo(Integer xTo) {
        this.xTo = xTo;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
