package ulab.edu.coronainfoupdate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;

import com.hbb20.CountryCodePicker;


public class MainActivity extends AppCompatActivity {
    public static TextView death;
    public  static TextView infected;
    public static TextView recovered;
    public static TextView countryName;
    public static TextView TotalDeath;
    public static TextView TotalActiveCase;
    public static TextView TotalCase;
    private static final int REQUEST_CALL=1;
    public static TextView TotalTest;
    public static CountryCodePicker ccp;
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
        TotalActiveCase = (TextView)findViewById(R.id.TotalActiveCase);
        TotalCase =(TextView)findViewById(R.id.TotalCase);
        TotalTest = (TextView)findViewById(R.id.TotalTest);
        ccp = (CountryCodePicker)findViewById(R.id.ccp);
        url = ccp.getSelectedCountryName().toString();
        fetchData data = new fetchData(url);
        data.execute();
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                url = ccp.getSelectedCountryName().toString();
                if(url.contains("UAE")){
                    url = "UAE";
                    Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
                }
                if(url.equals("United States")){
                    url = "USA";
                    Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
                } if(url.equals("United Kingdom")){
                    url = "UK";
                    Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
                }
                fetchData data = new fetchData(url);
                data.execute();


            }
        });
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