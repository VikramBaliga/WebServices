package projects.android.my.webservices;

import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String url = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(CheckInternetConnectivity())
        {
            Toast.makeText(this,"Internet Connected",Toast.LENGTH_LONG).show();
            WeatherService weatherTask = new WeatherService(MainActivity.this);
            weatherTask.execute(url);
        }
        else
        {
            Toast.makeText(this,"Internet Not Connected",Toast.LENGTH_LONG).show();
        }
    }

    private boolean CheckInternetConnectivity()
    {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo().isConnected();
    }
}
