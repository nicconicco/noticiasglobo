package noticias.globo.com.br.noticiasglobo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.livetouch.task.BaseTask;
import br.livetouch.task.Task;
import noticias.globo.com.br.noticiasglobo.NoticiasGloboApplication;
import noticias.globo.com.br.noticiasglobo.R;
import noticias.globo.com.br.noticiasglobo.activity.DetalheNoticiaActivity;
import noticias.globo.com.br.noticiasglobo.adapter.NoticiasAdapter;
import noticias.globo.com.br.noticiasglobo.domain.Constantes;
import noticias.globo.com.br.noticiasglobo.domain.Noticia;
import noticias.globo.com.br.noticiasglobo.service.GloboService;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class ListaNoticiasFragment extends BaseFragment{

    private RecyclerView recycler;
    private List<Noticia> noticias;
    private NoticiasAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_noticias, container, false);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Task task = taskGetNoticias();
        startTask(task);
    }

    private Task taskGetNoticias() {
        return new BaseTask() {
            @Override
            public void execute() throws Exception {
                noticias = GloboService.getNoticiasJson();
            }

            @Override
            public void updateView() {
                if (noticias != null && noticias.size() > 0) {
                    NoticiasGloboApplication.getInstance().setNoticias(noticias);
                    mAdapter =  new NoticiasAdapter(getContext(), noticias, onClickListener());
                    recycler.setAdapter(mAdapter);
                }
            }
        };
    }

    private NoticiasAdapter.OnClickListener onClickListener() {
        return new NoticiasAdapter.OnClickListener() {
            @Override
            public void onClick(Noticia n) {
                Bundle b = new Bundle();
                b.putSerializable(Constantes.NOTICIA, n);
                show(DetalheNoticiaActivity.class, b);
            }
        };
    }
}
