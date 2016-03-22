package pt.pbscaf.s05_3_database;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class PersonDetails extends Activity {
	protected TextView person_id;
    protected TextView first_name;
    protected TextView last_name;
    protected int _id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_details);
        DBAdapter adapter = new DBAdapter(getApplicationContext());
        _id = getIntent().getIntExtra("PERSON_ID", 0);
        Person p = adapter.getPerson(_id);
        person_id = (TextView) findViewById(R.id.pId);
        person_id.setText(Integer.toString(p.getId()));
        first_name = (TextView) findViewById(R.id.fName);
        first_name.setText(p.getFirstName());
        last_name = (TextView) findViewById(R.id.lName);
        last_name.setText(p.getLastName());
      }
}
