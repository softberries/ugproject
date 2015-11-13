package engine.impl;

import com.google.common.collect.Maps;
import component.Const;
import component.Gif;
import engine.GifEngine;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.*;
/**
 * Created by Maciek on 2015-11-13.
 */
public class GifEngineImpl implements GifEngine {

    String fileUrl;
    Map <String,Gif> fileMap;

    /**
     * zamienic windowsowe "/" na "\"
     * C:/Users/Maciek/Desktop/rozwiazanieProblemu50Android.txt
     * @param fileUrl
     */
    public void zaladujPlik(String fileUrl) {
        this.fileUrl=fileUrl;
    }

    /**
     * zalozenie kodowania pliku UTF-8
     */
    public void wygenerujWynik() throws IOException {
        if(fileUrl==null) throw new RuntimeException("Najpierw zaladuj plik !");
        fileMap = Maps.newHashMap();

        String line = "";
        FileReader fileReader = new FileReader(fileUrl);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        while((line = bufferReader.readLine()) != null){
            if(StringUtils.contains(line, Const.GIF)) dodajGifa(line);
        }
        bufferReader.close();
        fileReader.close();
    }

    /**
     * zwraca wszystkie wyniki
     */
    public void pokazWyniki() {

        List list = new ArrayList(fileMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Gif>>() {
            public int compare(Map.Entry<String, Gif> o1, Map.Entry<String, Gif> o2) {
                return o1.getValue().getNumber()-(o2.getValue().getNumber());

            }
        });

        Iterator<Map.Entry<String, Gif>> entries = fileMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Gif> entry = entries.next();
            System.out.println("***************************");
            System.out.println("name = "+entry.getValue().getName());
            System.out.printf("number of this gif "+entry.getValue().getNumber());
            System.out.println("url: ");
            for(String url : entry.getValue().getUrls()){
                System.out.println("        "+url);
            }
        }
    }



    private void dodajGifa(String line){
        String url = StringUtils.substringBetween(line, Const.GIF_START, Const.GIF);
        String name = StringUtils.substring(url, url.lastIndexOf(Const.DOT));
        if(fileMap.containsKey(name)) uaktualnijGifa(name,url);
        else dodajNowegoGifa(name,url);
    }
    private void uaktualnijGifa(String name, String url){
        Gif gif = fileMap.get(name);
        gif.getUrls().add(url);
        gif.setNumber(gif.getNumber() + 1);
    }
    private void dodajNowegoGifa(String name,String url){
        Gif gif = new Gif();
        gif.setName(name);
        gif.getUrls().add(url);
    }

}
