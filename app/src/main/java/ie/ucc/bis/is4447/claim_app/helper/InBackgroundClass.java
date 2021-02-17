package ie.ucc.bis.is4447.claim_app.helper;

// Run connection in Background thread
//https://www.youtube.com/watch?v=ARnLydTCRrE

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InBackgroundClass extends AsyncTask<String, Void, String> {


    Context ctx;



    public InBackgroundClass(Context ct){
        ctx = ct;
    }
    @Override
    protected String doInBackground(String... strings) {

        String WebURL = strings[0];
        InputStream inputStream;

        try {
            URL myURL = new URL(WebURL);
            HttpURLConnection connection = (HttpURLConnection) myURL.openConnection();

            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.connect();

            inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                builder.append(line + "\n");
            }

            inputStream.close();
            reader.close();

            return builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
