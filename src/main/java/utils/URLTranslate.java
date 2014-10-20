package utils;


import constants.Constants;

/**
 * Created by Hu on 2014/10/20 0020.
 */
public class URLTranslate {

    public static String translateToDownloadURL(String url){
        Integer start = url.lastIndexOf(Constants.SPLIT_EQUAL_STRING) + Constants.ONE_VALUE;
        String imgCode = url.substring(start);
        String downloadURL = Constants.DOWNLOAD_IMG_PREFIX + imgCode + Constants.JPG_SUFFIX;
        return downloadURL;
    }

    public static String translateToUserWeiboPath(String userName){
        return Constants.WEIBO_PREFIX + userName + Constants.PAGE_INFIX;
    }
}
