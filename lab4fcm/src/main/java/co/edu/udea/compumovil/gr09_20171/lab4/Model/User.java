package co.edu.udea.compumovil.gr09_20171.lab4.Model;
import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by julian on 3/05/17.
 */

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public String age;
    public String photoUrl;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, String age, String photoUrl) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.photoUrl = photoUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
