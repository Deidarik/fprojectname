package functions;

public enum FunctionsType{
    ERROR(0),
    SIN(1),
    COS(2),
    X(3),
    CONST(4),
    TG(5),
    CTG(6),
    ASIN(7),
    ACOS(8),
    ATG(9),
    ACTG(10),
    LN(11),
    POWER(12),
    COMP(13),
    MULT(14),
    SUM(15);
    private final int value;
    FunctionsType(int v)
    {
        this.value = v;
    }
    public int getValue(){return value;}
}