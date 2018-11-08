package com.mad.yairn.madcardswipe;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetProfiles extends AsyncTask<Void, Void, ArrayList<Profile>> {

    private  GetProfilesCallback callback;

    public GetProfiles(GetProfilesCallback callback){
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Profile> doInBackground(Void... arg0) {

        ArrayList<Profile> profiles = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL("https://raw.githubusercontent.com/txcsmad/f18-android/master/instructor/MAD.json");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                sb.append(line);
                line = bufferedReader.readLine();
            }

            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonArrayProfiles = jsonObject.getJSONArray("Profiles");
            JSONObject jsonObjectProfile;

            for(int index = 0; index < jsonArrayProfiles.length(); index++) {
                jsonObjectProfile = jsonArrayProfiles.getJSONObject(index);

                profiles.add(new Profile(jsonObjectProfile.getString("Name"), jsonObjectProfile.getString("Picture"),
                        jsonObjectProfile.getString("Position")));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return profiles;
    }

    @Override
    protected void onPostExecute(ArrayList<Profile> profiles) {
        super.onPostExecute(profiles);

        callback.onBackgroundCallComplete(profiles);
    }


    public interface GetProfilesCallback {
        public void onBackgroundCallComplete(ArrayList<Profile> profiles);
    }

}
