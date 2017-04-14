package co.edu.com.compumovil.gr09_20171.lab3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import java.io.FileOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import co.edu.com.compumovil.gr09_20171.lab3.POJO.Usuario;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by julian on 13/04/17.
 */
@SuppressWarnings("serial")
public class InfoUsuario implements Serializable {

    private final String url = "http://192.168.1.7:3000/api";
    private String username;
    private String password;
    private String email;
    private String age;
    private String photo;
    private boolean exist;
    private FileOutputStream outputStream;
    SharedPreferences sf;
    private final String preference = "pref";
    private final String keysave = "ultimoU";


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public void getToDB(final String u) {
        //making object of RestAdapter
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(url).build();

        //Creating Rest Services
        RestInterface restInterface = adapter.create(RestInterface.class);
        restInterface.dataUser(u, new Callback<Usuario>() {
            @Override
            public void success(Usuario usuario, Response response) {
                exist=true;
                username = usuario.getUsername();
                password = usuario.getPass();
                email = usuario.getEmail();
                age = usuario.getAge();
                photo = usuario.getPhoto();

            }

            @Override
            public void failure(RetrofitError error) {
exist=false;
            }
        });
    }


}


