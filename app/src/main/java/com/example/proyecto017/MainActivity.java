package com.example.proyecto017;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1, etm1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et1 = findViewById(R.id.et);
        etm1 = findViewById(R.id.etm);
    }

    public void grabar(View v) throws IOException {
        String nomararchivo = et1.getText().toString(); //el nombre del archivo es la fecha
                nomararchivo = nomararchivo.replace('/', '-'); // esto se hace porque no se puede guardar con slash el nombre de los archivos

        OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nomararchivo, Activity.MODE_PRIVATE));
        archivo.write(etm1.getText().toString());
        archivo.flush();
        archivo.close();

        Toast.makeText(this, "datos guardados", Toast.LENGTH_LONG).show();
        et1.setText("");
        etm1.setText("");

    }


    public void recuperar(View v) throws IOException {
        String normaarchivo = et1.getText().toString();//el nombre del archivo es la fecha
        normaarchivo = normaarchivo.replace('/', '-');// esto se hace porque no se puede guardar con slash el nombre de los archivos
        boolean enco = false;
        String[] archivos = fileList();
        for (int i = 0; i < archivos.length; i++) {
            if (normaarchivo.equals(archivos[i])) {
                enco = true;
            }
        }
        if (enco == true) {

            InputStreamReader archivo = new InputStreamReader(openFileInput(normaarchivo));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo = "";
            while (linea != null) {
                todo = todo + linea + "\n";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            etm1.setText(todo);

        } else {

            Toast.makeText(this, "no hay datos guardados par ala fehca", Toast.LENGTH_LONG).show();
            etm1.setText("");


        }


    }


}