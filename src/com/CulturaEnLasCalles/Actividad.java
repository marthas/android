package com.CulturaEnLasCalles;

import android.app.Activity;

import android.os.Bundle;


import android.widget.TextView;

public class Actividad  extends Activity {
    /** Called when the activity is first created. */
    String titulo;
    String descripcion;
	@Override
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad);
        Bundle data=this.getIntent().getExtras();
        if(data!=null){
            titulo=(data.containsKey("titulo"))?data.getString("titulo"):"";
            descripcion=(data.containsKey("descripcion"))?data.getString("descripcion"):"";
        }
        final TextView ttitulo= (TextView)this.findViewById(R.id.titulo);
        
        final TextView tdescripcion= (TextView)this.findViewById(R.id.Descripcion);
        ttitulo.setText(titulo);
        tdescripcion.setText(descripcion);
        }
        
    }

