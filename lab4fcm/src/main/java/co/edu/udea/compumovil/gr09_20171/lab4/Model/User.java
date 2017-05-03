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
    public int age;
    public Uri photoUrl;
    public String uId;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
