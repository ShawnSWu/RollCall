package com.shawn.newrollcall.ScanBLEModel.event;

/**
 * Created by Shawn Wu on 2017/12/10.
 *
 */

public class GroupItemDataResponse {

    private String group_image_uri;

    private int people_count;

    public GroupItemDataResponse(String group_image_uri, Integer people_count) {
        this.people_count = people_count;
        this.group_image_uri = group_image_uri;
    }

    public String getGroup_image_uri() {
        return group_image_uri;
    }

    public int getPeople_count() {
        return people_count;
    }
}
