import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Help to write info in file
 */
public class MyFileWriter extends FileOutputStream {
    private static final char error = '^';
    private static String errorString = "";
    private static final String separator = System.getProperty("line.separator");

    public MyFileWriter(String name) throws FileNotFoundException {
        super(name, true);
    }

    public boolean writeContent(String formula, LinkedList errors) throws IOException {

        //write formula
        formula += separator;
        this.writeFormula(formula.getBytes());

        //Get error(s) string
        int tmp;
        for (int i = 0; i < errors.size(); i++) {
            if (i == 0)
                tmp = (Integer) errors.get(i);
            else
                tmp = (Integer) errors.get(i) - (Integer) errors.get(i - 1) - 1;
            for (int j = 0; j < tmp; j++) {
                errorString += " ";
            }
            errorString += error;
        }

        //write error(s)
        errorString += separator;
        this.writeErrors(errorString.getBytes());

        errorString = "";
        return true;
    }

    private void writeFormula(byte[] byteContent) throws IOException {
        this.write(byteContent);
    }

    private void writeErrors(byte[] byteContent) throws IOException {
        this.write(byteContent);
        this.flush();
        this.close();
    }

}
