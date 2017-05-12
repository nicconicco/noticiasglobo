package noticias.globo.com.br.noticiasglobo;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.livetouch.BaseApplication;
import br.livetouch.db.DatabaseHelper;
import noticias.globo.com.br.noticiasglobo.domain.Noticia;

/**
 * Created by nicolaugalves on 11/05/17.
 */

public class NoticiasGloboApplication extends BaseApplication{


    public static NoticiasGloboApplication getInstance() {
        return (NoticiasGloboApplication) BaseApplication.getInstance();
    }

    private List<Noticia> noticias = new ArrayList<>();

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    @Override
    public String getGCMProjectNumber() {
        return null;
    }

    @Override
    public void onGCMMessage(Bundle bundle) {

    }

    @Override
    public String getBuildType() {
        return BuildConfig.BUILD_TYPE;
    }

    @Override
    public String getBuildFlavor() {
        return null;
    }

    @Override
    public String getTag() {
        return getString(R.string.project_tag);
    }

    @Override
    public int getLogoAlert() {
        return 0;
    }

    @Override
    public int getProgressId() {
        return 0;
    }

    @Override
    public String getGoogleAnalyticsTrackingId() {
        return null;
    }

    @Override
    public String getCrashlyticsKey() {
        return null;
    }

    @Override
    public DatabaseHelper getDatabaseHelper() {
        return null;
    }


}
