package lachongmedia.vn.demodiemdanh.databases.model;

/**
 * Created by tranh on 5/27/2017.
 */

public class Teacher {
    private String id;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Teacher(String id, String name, String url) {

        this.id = id;
        this.name = name;
        this.url = url;

    }
}
