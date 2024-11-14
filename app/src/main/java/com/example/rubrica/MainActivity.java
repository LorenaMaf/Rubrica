package com.example.rubrica;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {
    public ListView listView;
    EditText nome;
    EditText cognomen;
    EditText tel;
    CustomAdapter customAdapter;
    ArrayList<String> nomi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.etnome);
        cognomen = findViewById(R.id.etcognome);
        tel = findViewById(R.id.ettel);

        if (savedInstanceState != null) {
            nomi = savedInstanceState.getStringArrayList("CONTATTI");
        }
        nomi = new ArrayList<>();
        nomi.add("Mario Rossi");
        nomi.add("Giuseppe Verdi");
        nomi.add("Antonio Bianco");


        listView = findViewById(R.id.mylistview);
        customAdapter = new CustomAdapter(this, R.layout.list_element, new ArrayList<Contatto>());

        listView.setAdapter(customAdapter);

        for (String nomeContatto : nomi) {
            Contatto c = new Contatto(
                    nomeContatto,
                    "111-2222-333",
                    getResources().getDrawable(R.drawable.faceplaceholder));
            customAdapter.add(c);
        }
    }

    public void onInserisci(View v) {
        String str = nome.getText().toString();
        String cog = cognomen.getText().toString();
        String nec = str + " " + cog;
        if (str.length() > 0) {
            Contatto c = new Contatto(
                    nec,
                    tel.getText().toString(),
                    getResources().getDrawable(R.drawable.faceplaceholder));
            customAdapter.add(c);
            nomi.add(nec);
        }
        customAdapter.notifyDataSetChanged();
    }

    public void onPictureClick(View v) {
        Log.d("DEBUG","Picture has been clicked: position="+v.getTag());
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        int position = Integer.parseInt(v.getTag().toString());
                        Contatto c = customAdapter.getItem(position);
                        Toast.makeText(getApplicationContext(),
                                        "Click su foto di: " +c.getName()+", contatto eliminato", Toast.LENGTH_LONG)
                                .show();
                        customAdapter.remove(c);
                        nomi.remove(c);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(getApplicationContext(), "Azione annullata", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Stai per rimuovere il contatto. Sei sicuro?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

        return;
    }

    public void onNameClick(View v) {
        Log.d("DEBUG","Name has been clicked position="+v.getTag());

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        int position = Integer.parseInt(v.getTag().toString());
                        Contatto c = customAdapter.getItem(position);
                        Toast.makeText(getApplicationContext(),
                                        "Click su Nome di: " +c.getName()+", contatto eliminato", Toast.LENGTH_LONG)
                                .show();
                        customAdapter.remove(c);
                        nomi.remove(c);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(getApplicationContext(), "Azione annullata", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Stai per rimuovere il contatto. Sei sicuro?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

        return;

    }


    public void onTelClick(View v) {
        Log.d("DEBUG","Tel has been clicked position="+v.getTag());
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        int position = Integer.parseInt(v.getTag().toString());
                        Contatto c = customAdapter.getItem(position);
                        Toast.makeText(getApplicationContext(),
                                        "Click su Tel di: " +c.getName()+", contatto eliminato", Toast.LENGTH_LONG)
                                .show();
                        customAdapter.remove(c);
                        nomi.remove(c);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(getApplicationContext(), "Azione annullata", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Stai per rimuovere il contatto. Sei sicuro?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

        return;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("CONTATTI", nomi);
    }

}
