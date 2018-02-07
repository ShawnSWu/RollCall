package com.shawn.newrollcall.MainView.GroupList.view;

/**
 * Created by Shawn Wu on 2017/12/5.
 *
 */

public class CreateGroupImageSelectorItem {

    private String image_uri;
    private int resorce_image;

    public CreateGroupImageSelectorItem(int resorce_image,String image_uri) {
        this.resorce_image = resorce_image;
        this.image_uri = image_uri;
    }

    public String getImage_uri() {
        return image_uri;
    }

    public int getResorce_image() {
        return resorce_image;
    }

}
