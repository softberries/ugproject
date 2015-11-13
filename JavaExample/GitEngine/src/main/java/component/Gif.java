package component;

import java.util.*;

/**
 * Created by Maciek on 2015-11-13.
 */
public class Gif {

    List <String> urls = new ArrayList();
    String name;
    int number=1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
