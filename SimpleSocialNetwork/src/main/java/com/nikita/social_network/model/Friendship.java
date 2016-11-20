package com.nikita.social_network.model;

/**
 * Created by nikita on 17.11.2016.
 */
public class Friendship {
    private String from;
    private String to;
    private String status;
    private String nameFrom;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", status='" + status + '\'' +
                ", nameFrom='" + nameFrom + '\'' +
                '}';
    }
}
