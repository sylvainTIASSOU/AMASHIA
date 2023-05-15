package com.example.androidproject.model;

public class UserModel
{
  private  String name, mobile, chatKey, lastMsg;
  private  int unseenMsg;

    public UserModel(String name, String mobile, String chatKey, int unseenMsg, String lastMsg) {
        this.name = name;
        this.mobile = mobile;
        this.chatKey = chatKey;
        this.unseenMsg = unseenMsg;
        this.lastMsg = lastMsg;

    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnseenMsg() {
        return unseenMsg;
    }

    public void setUnseenMsg(int unseenMsg) {
        this.unseenMsg = unseenMsg;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getChatKey() {
        return chatKey;
    }
}
