package com.cse.cynosure.Model;

public class EventModel {
    public EventModel(){

    }
    private int userPic;
    private String eventname,eventdesc,coname,coname2;

    public int getUserPic() {
        return userPic;
    }

    public void setUserPic(int userPic) {
        this.userPic = userPic;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdesc() {
        return eventdesc;
    }

    public void setEventdesc(String eventdesc) {
        this.eventdesc = eventdesc;
    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        this.coname = coname;
    }

    public String getConame2() {
        return coname2;
    }

    public void setConame2(String coname2) {
        this.coname2 = coname2;
    }
}
