package com.shawn.newrollcall.MainView.GroupList.event;

/**
 * Created by Shawn Wu on 2017/12/3.
 *
 */

public class GetGroupListResponse {

    private String listname;

    private int people_count;

    private String group_image_uri;

    public GetGroupListResponse(String listname, int people_count, String group_image_uri) {
        this.listname = listname;
        this.people_count = people_count;
        this.group_image_uri = group_image_uri;
    }

    public String getListname() {
        return listname;
    }

    public int getPeople_count() {
        return people_count;
    }

    public String getGroup_image_uri() {
        return group_image_uri;
    }
}
