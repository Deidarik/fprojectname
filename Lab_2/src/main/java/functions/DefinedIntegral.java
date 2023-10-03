package functions;
import java.util.ArrayList;

class Analyze
{
    enum TypesOfWords{
        start(0),space(1),numbr(2),word_(3),openBr(4),closeBr(5),oper(6),error(7);
        TypesOfWords(int argument)
        {
            this.argument = argument;
        }
        final private int argument;
        public int getArgument(){return argument;}
    }
    static class PartsOfText
    {
        public String word;
        public TypesOfWords type;
        PartsOfText(String s, TypesOfWords t)
        {
            word = s;
            type = t;
        }
    }
    private final String sentence ;
    public ArrayList<PartsOfText> readyParts;
    Analyze(String l)
    {
        sentence = l;
        readyParts = new ArrayList<>();
    }
    private boolean isDigit(char c)
    {
        return (('1' <= c)&&(c<='9'));
    }
    private boolean isLetter(char c)
    {
        return (('a' <= c) && (c<='z'));
     }
    private boolean isArithOper(char c)
    {
        return (c == '+' || c == '-' || c == '/' || c == '*');
    }
    private boolean isSpace(char c)
    {
        return (c == ' ');
    }
    private TypesOfWords typeOfChar(char c)
    {
        if(isLetter(c))
            return TypesOfWords.word_;
        else if(isDigit(c))
            return TypesOfWords.numbr;
        else if(isArithOper(c))
            return TypesOfWords.oper;
        else if(isSpace(c))
            return TypesOfWords.space;
        else if(c == '(')
            return TypesOfWords.openBr;
        else if(c == ')')
            return TypesOfWords.closeBr;
        else
            return TypesOfWords.error;
    }
    public void firstAnalyze()
    {
        char curChar;
        String word = "";
        TypesOfWords prevState ;
        TypesOfWords state = TypesOfWords.start;
        TypesOfWords[][] table = new TypesOfWords[][]
                {
                //    start                space             number              word                openBracket         closeBracket         oper                error
                {TypesOfWords.error,  TypesOfWords.error,  TypesOfWords.error,  TypesOfWords.error,  TypesOfWords.error, TypesOfWords.error,  TypesOfWords.error, TypesOfWords.error},// start
                {TypesOfWords.space,  TypesOfWords.space,  TypesOfWords.space,  TypesOfWords.space,  TypesOfWords.space, TypesOfWords.space,  TypesOfWords.space, TypesOfWords.error},// space
                {TypesOfWords.numbr,  TypesOfWords.numbr,  TypesOfWords.numbr,  TypesOfWords.error,  TypesOfWords.numbr, TypesOfWords.error,  TypesOfWords.numbr, TypesOfWords.error},// number
                {TypesOfWords.word_,  TypesOfWords.word_,  TypesOfWords.error,  TypesOfWords.word_,  TypesOfWords.word_, TypesOfWords.error,  TypesOfWords.word_, TypesOfWords.error},// word
                {TypesOfWords.openBr, TypesOfWords.openBr, TypesOfWords.error,  TypesOfWords.openBr, TypesOfWords.openBr,TypesOfWords.error,  TypesOfWords.openBr,TypesOfWords.error},// openBracket
                {TypesOfWords.closeBr,TypesOfWords.closeBr,TypesOfWords.closeBr,TypesOfWords.closeBr,TypesOfWords.error, TypesOfWords.closeBr,TypesOfWords.error, TypesOfWords.error},// closeBracket
                {TypesOfWords.error,  TypesOfWords.oper,   TypesOfWords.oper,   TypesOfWords.oper ,  TypesOfWords.error, TypesOfWords.oper,   TypesOfWords.error, TypesOfWords.error},// oper

                };

        for(int i =0; i < sentence.length(); ++i)
        {
            curChar = sentence.charAt(i);

            prevState = state;
            state = table[typeOfChar(curChar).getArgument()][state.getArgument()];

            if(prevState != state && prevState != TypesOfWords.start)
            {
                if(prevState != TypesOfWords.space)
                {
                    PartsOfText ready;
                    ready = new PartsOfText(word, prevState);


                    readyParts.add(ready);
                    word = "";

                }

            }
            if(!isSpace(curChar))
                word+=curChar ;

        }
        if(!word.isEmpty())
        {
            PartsOfText ready = new PartsOfText(word, state);
            readyParts.add(ready);
            word = "";
        }

    }
}
//public class DefinedIntegral implements MathFunction {
//
//
//
//}
