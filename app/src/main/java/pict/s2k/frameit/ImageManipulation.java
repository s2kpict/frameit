package pict.s2k.frameit;

import android.net.Uri;

/**
 * Created by suryaa on 5/9/17.
 */

public class ImageManipulation {

    public static String storeAndGetThumbnail(Uri phtotToStore) {

        //Create a Thumnail and then Store to Storage Bucket and then return the download Url in string
        if(phtotToStore!=null)
            return phtotToStore.getPath() ;
        else
            return "" ;
    }

}
