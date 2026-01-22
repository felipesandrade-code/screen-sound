package com.example.ScreenSound.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name =  "artist_name")
    private String artistName;

    private LocalDate birthday;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private List<Music> musics;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "genre_music")
    private GenreMusic genreMusic;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "artist_type")
    private ArtistType artistType;


    public Artist(String artistName, LocalDate birthday) {
        this.artistName = artistName;
        this.birthday = birthday;
    }

    public Artist() {}

    public long getId() {
        return id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    public GenreMusic getGenreMusic() {
        return genreMusic;
    }

    public void setGenreMusic(GenreMusic genreMusica) {
        this.genreMusic = genreMusica;
    }

    public ArtistType getTipoArtista() {
        return artistType;
    }

    public void setTipoArtista(ArtistType artistType) {
        this.artistType = artistType;
    }

    @Override
    public String toString() {
        return this.artistName;
    }
}
