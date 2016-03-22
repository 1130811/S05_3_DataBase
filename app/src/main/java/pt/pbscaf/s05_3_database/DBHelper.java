package pt.pbscaf.s05_3_database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/*!
 * SQLiteOpenHelper is an abstract class used to implement the 
 * best practice pattern for creating, opening,
 * and upgrading databases. By implementing an SQLite Open Helper 
 * you hide the logic used to decide if a database needs to be 
 * created or upgraded before it is opened.
 * 
 * You create a subclass implementing onCreate(SQLiteDatabase), 
 * onUpgrade(SQLiteDatabase, int, int) and optionally 
 * onOpen(SQLiteDatabase), 
 * and this class takes care of opening the database if it exists, 
 * creating it if it does not, and upgrading it as necessary. 
 * Transactions are used to make sure the database is always in a 
 * sensible state.
 * 
 */

public class DBHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PeopleDB";
	private String createStatement = "";
	private String tableName;
	private Context context;
	
	public DBHelper(Context context, String tableName, String fields) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.createStatement  = "CREATE TABLE IF NOT EXISTS ";
        this.createStatement += tableName + " (";
        this.createStatement += fields + ");";
        this.tableName = tableName;
        this.context = context;
	}
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(this.createStatement);
		insertData(arg0);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		arg0.execSQL("DROP TABLE IF EXISTS " + this.tableName);
		onCreate(arg0);
	}
	/*!
	 * This method uses a structured xml file with data to fill database tables
	 */
	public void insertData(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String s;
        try {
                InputStream in = this.context.getResources().openRawResource(R.raw.sql);
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document doc = builder.parse(in, null);
                NodeList statements = doc.getElementsByTagName("statement");
                for (int i=0; i<statements.getLength(); i++) {
                        s = "INSERT INTO "+ this.tableName + " " + statements.item(i).getChildNodes().item(0).getNodeValue();
                        db.execSQL(s);
                        s="";
                }
        } catch (Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
        }

	}
}
