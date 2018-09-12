package cn.connie.crawler.core.controller;

import cn.connie.crawler.core.crawler.CoininformationCrawier;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.apache.http.message.BasicHeader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CoininformationController {


    public static void main(String[] args) throws Exception {


        String rootFolder = "/Users/hinge/Desktop/crawler"; // 定义爬虫数据存储位置
        int numberOfCrawlers = 1; // 定义7个爬虫，也就是7个线程

        CrawlConfig config = new CrawlConfig(); // 实例化爬虫配置

        config.setCrawlStorageFolder(rootFolder); // 设置爬虫文件存储位置
        HashSet<BasicHeader> collections = new HashSet<>();
        collections.add(new BasicHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3192.0 Safari/537.36"));
        collections.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"));
        collections.add(new BasicHeader("Accept-Encoding", "gzip,deflate,sdch"));
        collections.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6"));
        collections.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8"));
        collections.add(new BasicHeader("Connection", "keep-alive"));
        config.setDefaultHeaders(collections);
        /*
         * 设置允许爬取二进制文件
         * 因为图片属于二进制文件
         */
        config.setIncludeBinaryContentInCrawling(true);

        String[] crawlDomains = {"https://www.feixiaohao.com/"};

        /*
         * 实例化爬虫控制器
         */
        PageFetcher pageFetcher = new PageFetcher(config); // 实例化页面获取器
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); // 实例化爬虫机器人配置 比如可以设置 user-agent

        // 实例化爬虫机器人对目标服务器的配置，每个网站都有一个robots.txt文件 规定了该网站哪些页面可以爬，哪些页面禁止爬，该类是对robots.txt规范的实现
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
//        List<String> data = CsvUtils.readCsv("COININFO");
        List<String> data = Arrays.asList(crawlDomains);

//        String url = "https://www.feixiaohao.com/coindetails/" + lines[0];
        controller.addSeed("https://www.8btc.com/article/268792");
//        for (int i = 0; i < data.size(); i++) {
//
//
//            String line = data.get(i);
//
//            String[] lines = line.split(",");
//            // 实例化爬虫控制器
//
//        /*
//         * 配置爬虫种子页面，就是规定的从哪里开始爬，可以配置多个种子页面
//         */
//
//            String url = "https://www.feixiaohao.com/coindetails/" + lines[0];
//
//
//
//
//
//        /*
//         * 启动爬虫，爬虫从此刻开始执行爬虫任务，根据以上配置
//         */
//
//
//        }

        controller.start(CoininformationCrawier.class, numberOfCrawlers);
    }
}
