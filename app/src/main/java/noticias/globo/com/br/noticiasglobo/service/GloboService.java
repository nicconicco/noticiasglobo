package noticias.globo.com.br.noticiasglobo.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import br.livetouch.utils.AndroidUtils;
import noticias.globo.com.br.noticiasglobo.R;
import noticias.globo.com.br.noticiasglobo.activity.BaseActivity;
import noticias.globo.com.br.noticiasglobo.domain.Conteudo;
import noticias.globo.com.br.noticiasglobo.domain.Noticia;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class GloboService extends BaseActivity{

    public static List<Noticia> getNoticiasJson() throws IOException {
        String noticias = br.livetouch.utils.FileUtils.getFileString(AndroidUtils.getContext(), R.raw.capa);
        return jsonToObject(noticias);
    }

    private static List<Noticia> jsonToObject(String json) {

        //Conteudo conteudos = new Gson().fromJson(json, Conteudo.class);

        Type collectionType = new TypeToken<List<Conteudo>>(){}.getType();

        List<Conteudo> conteudos = (List<Conteudo>) new Gson()
                .fromJson( json , collectionType);

        if (conteudos.size() > 0) {
            return conteudos.get(0).conteudos;
        } else {
            return null;
        }
    }
}
