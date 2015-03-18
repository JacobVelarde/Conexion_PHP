package com.example.conexion_php;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Conexion_PHPMainActivity extends ActionBarActivity implements OnClickListener {
	Button bt1,bt2,bt3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conexion__phpmain);
		
		bt1 = (Button) findViewById(R.id.button1);
		bt2 = (Button) findViewById(R.id.button2);
		bt3 = (Button) findViewById(R.id.button3);
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.button1:
			Intent intent = new Intent(this, Consulta.class);
			startActivity(intent);
			break;
		case R.id.button2:
			Intent intent2 = new Intent(this, Insertar.class);
			startActivity(intent2);
			break;
		case R.id.button3:
			Intent intent3 = new Intent(this, Borrar.class);
			startActivity(intent3);
			break;
		}
	}
}
