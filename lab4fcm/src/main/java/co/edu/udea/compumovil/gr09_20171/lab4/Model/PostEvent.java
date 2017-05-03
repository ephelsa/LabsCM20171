package co.edu.udea.compumovil.gr09_20171.lab4.Model;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by julian on 3/05/17.
 */
@IgnoreExtraProperties
public class PostEvent {
    private String uId;
    private String author;
    private String title;
    private String fecha;
    private int score=0;
    private String location;
    private String info;
    private Uri photo;

    public PostEvent(){

    }

    public PostEvent(String uId, String author, String title, String fecha, String location, String info, Uri photo) {
        this.uId = uId;
        this.author = author;
        this.title = title;
        this.fecha = fecha;
        this.location = location;
        this.info = info;
        this.photo = photo;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
}
