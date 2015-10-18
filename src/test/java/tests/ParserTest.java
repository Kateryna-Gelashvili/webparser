package tests;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.k.ParserService;
import org.k.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kateryna on 07.08.2015.
 */
public class ParserTest{

    @Test
    public void testOfParser(){
        ArrayList<Product> list = new ArrayList<>();
        try {
            Document doc =
                    Jsoup.parse(new File(ParserTest.class.getClassLoader().getResource("test.html").getFile()),null);
            ParserService.parseEveryPage(list,doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(list.size()==32);
    }
}
