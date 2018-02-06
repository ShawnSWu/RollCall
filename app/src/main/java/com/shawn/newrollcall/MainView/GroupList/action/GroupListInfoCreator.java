package com.shawn.newrollcall.MainView.GroupList.action;

import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.MainView.GroupList.event.CreateGroupEvent;
import com.shawn.newrollcall.MainView.GroupList.event.DeleteGroupEvent;
import com.shawn.newrollcall.MainView.GroupList.event.GetGroupListInfoEvent;
import com.shawn.newrollcall.MainView.GroupList.event.GetGroupListResponse;

import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2018/2/3.
 */

public class GroupListInfoCreator extends FluxActionCreator {

    public void getGroupListInfomation(String account) {
        addAction(newAction(GroupListInfoType.GET_GROUP_LIST_INFO,new GetGroupListInfoEvent(account)));
    }

    public void getGroupListInfomationSuccess(ArrayList<GetGroupListResponse> getGroupListResponses) {
        addAction(newAction(GroupListInfoType.GET_GROUP_LIST_INFO_SUCCESS,getGroupListResponses));
    }

    public void updateSelectImage(int position) {
        addAction(newAction(GroupListInfoType.UPDATE_SELECT_IMAGE,position));
    }

    public void createGroup(String account,String listname,String group_image_uri) {
        addAction(newAction(GroupListInfoType.CREATE_GROUP,new CreateGroupEvent(account,listname,group_image_uri)));
    }

    public void createGroupSuccess() {
        addAction(newAction(GroupListInfoType.CREATE_GROUP_SUCCESS));
    }

    public void createGroupFail() {
        addAction(newAction(GroupListInfoType.CREATE_GROUP_FAIL));
    }

    public void deleteGroup(String account,String listname) {
        addAction(newAction(GroupListInfoType.DELETE_GROUP,new DeleteGroupEvent(account,listname)));
    }

    public void deleteGroupSuccess() {
        addAction(newAction(GroupListInfoType.DELETE_GROUP_SUCCESS));
    }


}
