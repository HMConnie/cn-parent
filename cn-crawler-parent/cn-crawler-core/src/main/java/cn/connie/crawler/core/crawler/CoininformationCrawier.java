package cn.connie.crawler.core.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;

import java.util.regex.Pattern;

public class CoininformationCrawier extends WebCrawler {


    /*
     * 指定文件后缀过滤
     */
    private static final Pattern filters = Pattern
            .compile(".*(\\.(css|js|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    /*
     * 正则匹配图片文件
     */
    private static final Pattern imgPatterns = Pattern.compile(".*(\\.(bmp|gif|jpe?g|png|tiff?))$");

    private static String FilestorageFolder = "/Users/hal9000/Desktop/coindescdesc/";// 爬取的图片本地存储地址
    private static String[] crawlDomains; // 指定要爬取的域名



    /**
     * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
     * 第一个参数referringPage封装了当前爬取的页面信息
     * 第二个参数url封装了当前爬取的页面url信息
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase(); // 得到小写的url
        if (imgPatterns.matcher(href).matches() && href.contains("/coindetails/")) { // 匹配指定图片后缀文件
            return true;
        }
        return false;
    }

    /**
     * 当我们爬到我们需要的页面，这个方法会被调用，我们可以尽情的处理这个页面
     * page参数封装了所有页面信息
     */
    @Override
    public void visit(Page page) {
        // 定义存储文件
        HtmlParseData a = (HtmlParseData) page.getParseData();

        String context = Jsoup.parse(a.getHtml()).select("p").get(1).toString().replace("<p>", "").replace("</p>", "");

        String releasedate = Jsoup.parse(a.getHtml()).select("span.text").get(3).childNode(0).toString();
        String weburl = Jsoup.parse(a.getHtml()).select("span.text").get(5).childNode(1).attributes().get("href");
        if (weburl.contains("--")) {
            weburl = "";
        }
        if (releasedate.contains("--")) {
            releasedate = "00-00-00 00:00:00";
        }
        System.out.println("page = [" + context + "]");
        System.out.println("page = [" + releasedate + "]");
        System.out.println("page = [" + weburl + "]");


    }


}
