package noticias.globo.com.br.noticiasglobo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import noticias.globo.com.br.noticiasglobo.R;
import noticias.globo.com.br.noticiasglobo.domain.Noticia;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    private static final int TYPE_NOTICIA = 1;
    private final Context context;
    private final List<Noticia> noticias;

    public interface OnClickListener {
        public void onClick(Noticia n);
    }
    private OnClickListener onClickListener;

    public NoticiasAdapter(Context context, List<Noticia> noticias, OnClickListener onClickListener) {
        this.context = context;
        this.noticias = noticias;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_noticia, parent, false);
        return new ViewHolderNoticia(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        final Noticia noticia = noticias.get(position);
        if (viewHolder instanceof ViewHolderNoticia) {

            final ViewHolderNoticia viewHolderNoticia = (ViewHolderNoticia) viewHolder;

            if (noticia.secao != null ) {
                if (noticia.secao.nome != null) {
                    viewHolderNoticia.tTitulo.setText(noticia.secao.nome);
                }
            }

            if (noticia.subTitulo!= null ) {
                viewHolderNoticia.tSubtitulo.setText(noticia.titulo);
            }


            if (noticia.imagens != null) {
                if (noticia.imagens.size() > 0) {
                    Picasso.with(context)
                            .load(noticia.imagens.get(0).url)
                            .resize(100, 70)
                            .centerCrop()
                            .into(viewHolderNoticia.img);
                }
            }

            viewHolderNoticia.btnCelula.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickListener != null) {
                        onClickListener.onClick(noticia);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return noticias != null ? noticias.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {

        return TYPE_NOTICIA;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class ViewHolderNoticia extends ViewHolder {

        LinearLayout btnCelula;
        TextView tTitulo;
        TextView tSubtitulo;
        ImageView img;

        public ViewHolderNoticia(View view) {
            super(view);

            btnCelula = (LinearLayout) view.findViewById(R.id.btnCelula);
            img = (ImageView) view.findViewById(R.id.img);
            tTitulo = (TextView) view.findViewById(R.id.tTitulo);
            tSubtitulo = (TextView) view.findViewById(R.id.tSubtitulo);
        }
    }
}
