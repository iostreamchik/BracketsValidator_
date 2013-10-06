import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Help to read info from file
 */
public class MyFileReader extends FileInputStream {

    private String content = "";

    public MyFileReader(String name) throws FileNotFoundException {
        super(name);
    }

    /**
     * Function to read file content<p>
     * It open a <b>file</b>, which set in object instance<p>
     * example: <code>MyFileReader reader = new MyFileReader(<b>you_file</b>);</code>
     * @throws java.io.IOException
     */
    public String readContent() throws IOException {

//        BufferedReader bufRead = new BufferedReader(new InputStreamReader(this, "Cp1251"));
//        String tmp = "";
//        while ((tmp = bufRead.readLine()) != null)
//            content += new String(tmp.getBytes("UTF-8"));

        int counter;
        do {
            counter = this.read();
            if (counter != -1) {
                content += (char) counter;
            }
        } while (counter != -1);
        return content;
    }

}
