package output;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by hu on 2014/10/19 0019.
 */
public class OutputToFile {
    public static boolean out(String filePath, String content, Boolean isAppend){
        try {
            File file = new File(filePath);
            FileOutputStream out = new FileOutputStream(file, isAppend);
            out.write(content.getBytes());
            out.write("\r\n".getBytes());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
