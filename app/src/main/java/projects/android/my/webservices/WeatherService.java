package projects.android.my.webservices;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by User on 27-11-2017.
 */

public class WeatherService extends AsyncTask
{
    Activity activity;

    public WeatherService(Activity activity)
    {
        this.activity=activity;
    }
    StringBuilder builder = new StringBuilder();
    @Override
    protected Object doInBackground(Object[] params)
    {

        Log.i("URL",params[0].toString());
        try
        {
            URL url = new URL(params[0].toString());
            URLConnection con =  url.openConnection();
            con.connect();
            InputStream stream = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String temp="";
            while ((temp = reader.readLine())!=null)
            {
                builder.append(temp);
            }
            Log.i("Service Data",builder.toString());
        }
        catch (Exception ex)
        {
            Log.e("ConnectionError","Error in connecting");
            Log.e("Error",ex.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
        try
        {
            JSONObject object = new JSONObject(builder.toString());

            TextView city = (TextView) activity.findViewById(R.id.txtCity);
            TextView city_weather = (TextView) activity.findViewById(R.id.txtWeather);
            TextView desc = (TextView) activity.findViewById(R.id.txtDesc);
            TextView temp_min = (TextView) activity.findViewById(R.id.txtTempMin);
            TextView temp_max = (TextView) activity.findViewById(R.id.txtTempMax);

            city.setText("City :"+object.getString("name"));

            JSONArray weather = object.getJSONArray("weather");

            for(int i=0;i<weather.length();i++)
            {
               city_weather.setText("Weather :"+weather.getJSONObject(i).getString("main"));
                desc.setText(weather.getJSONObject(i).getString("description"));
            }

            JSONObject main = object.getJSONObject("main");
            temp_min.setText("Min "+main.getString("temp_min"));
            temp_max.setText("Max "+main.getString("temp_max"));







        }
        catch (Exception ex)
        {

        }
    }
}
