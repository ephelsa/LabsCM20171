package co.edu.com.compumovil.gr09_20171.lab3;

import java.util.List;

import co.edu.com.compumovil.gr09_20171.lab3.POJO.Usuario;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;



public interface RestInterface {

    @GET("/Usuarios/{id}")
    void dataUser(@Path("id") String us, Callback<Usuario> callback);

    @FormUrlEncoded
    @POST("/Usuarios")
    void newUser(@Field("username") String username,
                 @Field("name") String name,
                 @Field("pass") String pass,
                 @Field("email") String email,
                 @Field("age") String age,
                 @Field("photo") String photo,
                 Callback<Usuario> callback);

    @FormUrlEncoded
    @PUT("/Usuarios/{id}")
    void updateUser(@Path("id") String username,
                    @Field("name") String name,
                    @Field("pass") String pass,
                    @Field("email") String email,
                    @Field("age") String age,
                    @Field("photo") String photo,
                    Callback<Usuario> callback);


}
