package csci490.gitreference;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private boolean isFiltered = false;
    private ArrayList<Command> commands;
    private CommandAdapter fAdapter;
    private InputStream is;
    private String jsonString;
    private String filtered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        try {
            is = getApplicationContext().getAssets().open("gitReference.json");
            jsonString = JsonUtils.parseJsonToString(is);
        }
        catch(Exception e) {

        }

        commands = JsonUtils.populateGitReferences(jsonString);

        final CommandAdapter adapter = new CommandAdapter(this, commands);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                if (isFiltered == false)
                {
                    isFiltered = true;
                    filtered = listView.getItemAtPosition(i).toString();
                    fAdapter = new CommandAdapter(getApplicationContext(), JsonUtils.filterGitReferences(jsonString, filtered));
                    listView.setAdapter(fAdapter);
                    Toast toast = Toast.makeText(getApplicationContext(), "Showing " + filtered + " section", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
                
                else
                {
                    isFiltered = false;
                    listView.setAdapter(adapter);
                    Toast toast = Toast.makeText(getApplicationContext(), "Unfiltered", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
            }
        });

    }
}
