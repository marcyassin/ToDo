package yassin.marc.todo;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Checklist> {
    ArrayList<Checklist> checklistItems;
    Context context;

    public CustomAdapter(Context context, ArrayList<Checklist> resource){
        super(context, R.layout.list_view_row_item, resource);
        this.context = context;
        this.checklistItems = resource;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_view_row_item, parent, false);
        final TextView name = (TextView) convertView.findViewById(R.id.itemText);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);

        final boolean[] toggle = {true};

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checklistItems.get(position).setValue(isChecked);
                if (toggle[0]){
                    name.setPaintFlags(name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    toggle[0] = !toggle[0];
                }
                else
                    name.setPaintFlags(name.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));

            }
        });


        name.setText(checklistItems.get(position).getName());
        if (checklistItems.get(position).getValue() == 1)
            cb.setChecked(true);

        else
            cb.setChecked(false);
        return convertView;
    }


}
