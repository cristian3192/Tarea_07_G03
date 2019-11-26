package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import uce.g3.registro_personas.Persona;

public class BaseDeDatos extends SQLiteOpenHelper {

    public BaseDeDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
/*crear estructura de las tablas*/
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table usuario(_ID integer primary key autoincrement, Nombre text, " +
                "Apellido text, Telefono text, Email text,Fecha_Nacimiento date, Usuario text, Clave text, " +
                "Genero text, Asignatura text, Becado text);";

        db.execSQL(query);

    }
/*modificar la estructura de la base de datos*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
// metodo para abrir la base de datos
    public void abrirBase(){
        this.getWritableDatabase();
    }

// metodo para cerrar la base de datos
    public void cerrarBase(){
        this.close();
    }
// metodo para ingresar datos a la tabla
    public void insertarRegistro(String nombre, String apellido, String telefono, String email, String fechaNacimiento, String usuario, String clave, String genero, String asignatura, String becado){

        ContentValues datos = new ContentValues();
        datos.put("Nombre",nombre);
        datos.put("Apellido",apellido);
        datos.put("Telefono",telefono);
        datos.put("Email",email);
        datos.put("Fecha_Nacimiento", String.valueOf(fechaNacimiento));
        datos.put("Usuario",usuario);
        datos.put("Clave",clave);
        datos.put("Genero",genero);
        datos.put("Asignatura",asignatura);
        datos.put("Becado",becado);

        this.getWritableDatabase().insert("usuario",null,datos);
    }

// metodo para validar el usuario
    public Cursor login(String usuario, String clave) throws SQLException {
        Cursor cursorC = null;
        cursorC = this.getReadableDatabase().query("usuario", new String[]{"Usuario","Clave"},
                "Usuario='"+usuario+"' and Clave='"+clave+"'",null,
                null,null,null);

        return cursorC;
    }

// metodo para obtener el arraylist
public ArrayList llenarListView(){
        ArrayList<String> lista = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM usuario";
        Cursor info = database.rawQuery(q,null);
        if (info.moveToFirst()){
            do {
                lista.add(info.getString(0)+" "+info.getString(1));
            }while(info.moveToNext());
        }
        return lista;
}
    public ArrayList llenarListInfo(){

        ArrayList<String> listaTemp = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM usuario";
        Cursor info = database.rawQuery(q,null);
        if (info.moveToFirst()){
            do {
               /* listaTemp.add("Nombre: "+info.getString(1)+"\n"+"Apellido: "+info.getString(2)+"\n"+"Telefono: "+info.getString(3)+"\n"
                        +"Email: "+info.getString(4)+"\n"+"Fecha Nacimiento: "+info.getString(5)+"\n"+"Genero: "+info.getString(8)+"\n"+
                        "Asignatura: "+info.getString(9)+"\n"+"Becado?: "+info.getString(10));*/
                listaTemp.add("Nombre: "+info.getString(1));
                listaTemp.add("Apellido: "+info.getString(2));
                listaTemp.add("Telefono: "+info.getString(3));
                listaTemp.add("Email: "+info.getString(4));
                listaTemp.add("Fecha Nacimiento: "+info.getString(5));
                listaTemp.add("Genero: "+info.getString(8));
                listaTemp.add("Asignatura: "+info.getString(9));
                listaTemp.add("Becado?: "+info.getString(10));

            }while(info.moveToNext());
        }
        return listaTemp;
    }

    public ArrayList llenarListInfo1(){

        ArrayList<String> listaTemp = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM usuario";
        Cursor info = database.rawQuery(q,null);
        if (info.moveToFirst()){
            do {
               listaTemp.add("Nombre: "+info.getString(1)+"\n"+"Apellido: "+info.getString(2)+"\n"+"Telefono: "+info.getString(3)+"\n"
                        +"Email: "+info.getString(4)+"\n"+"Fecha Nacimiento: "+info.getString(5)+"\n"+"Genero: "+info.getString(8)+"\n"+
                        "Asignatura: "+info.getString(9)+"\n"+"Becado?: "+info.getString(10));


            }while(info.moveToNext());
        }
        return listaTemp;
    }

    public ArrayList<Persona> listarPersonas(){

        ArrayList<Persona> listaPersonas = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM usuario";
        Cursor info = database.rawQuery(q,null);
        if (info.moveToFirst()){
            do {
                Persona pers = new Persona();
                pers.setNombre(info.getString(1));
                pers.setApellido(info.getString(2));
                pers.setTelefono(info.getString(3));
                pers.setEmail(info.getString(4));
                pers.setGenero(info.getString(8));
                pers.setAsignatura(info.getString(9));
                pers.setBecado(info.getString(10));
                listaPersonas.add(pers);
            }while(info.moveToNext());
        }
        return listaPersonas;
    }
}
