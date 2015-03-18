package com.example.conexion_php;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Borrar extends ActionBarActivity implements OnClickListener{

	EditText d1;
	Button borrar;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_borrar);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		
		d1 = (EditText) findViewById(R.id.editText2);
		borrar = (Button) findViewById(R.id.button1);

		borrar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String titulo = d1.getText().toString().trim();

		if (v.getId() == R.id.button1) {
			if (titulo.equals("")) {
				Toast.makeText(this, "Debes poner un ID y una IP correcta",
						Toast.LENGTH_LONG).show();
			} else {
				try {
					ConexionWeb("http://tiburcio.cdmon.org/~10091168/borra.php?titulo='" + titulo + "'");
					Toast.makeText(this, "Registro Borrado!", Toast.LENGTH_LONG)
							.show();
				} catch (Exception e) {
					Toast.makeText(this,"Verifica la IP \n El id" + e,Toast.LENGTH_LONG).show();
				}
			}
		}
	}
	
	public String ConexionWeb(String direccion) throws Exception {
		String pagina = "";
		URL url = new URL(direccion);
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conexion.getInputStream()));
			String linea = reader.readLine();
			while (linea != null) {
				pagina += linea + "\n";
				linea = reader.readLine();

			}
			reader.close();
		} else {
			Toast.makeText(this, "Error en la conexión", Toast.LENGTH_LONG)
					.show();
		}
		conexion.disconnect();
		return pagina;

	}
}
