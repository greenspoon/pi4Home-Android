package android.pi4home.jg.com.pi4homeandroid;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by josefgrinspun on 15.04.18.
 */

//@Rest(rootUrl = "http://192.168.2.108:8989", converters = {MappingJackson2HttpMessageConverter.class})


public interface pi4HomeRestClient {

    @GET("/")
    Call<String> getHomeText();

    @GET("/open")
    Call<String> openShutter();

    @GET("/close")
    Call<String> closeShutter();

    @GET("/stop")
    Call<String> stopShutter();
}
