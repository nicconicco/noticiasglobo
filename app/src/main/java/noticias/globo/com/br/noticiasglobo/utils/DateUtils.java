package noticias.globo.com.br.noticiasglobo.utils;

import java.text.SimpleDateFormat;

import br.livetouch.utils.LogUtil;

/**
 * Created by nicolaugalves on 12/05/17.
 */

public class DateUtils {
    private static final java.lang.String TAG = "DateUtils";

    public static String formatDataGlobo(String date) {

        String dateformat = "";
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        //2017-03-08T13:25:03-0300
        //"yyyy-MM-dd'T'HH:mm:ss'Z'");

        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            dateformat = myFormat.format(fromUser.parse(date));
        } catch (Exception e) {
            LogUtil.logError(TAG, e.getMessage(), e);
        }

        return dateformat;
    }
}
