package csci490.gitreference;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    ArrayList<HashMap<String,String>> gitReferenceList;

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

        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject json_data = jsonArray.getJSONObject(i);
            commands.id =  json_data.getString("id_key");
            commands.name = json_data.getString("name_key");
            c.thumb = json_data.getString("thumb_key");
            imgnfo.info = json_data.getString("info_key");
            myArray.add(new image_data());
        }


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

    /*
    JSONArray jsonObj = new JSONArray(jsonStr);

for (int i = 0; i < jsonObj.length(); i++) {
    JSONObject m = jsonObj.getJSONObject(i);
    String command = m.getString(TAG_COMMAND);
    String name = m.getString(TAG_NAME);
    String price = m.getString(TAG_PRICE);

    HashMap < String, String > contact = new HashMap < String, String > ();
    contact.put(TAG_CHANGE, change);
    contact.put(TAG_NAME, name);
    contact.put(TAG_PRICE, price);

    contactList.add(contact);
}
     */
}
