package crawler;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.util.Config;
import constants.Constants;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import output.OutputToFile;
import utils.ConfigurationLoader;
import utils.URLTranslate;
import utils.UserLoader;

import java.io.File;
import java.util.List;

public class WeiboCrawler extends BreadthCrawler {

    private static String filePath;

    public WeiboCrawler() {
        setUseragent("Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0");
        setCookie("gsid_CTandWM=4uu7e5021NV8ybjvwefczgoLc7M;");
    }

    @Override
    public void visit(Page page) {
        Elements divs = page.getDoc().select("div.c");
//        for (Element div : divs) {
//            System.out.println(div.text());
//        }
        String username = page.getUrl().substring(page.getUrl().lastIndexOf('/') + 1, page.getUrl().lastIndexOf('?'));
        Elements as = page.getDoc().getElementsByTag("a");
        for (Element a : as) {
            if ("原图".equals(a.text())) {
                String href = a.attr(Constants.HREF_STRING);
                String downloadPath = URLTranslate.translateToDownloadURL(href);
                OutputToFile.out(ConfigurationLoader.getInstance().getValue(Constants.OUTPUT_PATH_STRING) + username + Constants.TXT_SUFFIX, downloadPath, true);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Config.topN = 0;
        WeiboCrawler crawler = new WeiboCrawler();
        List<String> usernames = UserLoader.getUsernames();
        for (String username : usernames) {
            String userPath = URLTranslate.translateToUserWeiboPath(username);
            filePath = Constants.OUTPUT_PATH_STRING + username + Constants.TXT_SUFFIX;
            File f = new File(ConfigurationLoader.getInstance().getValue(Constants.OUTPUT_PATH_STRING) + username + Constants.TXT_SUFFIX);
            f.createNewFile();
            for (int i = 1; i <= 100; i++) {
                //  crawler.addSeed("http://weibo.cn/zhangjingchu?page=" + i);
                crawler.addSeed(userPath + i);
            }
        }
        crawler.addRegex(".*");
        crawler.setThreads(2);
        crawler.start(1);
    }
}