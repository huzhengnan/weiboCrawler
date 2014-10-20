package crawler;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Page;

import java.io.IOException;
import java.util.regex.Pattern;

public class ZhihuCrawler extends BreadthCrawler {

    /**
     * This function is called when a page is fetched and
     * ready to be processed by your program.       
     */
    @Override
    public void visit(Page page) {
        String question_regex="^http://www.zhihu.com/question/[0-9]+";
        if(Pattern.matches(question_regex, page.getUrl())){
            System.out.println("processing "+page.getUrl());

            /*extract title of the page*/
            String title=page.getDoc().title();
            System.out.println(title);

            /*extract the content of question*/
            String question=page.getDoc().select("div[id=zh-question-detail]").text();
            System.out.println(question);

        }
    }

    /**
     * start crawling
     */
    public static void main(String[] args) throws IOException {
        ZhihuCrawler crawler=new ZhihuCrawler();
        crawler.addSeed("http://www.zhihu.com/question/21003086");
        crawler.addRegex("http://www.zhihu.com/.*");
        /*start the crawler with depth=5*/
        try{
            crawler.start(5);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}