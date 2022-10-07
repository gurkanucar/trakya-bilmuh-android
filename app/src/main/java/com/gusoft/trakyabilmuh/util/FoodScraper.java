package com.gusoft.trakyabilmuh.util;

import android.util.Log;

import org.apache.commons.text.StringEscapeUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FoodScraper {
    public static List<String> getFoodList() {
        String urlAddress = "https://www.trakya.edu.tr/yemeklistesi";
        try {
            URL url = new URL(urlAddress);
            InputStream is = url.openStream();
            int ptr = 0;
            StringBuffer buffer = new StringBuffer();
            while ((ptr = is.read()) != -1) {
                buffer.append((char) ptr);
            }
            is.close();
            String temp = StringEscapeUtils.unescapeHtml4(buffer.toString().split("font-weight-600 font-16")[1]);
            String[] tempFoods = temp.split("</div>")[1]
                    .split("<span class=\"font-10 text-theme-color-2 text-nowrap\">")[0]
                    .replace(" </span> <span class=\"text-theme-color-2\">|</span>", "")
                    .split("<span class=\"text-nowrap\">");
            // String kal = temp.split("</div>")[1].split("<span class=\"font-10 text-theme-color-2 text-nowrap\">")[1];
            List<String> foods = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                foods = Arrays.stream(tempFoods).map(x -> x.replaceAll("\\s+$", ""))
                        .collect(Collectors.toList());
            }
            foods.remove(0);
            return foods;
            // foods.forEach(System.out::println);
        } catch (Exception e) {
            Log.e("FOOD Scraper", "getFoodList: " + e);
            return null;
        }
    }
}
