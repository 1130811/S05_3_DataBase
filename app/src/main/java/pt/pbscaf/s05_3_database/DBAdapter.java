package pt.pbscaf.s05_3_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/*!
 * 
 */
public class DBAdapter {
	private DBHelper dbHelper;
	
	private static final String TABLE = "People";
	private static final String _ID = "_id";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	
	private static final String SELECT_PEOPLE = "SELECT * FROM " + TABLE;
	
	
	 public DBAdapter(Context context) {
	        dbHelper = new DBHelper(context, TABLE, _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
	                + FIRST_NAME + " TEXT," + LAST_NAME + " TEXT");
	 }
	 public ArrayList<Person> getPeople() {
	        ArrayList<Person> people = new ArrayList<Person>();
	        SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
	        Cursor crsr = sqliteDB.rawQuery(SELECT_PEOPLE, null);
	        crsr.moveToFirst();
	        for (int i = 0; i < crsr.getCount(); i++){
	        	people.add(new Person(crsr.getInt(0), crsr.getString(1),crsr.getString(2)));
	            crsr.moveToNext();
	        }
	        crsr.close();
	        sqliteDB.close();
	        return people;
	    }
	 public Person getPerson(int _id) {
	        SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();
	        String s = "SELECT * FROM " + TABLE + " WHERE " + _ID+ "=" + _id;
	        Cursor crsr = sqliteDB.rawQuery(s, null);
	        crsr.moveToFirst();
	        Person person = new Person(crsr.getInt(0), crsr.getString(1),crsr.getString(2));
	        crsr.close();
	        sqliteDB.close();
	        return person;
	    }

	public void addPerson(String nome, String sobrenome){
		SQLiteDatabase sqliteDB = dbHelper.getReadableDatabase();

		String instrucao = "INSERT INTO " + TABLE + "("+FIRST_NAME+","+LAST_NAME +")"+
				" VALUES " +"(" + "'"+nome + "'"+  "," + "'"+sobrenome + "'"+")";
		System.out.println(instrucao);
		sqliteDB.execSQL(instrucao);
		sqliteDB.close();
	}


}
