package lachongmedia.vn.demodiemdanh.databases.model;

/**
 * Created by tranh on 5/10/2017.
 */

public class Student {
    private String id;
    private String name;
    private String avatar;
    private String nameParent;
    private String phonenumber;
    private String urlParent;
    private boolean isHereNow;

    public Student(String id, String name, String avatar, String nameParent, String phonenumber, String nameClass, String gvcn, String urlParent) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.nameParent = nameParent;
        this.phonenumber = phonenumber;
        this.urlParent = urlParent;
        this.nameClass = nameClass;
        this.gvcn = gvcn;
        this.isHereNow=false;
    }

    public boolean isHereNow() {
        return isHereNow;
    }

    public void setHereNow(boolean hereNow) {
        isHereNow = hereNow;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNameParent() {
        return nameParent;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getNameClass() {
        return nameClass;
    }

    public String getGvcn() {
        return gvcn;
    }

    private String nameClass;
    private String gvcn;

    public String getUrlParent() {
        return urlParent;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nameParent='" + nameParent + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", nameClass='" + nameClass + '\'' +
                ", gvcn='" + gvcn + '\'' +
                '}';
    }
}
