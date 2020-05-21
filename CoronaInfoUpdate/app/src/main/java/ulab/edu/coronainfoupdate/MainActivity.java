package ulab.edu.coronainfoupdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.daandtu.webscraper.Element;
import com.daandtu.webscraper.WebScraper;

public class MainActivity extends AppCompatActivity {
    EditText searchCountry;
    TextView infectedData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, "It Works", Toast.LENGTH_SHORT).show();


    }

    public void searchCountry(String CountryName){



    }



    public void sayHi(View view) {
      ConstraintLayout layout = new ConstraintLayout(this);

        searchCountry = (EditText)findViewById(R.id.SearchCountry);
        final String CountryName = searchCountry.getText().toString();
        searchCountry(CountryName);
        infectedData = (TextView)findViewById(R.id.infectedID);
        final WebScraper webScraper = new WebScraper(this);
        webScraper.setUserAgentToDesktop(true);
        webScraper.setLoadImages(false);
        webScraper.loadURL("https://www.worldometers.info/coronavirus/?utm_campaign=homeAdUOA?Si");
        layout.addView(webScraper.getView());
        webScraper.setOnPageLoadedListener(new WebScraper.onPageLoadedListener() {
            @Override
            public void loaded(String URL) {
                //Element searchBar = webScraper.findElementByXpath("//*[@id=\"main_table_countries_today_filter\"]/label/input");
                //searchBar.setText(CountryName);
                Element getInfo = webScraper.findElementByXpath("//*[@id=\"maincounter-wrap\"]/div/span");
                String infected = getInfo.getText();
                Toast.makeText(MainActivity.this, infected, Toast.LENGTH_SHORT).show();

            }
        });





    }
}