package noticias.globo.com.br.noticiasglobo.domain;

import java.util.List;

import br.livetouch.db.Entity;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class Conteudo extends Entity {

    public Long id;
    public List<Noticia> conteudos;
    public String produto;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
