package com.example.android.actionbarcompat.styled;

import java.util.Date;



public class IoteTag {

    private String tagId;       // globally unique
    private String userId;
    private String name;        // displayed name
    private Date lastSeen;


    public IoteTag(String tagId, String userId, String name) {

        // todo: figure out what to do with userId
        this.userId = "no one";
        this.tagId = tagId;
        this.name = name;

    }



}
