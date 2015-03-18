package com.example.conexion_php;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Insertar extends ActionBarActivity implements OnClickListener {

	EditText  d1, d2, d3, d4, d5;
	Button guardar;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		d1 = (EditText) findViewById(R.id.editText2);
		d2 = (EditText) findViewById(R.id.editText3);
		d3 = (EditText) findViewById(R.id.editText4);
		d4 = (EditText) findViewById(R.id.editText5);
		guardar = (Button) findViewById(R.id.button1);
	

		guardar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String titulo = d1.getText().toString().trim();
		String autor = d2.getText().toString().trim();
		String editorial = d3.getText().toString().trim();
		String ano = d4.getText().toString().trim();
		
		System.out.println("titulo : " + titulo);
		System.out.println("autor : " + autor);
		System.out.println("editorial : " + editorial);
		System.out.println("ano : " + ano);

		if (v.getId() == R.id.button1) {
			if (titulo.equals("") || autor.equals("") || editorial.equals("") || ano.equals("") ) {
				Toast.makeText(this, "Ingresa todos los datos!",
						Toast.LENGTH_LONG).show();
				System.out.println("Entro al IF");
			} else {
				try {
//					ConnectivityManager iMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//					Method iMthd = null;
//					
//					iMthd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
//					
//					iMthd.setAccessible(true);
					 //String ed = edt1.getText().toString().trim();
					String n  = "http://tiburcio.cdmon.org/~10091168/inserta.php?titulo=%27"+
							titulo+"%27&autor=%27"+
							autor+"%27&editorial=%27"+
							editorial+"%27&año=%27"+ano+"%27";

					System.out.println("Entro al ELSE" + n);
					ConexionWeb(n);
					
					
					Toast.makeText(this, "Datos Agregados!", Toast.LENGTH_LONG)
							.show();
				} catch (Exception e) {
					Toast.makeText(this,
							" El id no se puede repetir" + e,
							Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	public String ConexionWeb(String direccion) throws Exception {
		String pagina = "";
		URL url = new URL(direccion);
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		
		if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
			System.out.println(conexion.getResponseCode());
			System.out.println(conexion.getResponseMessage());
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

