package com.example.pmdmrec04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pmdmrec04.R;

import java.util.ArrayList;

import sqlite.MyOpenHelper;
import sqlite.Provincia;

public class InvitadoActivity extends AppCompatActivity {

    private MyOpenHelper dbHelper;
    SQLiteDatabase db;
    ListView listView;
    ArrayList<Provincia> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitado);

        dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.listViewInvitado);


        lista = new ArrayList<Provincia>();
        Cursor c = db.rawQuery("SELECT * FROM provincias ", null);

        if (c.moveToFirst()) {

            while (!c.isAfterLast()) {
                String nombre = c.getString(c.getColumnIndex("nombre"));
                int fase = c.getInt(c.getColumnIndex("fase"));

                lista.add(new Provincia(nombre, fase));
                c.moveToNext();
            }
        }

        MyAdapter myAdapter = new MyAdapter(this, R.layout.lista_provincias, lista);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                Intent intent;
                Bundle datosEnvio = new Bundle();
                switch (posicion){
                    case 0:
                        intent = new Intent(view.getContext(), LugoActivity.class);
                        Provincia proLugo = lista.get(posicion);
                        datosEnvio.putString("fase", String.valueOf(proLugo.getFase()));
                        datosEnvio.putString("descripcion", descripcionFase(proLugo.getFase()));
                        intent.putExtras(datosEnvio);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), OurenseActivity.class);
                        Provincia proOurense = lista.get(posicion);
                        datosEnvio.putString("fase", String.valueOf(proOurense.getFase()));
                        datosEnvio.putString("descripcion", descripcionFase(proOurense.getFase()));
                        intent.putExtras(datosEnvio);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), CorunaActivity.class);
                        Provincia proCoruna = lista.get(posicion);
                        datosEnvio.putString("fase", String.valueOf(proCoruna.getFase()));
                        datosEnvio.putString("descripcion", descripcionFase(proCoruna.getFase()));
                        intent.putExtras(datosEnvio);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(view.getContext(), PontevedraActivity.class);
                        Provincia proPontevedra = lista.get(posicion);
                        datosEnvio.putString("fase", String.valueOf(proPontevedra.getFase()));
                        datosEnvio.putString("descripcion", descripcionFase(proPontevedra.getFase()));
                        intent.putExtras(datosEnvio);
                        startActivity(intent);
                        break;
                }
                //Toast.makeText(InvitadoActivity.this, "Has pulsado: "+ lista.get(posicion), Toast.LENGTH_LONG).show();
            }
        });

    }

    public String descripcionFase(int fase){
        String cadena = "";
        switch (fase){
            case 0:
                cadena = "- Salidas individuales para hacer ejercicio, previstas para el 2 de mayo.\n" +
                        "\n" +
                        "- Apertura de locales y establecimientos con cita previa para atención individual de los clientes, como restaurantes con servicio de comida para poder llevar a domicilio sin consumo en el local. El servicio deberá prestarse con la máxima protección.\n" +
                        "\n" +
                        "- Apertura de entrenamientos individuales de deportistas profesionales y federados y el entrenamiento básico de ligas profesionales.\n" +
                        "\n" +
                        "- A lo largo de esta fase cero se va a intensificar la preparación de todos los locales públicos con señalización y medidas de protección para preparar el comienzo de la siguiente fase.";
                break;
            case 1:
                cadena = "-Uso de coche de hasta 9 plazas por parte de los habitantes de un mismo domicilio. En caso de no vivir juntos, solo se podrán ocupar dos plazas por cada fila de asientos, manteniendo las distancias, y cada pasajero deberá utilizar mascarillas u otro dispositivo de protección.\n" +
                        "\n" +
                        "-Apertura de terrazas al 50 % de su capacidad. Tendrá que haber una distancia mínima de dos metros entre las mesas y los grupos de clientes tendrán un máximo de diez personas. Además, habrá que desinfectar las mesas entre un cliente y otro y no se podrán utilizar cartas de uso común ni servilleteros.\n" +
                        "\n" +
                        "-Apertura de tiendas de menos de 400 metros cuadrados sin cita previa. El aforo máximo para los comercios será del 30 %, será necesario mantener una distancia de seguridad mínima de dos metros y se deberá establecer un horario de atención preferente a mayores. Tendrá que procederse a la desinfección del local dos veces al día.\n" +
                        "\n" +
                        "-Velatorios para un número limitado de familiares en instalaciones públicas o privadas: quince personas al aire libre o diez en espacios cerrados. La comitiva para el enterramiento o la despedida para la cremación del fallecido se restringe a un máximo de quince personas y, en su caso, se podrá sumar el ministro de culto o persona asimilada de la confesión respectiva para la práctica de los ritos funerarios de despedida del difunto.\n" +
                        "\n" +
                        "-Mercadillos al aire libre en la vía pública, con condiciones de distanciamiento entre puestos y delimitación del mercado ambulante para correcto control del aforo por las fuerzas de seguridad. Limitación inicial al 25% de los puestos habituales y afluencia máxima de la tercera parte del aforo.\n" +
                        "\n" +
                        "-Los lugares de culto abrirán, aunque sólo se permitirá la entrada hasta un tercio de su capacidad.";
                break;
            case 2:
                cadena = "- Permite la reapertura al público de las zonas comunes de hoteles y alojamientos turísticos.\n" +
                        "\n" +
                        "- Permite la apertura establecimientos destinados a actos y espectáculos culturales como cines y teatros, pero con un aforo máximo del 30 %.\n" +
                        "\n" +
                        "- Se podrá ir a la piscina, con cita previa, y respetando la distancia de seguridad, sin superar nunca el aforo del 30 %.\n" +
                        "\n" +
                        "- También se podrá ir a la playa, en grupos de 15 personas y manteniendo siempre la distancia de seguridad.\n" +
                        "\n" +
                        "- Se podrá practicar deporte a cualquier hora a los menores de 70 años, excepto en los tramos horarios reservados para el paseo de las personas mayores.\n" +
                        "\n" +
                        "- Durante esta fase, y en lo que respecta a las empresas, se seguirá priorizando el teletrabajo.";
                break;
            case 3:
                cadena = "- Una vez se cumplan marcadores se flexibilizará la movilidad general, pero con recomendación de uso de la mascarilla fuera del hogar y de los transportes públicos.\n" +
                        "\n" +
                        "- Apertura de playas.\n" +
                        "\n" +
                        "- En el ámbito comercial, se limitará el aforo al 50 por ciento y se fijará una distancia mínima de dos metros.\n" +
                        "\n" +
                        "- En restauración se flexibilizarán las condiciones de aforo y de ocupación si bien con estrictas condiciones de separación entre el público.";
                break;
        }
        return cadena;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}

