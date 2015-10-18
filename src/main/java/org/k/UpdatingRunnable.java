package org.k;

import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Kateryna on 07.08.2015.
 */
public class UpdatingRunnable implements Runnable {
    final static Logger logger = Logger.getLogger(DataBaseService.class);

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            ArrayList<Product> listOfLapTopsRozetka = ParserService.getLapTopsFromRozetka();
            DataBaseService.updateListOfLaptopsInDatabase(listOfLapTopsRozetka);
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
