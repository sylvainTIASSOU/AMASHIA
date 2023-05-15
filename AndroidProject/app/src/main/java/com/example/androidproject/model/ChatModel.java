package com.example.androidproject.model;

public class ChatModel
{
    private String message,date, time, mobile, name;

    public ChatModel(String message, String date, String time, String mobile, String name) {
        this.message = message;
        this.date = date;
        this.time = time;
        this.mobile = mobile;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }
}
