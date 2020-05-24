package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    public final static String NOMBRE_BD="basedatos";
    public final static int VERSION_BD=1;
    private SQLiteDatabase db;

    // CREATES
    private String CREAR_TABLA_PROVINCIAS = "CREATE TABLE provincias (id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , nombre VARCHAR(50) NOT NULL, fase INTEGER)";
    private String CREAR_TABLA_USUARIOS = "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , usuario VARCHAR(50) NOT NULL, contrasena VARCHAR(40) NOT NULL, nombre VARCHAR(100) NOT NULL, apellidos VARCHAR(150), email VARCHAR(200))";
    private String CREAR_TABLA_IMAGENES = "CREATE TABLE imagenes (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, imagen_coruna VARCHAR(255), imagen_lugo VARCHAR(255), imagen_ourense VARCHAR(255), imagen_pontevedra VARCHAR(255))";
    // INSERTS
    private String INSERTAR_DATOS_USUARIOS = "INSERT INTO usuarios (usuario, contrasena, nombre, apellidos, email) VALUES ('miguel', 'abc', 'Luis Miguel', 'García Rodríguez', 'luismiguelgr@gmx.es')";
    private String INSERTAR_DATOS_PROVINCIAS = "INSERT INTO provincias (nombre, fase) VALUES ('Lugo', 1), ('Ourense', 2), ('A Coruña', null), ('Pontevedra', null)";
private String INSERTAR_DATOS_IMAGENES = "INSERT INTO imagenes (imagen_coruna, imagen_lugo, imagen_ourense, imagen_pontevedra) VALUES ('', '', '', '')";
    public MyOpenHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    public void setSQLiteDatabase(SQLiteDatabase db){
        this.db = db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIOS);
        db.execSQL(CREAR_TABLA_PROVINCIAS);
        db.execSQL(CREAR_TABLA_IMAGENES);
        db.execSQL(INSERTAR_DATOS_USUARIOS);
        db.execSQL(INSERTAR_DATOS_PROVINCIAS);
        db.execSQL(INSERTAR_DATOS_IMAGENES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS provincias");
        onCreate(db);
    }

}
