package ulab.edu.coronainfoupdate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    public static TextView death;
    public  static TextView infected;
    public static TextView recovered;
    public static TextView countryName;
    public static TextView TotalDeath;
    public static TextView Critical;
    public static TextView TotalCase;
    private static final int REQUEST_CALL=1;

    EditText getUrl;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        death = (TextView) findViewById(R.id.deathID);
        getUrl = (EditText)findViewById(R.id.SearchCountry);
        infected = (TextView)findViewById(R.id.infectedID);
        recovered = (TextView)findViewById(R.id.RecoveryID);
        countryName = (TextView)findViewById(R.id.CountryName);
        TotalDeath = (TextView)findViewById(R.id.totalDeath);
        Critical = (TextView)findViewById(R.id.CriticalCase);
        TotalCase =(TextView)findViewById(R.id.TotalCase);

    }
    public void onClick(View view) {


        url = getUrl.getText().toString();
        if(url.length()==0){
            death.setText("0");
            infected.setText("0");
            recovered.setText("0");
            TotalDeath.setText("0");
            TotalCase.setText("0");
            Critical.setText("0");
            Toast.makeText(this, "Please Enter A Country Name", Toast.LENGTH_SHORT).show();
        }else {

            fetchData data = new fetchData(url);
            data.execute();
        }



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
        }
    }

    public void callNumber(View view) {
        makePhoneCall();
    }
    public void makePhoneCall(){
        String number;
        number = "333";

        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }else{
            String dial = "tel:"+number;
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }
    }
}