package co.edu.udea.compumovil.gr09_20171.lab4.Model;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by julian on 3/05/17.
 */
@IgnoreExtraProperties
public class PostEvent {
    public String uId;
    public String author;
    public String title;
    public String fecha;
    public int score=0;
    public String location;
    public String info;
    public Uri photo;

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
}
