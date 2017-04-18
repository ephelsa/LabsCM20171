package co.edu.com.compumovil.gr09_20171.lab3.Otros;

import android.os.StrictMode;


import java.io.IOException;
import java.io.Serializable;

import co.edu.com.compumovil.gr09_20171.lab3.POJO.Usuario;
import co.edu.com.compumovil.gr09_20171.lab3.POJO.RestInterface;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by julian on 13/04/17.
 */
@SuppressWarnings("serial")
public class InfoUsuario implements Serializable {
    final String url = "http://192.168.194.78:3000/api/";
    String username;
    String name;
    String password;
    String email;
    String age;
    String photo;

    public InfoUsuario() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
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

    public boolean getBDdata(String u) {
        boolean res = false;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RestInterface restInterface = retrofit.create(RestInterface.class);
        Call<Usuario> call = restInterface.getUsuariosT(u);
        try {

            Usuario data = call.execute().body();
            username = data.getUsername();
            name = data.getName();
            password = data.getPass();
            email = data.getEmail();
            age = data.getAge();
            photo = data.getPhoto();
            res = true;

        } catch (IOException e) {
            e.printStackTrace();
            res = false;
        }

        return res;
    }

    public void updateUser(String u,String n,String e,String a){

    }

}


