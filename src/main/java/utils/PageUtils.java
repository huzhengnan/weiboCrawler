package utils;

import constants.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hu on 2014/10/20 0020.
 */
public class PageUtils {
    public static String getPageCount(String username)  {
        try {
            String userPath = URLTranslate.translateToUserWeiboPath(username);
            Document document = Jsoup.parse(new URL(userPath + Constants.ONE_STRIG), 1000);
            System.out.println(document.title());
            Elements elements = document.getElementsByAttributeValue("name", "mp");
            for (Element element: elements){
                System.out.println(element.attr("value"));
            }
            return "";
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String args[]){
        getPageCount("zhangjingchu");
    }
}
