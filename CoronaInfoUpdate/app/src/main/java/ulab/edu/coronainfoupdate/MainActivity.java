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
    }
    public void onClick(View view) {


        url = getUrl.getText().toString();
        if(url.length()==0){
            death.setText("0");
            infected.setText("0");
            recovered.setText("0");
            TotalDeath.setText("0");
            TotalCase.setText("0");
            TotalActiveCase.setText("0");
            TotalTest.setText("0");
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