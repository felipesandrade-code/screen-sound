package com.example.ScreenSound.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String musicName;

    private double musicDuration;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "musicsArtists", joinColumns = @JoinColumn(name = "music_id"), inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> artists;

    @Enumerated(value = EnumType.STRING)
    private GenreMusic genreMusic;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
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
}
