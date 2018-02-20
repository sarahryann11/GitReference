package csci490.gitreference;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sarah on 2/20/2018.
 */

public class CommandAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflator;
    private ArrayList<Command> mDataSource;

    public CommandAdapter(Context context, ArrayList<Command> commands) {
        mContext = context;
        mDataSource = commands;
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = mInflator.inflate(R.layout.list_item, viewGroup, false);

        TextView commandView = rowView.findViewById(R.id.command);
        TextView exampleView = rowView.findViewById(R.id.example);
        TextView explanationView = rowView.findViewById(R.id.explanation);

        Command command = (Command) getItem(position);
        commandView.setText(command.getCommand());
        exampleView.setText(command.getExample());
        explanationView.setText(command.getExplanation());

        return rowView;
    }
}
