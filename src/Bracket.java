import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Parent brackets class
 */
public abstract class Bracket {

    private char openSymbol;
    private char closeSymbol;
    private LinkedList<Integer> bracketsCollector = new LinkedList<Integer>();

    public char getOpenSymbol() {
        return openSymbol;
    }

    public char getCloseSymbol() {
        return closeSymbol;
    }

    Bracket (char o, char c) {
        openSymbol = o;
        closeSymbol = c;
    }

    /**
     * Checks brace matching
     * @param formula formula from one row
     * @return LinkedList
     */
    public LinkedList validate(String formula) {
        char[] f = formula.toCharArray();
        for (int i = 0; i < f.length; i++) {
            if (f[i] == openSymbol)
                bracketsCollector.add(i);
            else if (f[i] == closeSymbol)
                try {
                    bracketsCollector.removeLast();
                } catch (NoSuchElementException ex) {
                    //if first bracket is closed
                    bracketsCollector.add(i);
                }
        }
        if (bracketsCollector.size() > 0)
            return bracketsCollector;
        else
            return null;
    }

}
