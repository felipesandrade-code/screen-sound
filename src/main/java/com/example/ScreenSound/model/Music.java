package com.example.ScreenSound.model;

import jakarta.persistence.*;


@Entity
@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String musicName;

    private double musicDuration;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Artist artist;

    @Enumerated(value = EnumType.STRING)
    private GenreMusic genreMusic;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public long getId() {
        return id;
    }

    public double getMusicDuration() {
        return musicDuration;
    }

    public void setMusicDuration(int musicDuration) {
        this.musicDuration = musicDuration;
    }

    public GenreMusic getGenreMusic() {
        return genreMusic;
    }

    public void setGenreMusic(GenreMusic genreMusic) {
        this.genreMusic = genreMusic;
    }

    @Override
    public String toString() {
        return "\nmusicName = " + musicName  +
                ", \nmusicDuration = " + musicDuration +
                " seconds, \nartist = " + artist +
                ", \ngenreMusic = " + genreMusic + "\n";
    }
}
