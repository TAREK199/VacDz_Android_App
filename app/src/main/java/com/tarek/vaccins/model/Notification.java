package com.tarek.vaccins.model;



public class Notification {

    private String notificationActe ;
    private String notificationMsg ;


    public Notification(String notificationActe, String notificationMsg) {
        this.notificationActe = notificationActe;
        this.notificationMsg = notificationMsg;
    }

    public String getNotificationActe() {
        return notificationActe;
    }

    public void setNotificationActe(String notificationActe) {
        this.notificationActe = notificationActe;
    }

    public String getNotificationMsg() {
        return notificationMsg;
    }

    public void setNotificationMsg(String notificationMsg) {
        this.notificationMsg = notificationMsg;
    }


}
