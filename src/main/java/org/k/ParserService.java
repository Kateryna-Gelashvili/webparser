package org.k;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Kateryna on 30.07.2015.
 */
public abstract class ParserService {
    final static Logger logger = Logger.getLogger(DataBaseService.class);

    public static ArrayList<Product> getLapTopsFromRozetka(){
        ArrayList<Product> listOfLapTops = new ArrayList<Product>();
        try {
            Document doc = Jsoup.connect("http://rozetka.com.ua/notebooks/c80004/filter/").timeout(0).get();
            parseWebSite(listOfLapTops,doc);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return listOfLapTops;
    }

    public static void parseWebSite(ArrayList<Product> listOfLapTops, Document doc) {
            Elements pages = doc.getElementsByClass("paginator-catalog").first().children().first().children();
            Element lastPage = pages.last();
            String stringAmountOfPages = lastPage.children().first().ownText();
            int amountOfPages = Integer.parseInt(stringAmountOfPages);

            for (int i = 1; i <= amountOfPages; i++) {
                try {
                    Document docPage =
                            Jsoup.connect("http://rozetka.com.ua/notebooks/c80004/filter/page=" + i + "/").timeout(0).get();
                    parseEveryPage(listOfLapTops,docPage);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }


    public static void parseEveryPage(ArrayList<Product> listOfLapTops, Document docPage){
        Elements allElements = docPage.getElementsByClass("g-i-tile-i-box-desc");
        for (Element element : allElements) {

            Product lapTop = new Product();
            lapTop.setWebsite("rozetka.com.ua");

            String name = element.select("a[href]").text().split("\\)")[0] + ")";
            lapTop.setName(name);

            Elements details = element.getElementsByClass("g-price-uah");
            if (details != null & details.size() > 0) {
                Element detail = details.first();
                String stringPrice = detail.ownText();
                stringPrice = stringPrice.replaceAll("[^0-9,]+", "");
                BigDecimal price = new  BigDecimal(stringPrice);
                lapTop.setPrice(price);

                String currency = detail.children().first().text();
                lapTop.setCurrency(Currency.getCurrencyByName(currency));

            }

            listOfLapTops.add(lapTop);
        }
    }
    }





