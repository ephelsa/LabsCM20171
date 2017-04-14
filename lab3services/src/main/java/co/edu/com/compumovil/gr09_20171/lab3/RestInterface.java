package co.edu.com.compumovil.gr09_20171.lab3;

import co.edu.com.compumovil.gr09_20171.lab3.POJO.Usuario;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;



public interface RestInterface {

    @GET("/Usuarios/{id}")
    void dataUser(@Path("id") String us, Callback<Usuario> callback);

    @PUT("/Usuarios")
    void newUser(@Field("username") String username,
                 @Field("pass") String pass,
                 @Field("email") String email,
                 @Field("age") String age,
                 @Field("photo") String photo,
                 Callback<Usuario> callback);
}
