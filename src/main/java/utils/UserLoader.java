package utils;

import constants.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hu on 2014/10/20 0020.
 */
public class UserLoader {
    public static List<String> getUsernames() {
        List<String> usernames = new ArrayList<String>();
        try {
            InputStream in = new FileInputStream(new File(ConfigurationLoader.getInstance().getValue(Constants.USER_INPUT_PATH_STRING)));
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            String username = "";
            while ((username = br.readLine()) != null) {
                System.out.println(username);
                usernames.add(username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usernames;
    }
}
