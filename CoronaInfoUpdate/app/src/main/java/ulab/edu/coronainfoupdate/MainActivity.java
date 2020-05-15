package ulab.edu.coronainfoupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView infected = (TextView)findViewById(R.id.infectedID);
    TextView death = (TextView)findViewById(R.id.deathID);
    TextView recovered = (TextView)findViewById(R.id.recoveredID);
    SearchView searchView = (SearchView)findViewById(R.id.searchView);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
