package functions;

import java.util.*;

public class DefinedIntegral implements MathFunction {
    double x;
    public static String func;

    DefinedIntegral() {
        Scanner in = new Scanner(System.in);
        func = in.nextLine();
    }

    public enum Conditions
    {
        S0,S1,S2,S3,S4,S5,S6;
    }

    public enum Signals{
        Funlet, Numb,X,Ls,Lr,xP
    }
    public void createtable(Conditions c[6][6] ){

    }
    public static double FindIntegral(String tmpa, String tmpf)
    {
        double r=0;
        List<String> TempA = new LinkedList<String>();
        Conditions c[6][6];
        for(int j=0;j<tmpa.length();++j)
        {

        }
        return r;
    }
    public static double apply(double x, String f) {
        List<Double> TempRes = new LinkedList<Double>();
        List<Character> TempOp = new LinkedList<Character>();
        String tmpf="";
        String tmpa="";
        boolean is_fun = true;
        double s = 0;
        for (int i = 0; i < func.length(); ++i) {
            if (func.charAt(i)!=' ') {
                if (func.charAt(i) == '+' || func.charAt(i)  == '-' || func.charAt(i) == '*') TempOp.add(func.charAt(i));
                else {
                    if (func.charAt(i) != '(' && func.charAt(i) != ')') {
                        tmpf+=func.charAt(i);
                    }
                    else
                    {
                        is_fun = (func.charAt(i)== ')') ? true: false;
                        tmpa+=func.charAt(i);
                    }
                }
            }
            else {
                TempRes.add(FindIntegral(tmpa,tmpf));
                tmpa="";
                tmpf="";
            }
        }
        return s;
    }
}
