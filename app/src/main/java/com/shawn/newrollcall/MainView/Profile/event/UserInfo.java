package com.shawn.newrollcall.MainView.Profile.event;

/**
 * Created by Shawn Wu on 2017/11/31.
 *
 */

public class UserInfo {

    private String userName;

    private String userEmail;

    private String userProfileImage;

    public UserInfo(String userName, String userEmail,String userProfileImage) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userProfileImage = userProfileImage;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }
}
