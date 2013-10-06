import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Main class
 */
public class Main {
    private static final String separator = System.getProperty("line.separator");
    private static MyFileReader reader;
    private static MyFileWriter writer;
    private static LinkedList errors;
    private static String in;
    private static String out;


    public static void main(String[] args) {
        String content = "";
        in = args[0];
        out = args[1];

        //Open file and get formulas
        try {
            reader = new MyFileReader(in);
            content = reader.readContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bracket parentheses = new Parentheses('(', ')');

        String formula = null;
        for (int i = 0 ; ; i = content.indexOf(separator, i) + separator.length()) {
            try {
                //get row(formula) from content
                formula = content.substring(i, content.indexOf(separator, i));

                //validate formula on errors
                errors = parentheses.validate(formula);

                //if formula contains error(s) - make write to out file
                if (errors != null)
                    createWriter();
                else continue;

                //check correctly writing
                try {
                    if (!writer.writeContent(formula, errors))
                        System.out.println("Can`t write to file");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IndexOutOfBoundsException ex) {
                //when last row not contains separator we getting he in exception
                formula = content.substring(i, content.length());

                //validate formula on errors
                errors = parentheses.validate(content.substring(i, content.length()));

                //if formula contains error(s) - make write to out file
                if (errors != null)
                    createWriter();
                else break;

                //check correctly writing
                try {
                    if (!writer.writeContent(formula, errors))
                        System.out.println("Can`t write to file");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            errors.clear();
        }
    }

    private static void createWriter() {
        try {
            writer = new MyFileWriter(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
