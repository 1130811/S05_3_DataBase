package pt.pbscaf.s05_3_database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    DBAdapter adapter;
    ArrayList<Person> people;
    ListView peopleList;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         /*!
         * Create database if it is not created yet
         */
        adapter = new DBAdapter(this);
        /*!
         * Fill the listview
         */
        peopleList = (ListView) findViewById(R.id.listView);
        people = adapter.getPeople();
        listAdapter = new ListAdapter(this, R.layout.person_item,people);
        peopleList.setAdapter(listAdapter);
        /*!
         * Register a callback to be invoked when an item in this AdapterView
         * has been clicked.
         */
        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /*!
             * Callback method to be invoked when an item in this
             * AdapterView has been clicked.
             * Implementers can call getItemAtPosition(position)
             * if they need to access the data associated with
             * the selected item.
             * Parameters
             * 	arg0 	The AdapterView where the click happened.
             *  arg1 	The view within the AdapterView that was clicked (this will be a view provided by the adapter)
             *  arg2 	The position of the view in the adapter.
             *  arg3 	The row id of the item that was clicked.
             */
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, PersonDetails.class);
                Person p = (Person) listAdapter.getItem(arg2);
                intent.putExtra("PERSON_ID", p.getId());
                startActivity(intent);
            }
        });


    }
    /***
     * OPTION MENU
     ****/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu ; this adds items to the action bar if it is present .
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here . The action bar will
// automatically handle clicks on the Home /Up button , so long
// as you specify a parent activity in AndroidManifest . xml .
        int id = item.getItemId();
// noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.add:
                Intent i = new Intent(this, AddPerson.class);
                startActivity(i);//a activity nao tera retorno por isso n tenho de instanciar um Intent
                //a desvantagem de não ter startActivity on result é que não sei de que ativity vem (apesar de dizer no Intent).
                // Nesse caso tenho de atualizar a lista fazendo override do metodo onResume. Contudo não seria preciso atualizar a
                // lista qd venho do search, pois não vai atualizar sempre.
                //o melhor seria fazer startAtivityforResult e só atualizar qd viesse do AddPeople
                return true;
            case R.id.search:
                Toast.makeText(this, "Ainda por implementar", Toast.LENGTH_SHORT).show();
              //  Intent i = new Intent(this, S.class);
                //startActivityForResult(i, 1);//a activity de edição/apagar está identificado com 1 no método onActivityResult
                //return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        people.clear();
        people.addAll(adapter.getPeople());
        listAdapter.notifyDataSetChanged();

    }
}
