package noticias.globo.com.br.noticiasglobo.domain;

import br.livetouch.db.Entity;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class Imagem extends Entity {

    public Long id;
    public String autor;
    public String fonte;
    public String legenda;
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
