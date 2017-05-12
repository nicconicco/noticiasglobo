package noticias.globo.com.br.noticiasglobo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import br.livetouch.fragment.BaseFragment;
import br.livetouch.utils.AlertUtils;
import br.livetouch.utils.KeyboardUtils;
import noticias.globo.com.br.noticiasglobo.R;
import noticias.globo.com.br.noticiasglobo.domain.Constantes;
import noticias.globo.com.br.noticiasglobo.fragment.NoticiasFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MenuItem menuSelecionado;
    private Toolbar toolbar;
    // frag esta em uso no fim do código.
    private BaseFragment frag;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
        }

        BaseFragment fragment = new NoticiasFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment, Constantes.NOTICIA).commitNow();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                closeKeyboar();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                closeKeyboar();
            }

            private void closeKeyboar() {
                KeyboardUtils.closeVirtualKeyboard(getContext(), findViewById(R.id.drawer_layout));
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigation_drawer_container);
        NavigationView navViewHeader = (NavigationView) navigationView.findViewById(R.id.navigation_drawer);
        NavigationView navViewFooter = (NavigationView) navigationView.findViewById(R.id.navigation_drawer_bottom);
        navViewHeader.setNavigationItemSelectedListener(this);
        navViewFooter.setNavigationItemSelectedListener(this);

        navViewHeader.setItemIconTintList(null);

        MenuItem item = navViewHeader.getMenu().getItem(0);
        item.setChecked(true);
        menuSelecionado = item;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (menuSelecionado != null) {
            if (id == menuSelecionado.getItemId() && id != R.id.nav_logout) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
            menuSelecionado.setChecked(false);
        }
        if (id != R.id.nav_logout) {
            menuSelecionado = item;
            menuSelecionado.setChecked(true);
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (id) {
            case R.id.nav_noticias:
                showFragment(ft);
                break;
//            case R.id.nav_defesa:
//                showFragment(ft);
//                break;
//            case R.id.nav_sociedade:
//                showFragment(ft);
//                break;
//            case R.id.nav_mundo:
//                showFragment(ft);
//                break;
//            case R.id.nav_economia:
//                showFragment(ft);
//                break;
            case R.id.nav_logout:
                AlertUtils.alertConfirm(this, R.string.deseja_realmente_sair, R.string.sair, R.string.cancelar, new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, null);
                break;
            default:
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    private void showFragment(FragmentTransaction ft) {
        NoticiasFragment frc = new NoticiasFragment();
        frag = frc;
        ft.replace(R.id.container, frc).commitNow();
    }
}
