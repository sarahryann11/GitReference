package csci490.gitreference;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Sarah on 2/21/2018.
 */

public class JsonUtils {

    public static String parseJsonToString(InputStream is) {
        String json = null;
        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrayList<Command> populateGitReferences(String jsonString) {
        ArrayList<Command> data = new ArrayList<>();

        JSONArray jArray = null;
        try {
            // Load json String into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);

            // Extract all the "command" JSON objects into a JsonArray
            jArray = jsonObject.getJSONArray("commands");

            // Extract json objects from JsonArray and store into ArrayList as class objects
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);
                Command commands = new Command();
                commands.setCommand(json_data.getString("command"));
                commands.setExample(json_data.getString("example"));
                commands.setExplanation(json_data.getString("explanation"));
                commands.setSection(json_data.getString("section"));

                data.add(commands);
            }

        } catch (Exception ex) {

        }

        return data;
    }

    public static ArrayList<Command> filterGitReferences(String jsonString, String f) {
        ArrayList<Command> data = new ArrayList<>();

        JSONArray jArray = null;
        try {
            // Load json String into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);

            // Extract all the "command" JSON objects into a JsonArray
            jArray = jsonObject.getJSONArray("commands");

            // Extract json objects from JsonArray and store into ArrayList as class objects
            // filter the content this time though
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);
                if (json_data.getString("section").compareTo(f) == 0)
                {
                    Command commands = new Command();
                    commands.setCommand(json_data.getString("command"));
                    commands.setExample(json_data.getString("example"));
                    commands.setExplanation(json_data.getString("explanation"));
                    commands.setSection(json_data.getString("section"));

                    data.add(commands);
                }
            }

        } catch (Exception ex) {

        }

        return data;
    }

    public static String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    public static boolean create(Context context, String fileName, String jsonString){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE); // TODO: Your code goes here
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public static boolean append(Context context, String fileName, String jsonString) {
        // TODO: Your code here

        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            byte[] strBytes = jsonString.getBytes();
            fos.write(strBytes);

            fos.close();

        } catch (FileNotFoundException fnfe){
            return false;
        } catch (IOException ioe) {
            return false;
        }
        return true;
    }

    public static boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }
}
