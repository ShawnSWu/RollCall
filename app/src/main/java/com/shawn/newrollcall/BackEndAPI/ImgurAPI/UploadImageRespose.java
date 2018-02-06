package com.shawn.newrollcall.BackEndAPI.ImgurAPI;

/**
 * Created by Shawn Wu on 2018/2/1.
 */

public class UploadImageRespose {
    public boolean success;
    public int status;
    public UploadedImages data;

    public static class UploadedImages {
        public String id;
        public String title;
        public String description;
        public String type;
        public boolean animated;
        public int width;
        public int height;
        public int size;
        public int views;
        public int bandwidth;
        public String vote;
        public boolean favorite;
        public String account_url;
        public String deletehash;
        public String name;
        public String link;


        public String getImageLink() {
            return link;

        }

    }

}
