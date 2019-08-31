package android.pi4home.jg.com.pi4homeandroid;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by josefgrinspun on 15.04.18.
 */

public class Pi4HomeManager {

    private static Pi4HomeManager _sharedInstance;
    private pi4HomeRestClient restClient;

    private Pi4HomeManager() {
        Retrofit.Builder retroBuilder = new Retrofit
                .Builder()
                .baseUrl("http://192.168.2.108:8989")
                .addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit = retroBuilder.build();
        restClient = retrofit.create(pi4HomeRestClient.class);
    }

    static Pi4HomeManager sharedInstance() {
        if (_sharedInstance == null) {
            _sharedInstance = new Pi4HomeManager();
        }
        return _sharedInstance;
    }

    public void callHomeShutter(final RestCallback callback) {
        restClient.getHomeText().enqueue(new CallbackWrapper<String>(callback));
    }

    public void callOpenShutter(final RestCallback callback) {
        restClient.openShutter().enqueue(new CallbackWrapper<String>(callback));
    }

    public void callCloseShutter(final RestCallback callback) {
        restClient.closeShutter().enqueue(new CallbackWrapper<String>(callback));
    }

    public void callStopShutter(final RestCallback callback) {
        restClient.stopShutter().enqueue(new CallbackWrapper<String>(callback));
    }

    class CallbackWrapper<T> implements Callback<T> {

        private RestCallback restCallback;

        public CallbackWrapper(RestCallback restCallback) {
            this.restCallback = restCallback;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            this.restCallback.finsihed(true,response.body().toString());
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            this.restCallback.finsihed(false,t.getMessage());
        }
    }

    public interface RestCallback{
        void finsihed(boolean successful,String result);
    }
}
