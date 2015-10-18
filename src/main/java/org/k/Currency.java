package org.k;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kateryna on 01.08.2015.
 */
public enum Currency {
    UAH(980), USD(840), EUR(978);

    private int ID;

    private Currency(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public static Currency getCurrencyByName(String name){
        Pattern uahPattern = Pattern.compile(".*грн.*|.*грив.*|.*uah.*|.*UAH.*|.*980.*");
        Matcher uahMatcher = uahPattern.matcher(name);

        Pattern usdPattern = Pattern.compile(".*дол.*|.*dol.*|.*usd.*|.*USD.*|.*840.*");
        Matcher usdMatcher = usdPattern.matcher(name);

        Pattern eurPattern = Pattern.compile(".*евр.*|.*євр.*|.*eur.*|.*EUR.*|.*978.*");
        Matcher eurMatcher = eurPattern.matcher(name);

        if(uahMatcher.matches()){
            return Currency.UAH;
        }
        if(usdMatcher.matches()){
            return Currency.USD;
        }
        if(eurMatcher.matches()){
            return Currency.EUR;
        }
        return null;
    }
}
