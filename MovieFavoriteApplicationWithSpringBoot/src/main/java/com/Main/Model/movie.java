package com.Main.Model;

import lombok.Data;

@Data
public class movie {
    private int id;
    private String name;
    private int time_release;
    private String certification;
    private String release;
    private String genre;
    private String runtime;
    private float user_score;
    private String tagline;
    private String overview;
    private String status;

    public movie(int id, String name, int time_release, String certification, String release, String genre, String runtime, float user_score, String tagline, String overview, String status) {
        this.id = id;
        this.name = name;
        this.time_release = time_release;
        this.certification = certification;
        this.release = release;
        this.genre = genre;
        this.runtime = runtime;
        this.user_score = user_score;
        this.tagline = tagline;
        this.overview = overview;
        this.status = status;
    }

    public movie(String name, String release, float user_score) {
        this.name = name;
        this.release = release;
        this.user_score = user_score;
    }
}
