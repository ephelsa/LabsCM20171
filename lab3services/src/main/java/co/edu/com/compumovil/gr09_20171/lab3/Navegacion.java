package co.edu.com.compumovil.gr09_20171.lab3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Navegacion extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //private final String filename = "registro.txt";
    InfoUsuario infoUsuario;
    //private FileOutputStream outputStream;
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "compumovil", null, 1);
    private Fragment perfil, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* try {
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput(filename)));

            username = fin.readLine();
            fin.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }*/
        setContentView(R.layout.activity_navegacion);
        infoUsuario=(InfoUsuario)getIntent().getExtras().getSerializable("datos");
        setTitle(R.string.title_activity_navegacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Editar el Head del Navigation Drawer
        View header = navigationView.getHeaderView(0);
        ImageView nav_image;
        TextView nav_header_user;
        TextView nav_header_email;

        nav_image = (ImageView) header.findViewById(R.id.imageView);
        nav_header_user = (TextView) header.findViewById(R.id.navigation_header_container_user);
        nav_header_email = (TextView) header.findViewById(R.id.navigation_header_container_correo);

        //nav_image.setImageBitmap(admin.getFotoUser(username));
        nav_header_user.setText(infoUsuario.getUsername());
        nav_header_email.setText(infoUsuario.getEmail());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navegacion, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager = getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            setTitle(R.string.nav_perfil);
            perfil = new Perfil();
            Bundle args=new Bundle();
            args.putSerializable("dat",infoUsuario);
            perfil.setArguments(args);
            fragmentManager.beginTransaction().replace(R.id.fragment_content, perfil).commit();


        } else if (id == R.id.nav_eventos) {
            setTitle(R.string.nav_eventos);

        } else if (id == R.id.nav_configuraciones) {
            setTitle(R.string.nav_configuraciones);

        } else if (id == R.id.nav_about) {
            setTitle(R.string.nav_about);
            about = new About();

            fragmentManager.beginTransaction().replace(R.id.fragment_content, about).commit();

        } else if (id == R.id.nav_logout) {
            //setTitle(R.string.nav_logout);
            /*try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write("".getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            Intent mainIntent = new Intent().setClass(
                    Navegacion.this, Login.class);
            startActivity(mainIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
