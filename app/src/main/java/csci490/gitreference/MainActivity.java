package csci490.gitreference;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String jsonData = bundle.getString("gitReference");

        listView = (ListView) findViewById(R.id.listView);

        //ArrayList<Command> commands = loadJSONFromAsset(this);
        //String json = loadJSONFromAsset();

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Command> commands = new ArrayList<>();


        //JSONObject obj = new JSONObject(json);

        CommandAdapter adapter = new CommandAdapter(this, commands);
        listView.setAdapter(adapter);
    }

    public ArrayList<Command> loadJSONFromAsset(Context context) {
        ArrayList<Command> json = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("gitReference.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public ArrayList<String> populateData(String jsonFileName)
    {
        ArrayList<String> returnList = new ArrayList<>();

        String jsonString = ProcessData(jsonFileName);
        Log.i("JSON", jsonString);

        ArrayList<Command> references = JsonUtils.populateGitReferences(jsonString);

        for(Command g:references)
        {
            returnList.add(g.getCommand());
        }

        return returnList;
    }

    public String ProcessData(String filename)
    {
        String jsonString = "";
        boolean isFilePresent = JsonUtils.isFilePresent(this, filename);

        if(isFilePresent)
        {
            jsonString = JsonUtils.read(this, filename);
            Log.i("JSON", "JSON was present");
        }

        else
        {
            Log.i("JSON", "JSON was not present. Creating...");
            InputStream is = null;
            try {
                is = getApplicationContext().getAssets().open("gitReference.json");
            } catch (IOException e) {
                e.printStackTrace();
            }

            jsonString = JsonUtils.parseJsonToString(is);
            boolean isFileCreated = JsonUtils.create(this, filename, jsonString);

            if(isFileCreated)
                Log.i("JSON", "Created the filesystem JSON");
            else
            {
                Log.e("JSON", "Error creating filesystem JSON");
            }
        }

        return jsonString;
    }

}
