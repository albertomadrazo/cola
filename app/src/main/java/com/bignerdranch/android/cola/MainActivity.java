package com.bignerdranch.android.cola;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    Button leer;
    Button creaArchivo;
    TextView contenido;
    TextView listaArchivos;
    public static Context mContext;
    EditText etTitulo, etAutor, etIdioma;
    Libro libro;
    String directorio = "encolados";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        etTitulo = (EditText) findViewById(R.id.titulo);
        etAutor = (EditText) findViewById(R.id.autor);
        etIdioma = (EditText) findViewById(R.id.idioma);

        leer = (Button) findViewById(R.id.leer);
        contenido = (TextView) findViewById(R.id.contenido);
        String date = new SimpleDateFormat("yyyy-mm-dd").format(new Date());

        contenido.setText(date);

        creaArchivo = (Button) findViewById(R.id.creaArchivo);
        creaArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        listaArchivos = (TextView) findViewById(R.id.listaArchivos);

        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo, autor, idioma;
                titulo = etTitulo.getText().toString();
                autor = etAutor.getText().toString();
                idioma = etIdioma.getText().toString();
                libro = new Libro(titulo, autor, idioma);
                try {
                    String nombreArchivo = nombraArchivo();
                    creaArchivo(mContext, nombreArchivo, libro);
                    String libroJson = leeJson(mContext, nombreArchivo);
                    Libro elLibro = leeObjeto(mContext, libroJson);
                    contenido.setText(libroJson);
                    String listillaHijaDePuta = "";
                    File lista = listarArchivos();
                    for(String item : lista.list()){
                        listillaHijaDePuta += item + "\n";
                    }
                    listaArchivos.setText(listillaHijaDePuta);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void creaArchivo(Context context, String nombreArchivo, Libro libro){
        Gson gson = new Gson();
        String s = gson.toJson(libro);

        FileOutputStream outputStream;
        try{
            outputStream = openFileOutput(nombreArchivo, Context.MODE_PRIVATE);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String leeJson(Context context, String nombreArchivo) throws IOException{

        FileInputStream fis = null;
        try {
            fis = mContext.openFileInput(nombreArchivo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;

        while((line = bufferedReader.readLine()) != null){
            sb.append(line);
        }

        String json = sb.toString();
        return json;
    }

    public Libro leeObjeto(Context context, String json) throws IOException {
        Gson gson = new Gson();
        Libro libro = gson.fromJson(json, Libro.class);

        return libro;
    }

    public String nombraArchivo(){
        String nombreArchivo = new Date().toString();
        return nombreArchivo;
    }

    public File listarArchivos(){
        // lista los archivos en el directorio
        File lista = getApplicationContext().getFilesDir();
        for(String item : lista.list()){
            Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
        }

        return lista;
    }
}

























