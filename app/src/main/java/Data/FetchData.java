package Data;

import android.content.res.AssetManager;
import android.util.Log;

import com.example.garethlye.uitest1.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by garethlye on 13/02/2017.
 **/

public class FetchData {

    private MainActivity mMainActivity;
    private       String[][] countryList;

    public FetchData(MainActivity mainActivity) {
        mMainActivity = mainActivity;
        setup();
    }

    public String getJSON() {
        String data = "";

        try {
            AssetManager assetManager = mMainActivity.getAssets();
            InputStream in = assetManager.open("KfitData.json");
            InputStreamReader isr = new InputStreamReader(in);
            char[] inputBuffer = new char[100];

            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                data += readString;
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return data;
    }

    public void setup() {
        String str = getJSON();
        String finalData[][] = new String[3][8];
        try {
            JSONObject json = new JSONObject(str);
            JSONArray array = json.getJSONArray("countries");

            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject main = array.getJSONObject(i);
                    finalData[i][0] = main.getString("name");
                    JSONArray jsonArray = main.getJSONArray("cities");

                    for (int j = 0; j <= jsonArray.length(); j++) {
                        try {
                            JSONObject jObject = main.getJSONArray("cities").getJSONObject(j);
                            finalData[i][j+1] = jObject.getString("name");
                        } catch (Exception e) {
                            Log.e("FAIL :(", "failed to parse first json :(");
                        }
                    }
                } catch (Exception e) {
                    Log.e("FAIL :(", "failed to parse json :(");
                }
            }

        } catch (Exception e) {
            Log.e("parsing countryList", "Failed :(");
        }
        countryList = finalData;
    }

    public String[][] getCountryList() {
        return countryList;
    }

}
