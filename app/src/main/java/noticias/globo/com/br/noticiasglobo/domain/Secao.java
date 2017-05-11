package noticias.globo.com.br.noticiasglobo.domain;

import br.livetouch.db.Entity;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class Secao extends Entity {

    public Long id;
    public String nome;
    public String url;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
