package yassin.marc.todo;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class MainActivity extends Activity {
    private ListView lv;
    ArrayList<Checklist> checklistItems;
    CustomAdapter myAdapter;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.addItem);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shortclick();
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                longclick();
                return true;

            }
        });


        lv = (ListView) findViewById(R.id.listView);
        checklistItems = new ArrayList<Checklist>();
        checklistItems.add(new Checklist(0,"When you add a new item it will show here"));
        checklistItems.add(new Checklist(0,"Check off the items that you've finished"));
        checklistItems.add(new Checklist(0,"To delete items, check them off..."));
        checklistItems.add(new Checklist(0,"then long press on the Save button"));

        myAdapter = new CustomAdapter(this,checklistItems);
        lv.setAdapter(myAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void shortclick()
    {
        EditText taskET = (EditText) findViewById(R.id.taskET);
        String itemText = taskET.getText().toString();
        Checklist newTask = new Checklist(0,itemText);
        myAdapter.add(newTask);
        taskET.setText("");

    }

    public void longclick()
    {
        Toast toast;
        int numOfdeletions;
        ArrayList<Checklist> temp = new ArrayList<Checklist>();

        for (int i = 0; i < checklistItems.size(); i++){
            if (checklistItems.get(i).getValue() == 0)
                temp.add(checklistItems.get(i));
        }

        numOfdeletions = checklistItems.size() - temp.size();

        checklistItems.clear(); //added
        checklistItems.addAll(temp); //added

        if (numOfdeletions == 1)
            toast = Toast.makeText(getApplicationContext(),numOfdeletions+" item removed",Toast.LENGTH_SHORT);
        else
            toast = Toast.makeText(getApplicationContext(),numOfdeletions+" items removed",Toast.LENGTH_SHORT);

        myAdapter.notifyDataSetChanged();
        toast.show();
    }




}
