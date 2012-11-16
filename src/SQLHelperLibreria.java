import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.CulturaEnLasCalles.SQLHelper;


public class SQLHelperLibreria extends SQLiteOpenHelper {

	public SQLHelperLibreria(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		createDBLibros(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS Librerias");
		createDBLibros(db);
		
	}
	
	private void createDBLibros(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE Librerias(nombre TEXT,ubicacion TEXT,coordenadas TEXT, barrio TEXT, tematica TEXT, telefono TEXT, email TEXT,web TEXT, video TEXT");
		
	}

}
