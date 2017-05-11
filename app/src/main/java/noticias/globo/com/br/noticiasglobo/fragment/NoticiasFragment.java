package noticias.globo.com.br.noticiasglobo.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.livetouch.task.BaseTask;
import br.livetouch.task.Task;
import br.livetouch.utils.LogUtil;
import noticias.globo.com.br.noticiasglobo.R;
import noticias.globo.com.br.noticiasglobo.domain.Noticia;
import noticias.globo.com.br.noticiasglobo.service.GloboService;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class NoticiasFragment extends BaseFragment{

    private ListaNoticiasFragment frag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        Toolbar actionBar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if (actionBar != null) {
            actionBar.setTitle("");
            TextView title = (TextView) actionBar.findViewById(R.id.title);
            title.setText(R.string.noticias);
        }

        setHasOptionsMenu(true);

        Task task = taskGetNoticias();
        startTask(task);

        frag = new ListaNoticiasFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.container2, frag).commitNow();

        return view;
    }

    private Task taskGetNoticias() {
        return new BaseTask() {
            public List<Noticia> result;

            @Override
            public void execute() throws Exception {
                result = GloboService.getNoticiasJson();
            }

            @Override
            public void updateView() {
                if (result != null) {
                    LogUtil.log("json local pego com sucesso");
                }
            }
        };
    }
}
