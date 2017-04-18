package co.edu.com.compumovil.gr09_20171.lab3.POJO;

import java.util.List;

import co.edu.com.compumovil.gr09_20171.lab3.POJO.Usuario;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RestInterface {
    @GET("Usuarios/{id}")
    Call<Usuario> getUsuariosT(@Path("id") String u);

    @FormUrlEncoded
    @POST("Usuarios")
    Call<Usuario> newUser(@Field("username") String username,
                          @Field("name") String name,
                          @Field("pass") String pass,
                          @Field("email") String email,
                          @Field("age") String age,
                          @Field("photo") String photo);

    @FormUrlEncoded
    @PUT("Usuarios/{id}")
    Call<Usuario> updateUser(@Path("id") String username,
                          @Field("name") String name,
                          @Field("pass") String pass,
                          @Field("email") String email,
                          @Field("age") String age,
                          @Field("photo") String photo);

    @GET("Eventos")
    Call<List<Evento>> getEvents();


}
