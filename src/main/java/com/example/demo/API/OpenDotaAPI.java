package com.example.demo.API;

public class OpenDotaAPI {
    // https://api.opendota.com/api/matches/{match_id}
    private long match_id;
    private String picks_bans; // List of objects, how to access
    private String duration;

    public long getMatch_id() {
        return match_id;
    }

    public void setMatch_id(long match_id) {
        this.match_id = match_id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPicks_bans() {
        return picks_bans;
    }

    public void setPicks_bans(String picks_bans) {
        this.picks_bans = picks_bans;
    }
}
