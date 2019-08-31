package android.pi4home.jg.com.pi4homeandroid;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    TextView infoTextView;

    @ViewById
    Button upButton;

    @ViewById
    Button stopButton;

    @ViewById
    Button downButton;

    @AfterInject
    void afterViews() {

        Pi4HomeManager.sharedInstance().callHomeShutter(new Pi4HomeManager.RestCallback() {
            @Override
            public void finsihed(boolean successful, String result) {
                infoTextView.setText(result);
            }
        });
    }

    @Click(R.id.upButton)
    void upButtonClicked() {
        Pi4HomeManager.sharedInstance().callOpenShutter(new Pi4HomeManager.RestCallback() {
            @Override
            public void finsihed(boolean successful, String result) {
                infoTextView.setText(result);
            }
        });
    }

    @Click(R.id.stopButton)
    void stopButtonClicked() {
        Pi4HomeManager.sharedInstance().callStopShutter(new Pi4HomeManager.RestCallback() {
            @Override
            public void finsihed(boolean successful, String result) {
                infoTextView.setText(result);
            }
        });
    }

    @Click(R.id.downButton)
    void downButtonClicked() {
        Pi4HomeManager.sharedInstance().callCloseShutter(new Pi4HomeManager.RestCallback() {
            @Override
            public void finsihed(boolean successful, String result) {
                infoTextView.setText(result);
            }
        });
    }
}

