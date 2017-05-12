package noticias.globo.com.br.noticiasglobo.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.livetouch.task.BaseTask;
import br.livetouch.task.Task;
import br.livetouch.utils.LogUtil;
import noticias.globo.com.br.noticiasglobo.NoticiasGloboApplication;
import noticias.globo.com.br.noticiasglobo.R;
import noticias.globo.com.br.noticiasglobo.activity.DetalheNoticiaActivity;
import noticias.globo.com.br.noticiasglobo.domain.Constantes;
import noticias.globo.com.br.noticiasglobo.domain.Noticia;
import noticias.globo.com.br.noticiasglobo.service.GloboService;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class NoticiasFragment extends BaseFragment{

    private ListaNoticiasFragment frag;

    private TextView tSecao;
    private TextView tTitulo;
    private ImageView img;
    private FrameLayout btnCapa;
    private List<Noticia> noticias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        Toolbar actionBar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if (actionBar != null) {
            actionBar.setTitle("");

            TextView title = (TextView) actionBar.findViewById(R.id.title);
            title.setText(R.string.O_GLOBO);

        }

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        tSecao = (TextView) findViewById(R.id.tSecao);
        tTitulo = (TextView) findViewById(R.id.tTitulo);
        img = (ImageView) findViewById(R.id.img);

        if (NoticiasGloboApplication.getInstance().getNoticias() == null ||
                NoticiasGloboApplication.getInstance().getNoticias().size() == 0) {
            Task task = taskGetNoticias();
            startTask(task);
        }

        findViewById(R.id.btnCapa).setOnClickListener(onClickCapa());

        frag = new ListaNoticiasFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.container2, frag).commitNow();
    }

    private View.OnClickListener onClickCapa() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noticias.size() > 0) {
                    Bundle b = new Bundle();
                    b.putSerializable(Constantes.NOTICIA, noticias.get(0));
                    show(DetalheNoticiaActivity.class, b);
                }
            }
        };
    }

    private Task taskGetNoticias() {
        return new BaseTask() {


            @Override
            public void execute() throws Exception {
                noticias = GloboService.getNoticiasJson();
                NoticiasGloboApplication.getInstance().setNoticias(noticias);
            }

            @Override
            public void updateView() {
                if (noticias != null) {
                    LogUtil.log("json local pego com sucesso");

                    if (noticias.get(0).secao.nome != null) {
                        if (tSecao != null) {
                            tSecao.setText(noticias.get(0).secao.nome);
                        }
                    }

                    if (noticias.get(0).titulo != null) {
                        tTitulo.setText(noticias.get(0).titulo);
                    }

                    if (noticias.get(0).imagens.get(0).url != null) {
                        Picasso.with(getContext())
                                .load(noticias.get(0).imagens.get(0).url)
                                .fit()
                                .into(img);
                    }
                }
            }
        };
    }
}
