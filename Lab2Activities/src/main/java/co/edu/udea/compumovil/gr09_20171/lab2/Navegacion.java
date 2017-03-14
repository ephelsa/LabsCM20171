package co.edu.udea.compumovil.gr09_20171.lab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Navegacion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion);
        setTitle(R.string.title_activity_navegacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Editar el Head del Navigation Drawer
        View header = navigationView.getHeaderView(0);
        TextView nav_header_user;
        TextView nav_header_email;
        nav_header_user = (TextView) header.findViewById(R.id.navigation_header_container_user);
        nav_header_email = (TextView) header.findViewById(R.id.navigation_header_container_correo);
        nav_header_user.setText(getIntent().getExtras().getString("usuario"));
        nav_header_email.setText("Aquí iría el correo de " + nav_header_user.getText());
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
        TextView asdasd;

        int id = item.getItemId();

        if (id == R.id.nav_perfil) {
            setTitle(R.string.nav_perfil);
            perfil = new Perfil();

            fragmentManager.beginTransaction().replace(R.id.fragment_content, perfil).commit();

            Toast.makeText(this, getIntent().getExtras().getString("usuario"), Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_eventos) {
            setTitle(R.string.nav_eventos);

        } else if (id == R.id.nav_configuraciones) {
            setTitle(R.string.nav_configuraciones);

        } else if (id == R.id.nav_about) {
            setTitle(R.string.nav_about);

        } else if (id == R.id.nav_logout) {
            setTitle(R.string.nav_logout);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
