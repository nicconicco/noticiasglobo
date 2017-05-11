package noticias.globo.com.br.noticiasglobo.domain;

import java.util.List;

import br.livetouch.db.Entity;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class Noticia extends Entity {

    public List<String> autores;
    public String informePublicitario;
    public String subTitulo;
    public String texto;
    public List<String> videos;
    public String atualizadoEm;
    public Long id;
    public String publicadoEm;
    public Secao secao;
    public String tipo;
    public String titulo;
    public String url;
    public String urlOriginal;
    public List<Imagem> imagens;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
