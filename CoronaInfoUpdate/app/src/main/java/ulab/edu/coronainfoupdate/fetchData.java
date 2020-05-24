package ulab.edu.coronainfoupdate;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void,Void,Void>{
    String thisUrl = "";
    String wholeJsonFile = "";
    String death = "";
    String infected = "";
    String recovered = "";
    String Country= "";
    String TotalDeath = "";
    String Critical = "";
    String TotalCase = "";
    fetchData(String url){
        thisUrl = " https://coronavirus-19-api.herokuapp.com/countries/"+url;
        //System.out.println(thisUrl);

    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL(thisUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line!=null){
                line = bufferedReader.readLine();
                wholeJsonFile = wholeJsonFile+line;
            }

            JSONObject JA = new JSONObject(wholeJsonFile);
            death = JA.getString("todayDeaths");
            infected = JA.getString("todayCases");
            recovered = JA.getString("recovered");
            Country = JA.getString("country");
            TotalDeath = JA.getString("deaths");
            Critical = JA.getString("critical");
            TotalCase = JA.getString("cases");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MainActivity.death.setText(this.death);
        MainActivity.infected.setText(this.infected);
        MainActivity.recovered.setText(this.recovered);
        MainActivity.countryName.setText(this.Country);
        MainActivity.TotalDeath.setText(this.TotalDeath);
        MainActivity.Critical.setText(this.Critical);
        MainActivity.TotalCase.setText(this.TotalCase);
    }

}
