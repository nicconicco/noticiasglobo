package noticias.globo.com.br.noticiasglobo.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import noticias.globo.com.br.noticiasglobo.R;
import noticias.globo.com.br.noticiasglobo.domain.Constantes;
import noticias.globo.com.br.noticiasglobo.domain.Noticia;
import noticias.globo.com.br.noticiasglobo.utils.DateUtils;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class DetalheNoticiaActivity extends BaseActivity {

    private Toolbar toolbar;
    private Noticia noticia;
    private TextView tTitulo;
    private TextView tSecao;
    private ImageView img;
    private TextView tData;
    private TextView tDescricao;
    private TextView tMateria;
    private TextView tDescricaoImagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_noticia);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);

            actionBar.setTitle("");
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            noticia = (Noticia) extras.getSerializable(Constantes.NOTICIA);

            if (actionBar != null) {
                TextView title = (TextView) toolbar.findViewById(R.id.title);
                title.setText(noticia.secao.nome);
            }

            tTitulo = (TextView) findViewById(R.id.tTitulo);
            tSecao = (TextView) findViewById(R.id.tSecao);
            img = (ImageView) findViewById(R.id.img);
            tData = (TextView) findViewById(R.id.tData);
            tDescricao = (TextView) findViewById(R.id.tDescricao);
            tMateria = (TextView) findViewById(R.id.tMateria);
            tDescricaoImagem = (TextView) findViewById(R.id.tDescricaoImagem);

            tTitulo.setText(noticia.titulo);
            tSecao.setText(noticia.secao.nome);

            tData.setText(DateUtils.formatDataGlobo(noticia.publicadoEm));

            tDescricao.setText(noticia.subTitulo);
            tMateria.setText(noticia.texto);

            Picasso.with(getContext())
                    .load(noticia.imagens.get(0).url)
                    .fit()
                    .into(img);

            if (noticia.imagens.get(0).legenda != null) {
                tDescricaoImagem.setText(noticia.imagens.get(0).legenda + "");
                if (noticia.imagens.get(0).fonte != null) {
                    tDescricaoImagem.setText(noticia.imagens.get(0).legenda + ".Foto: "+noticia.imagens.get(0).fonte);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
}
