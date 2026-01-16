package com.example.ScreenSound.model;


public enum GenreMusic {
    SERTANEJO,
    POP,
    ROCK,
    TRAP,
    FUNK,
    SAMBA,
    MPB,
    RAP,
    BLUES,
    JAZZ;

    public static GenreMusic fromString(String text){
        for(GenreMusic genreMusic: GenreMusic.values()){
            if(genreMusic.name().equalsIgnoreCase(text.trim())){
                return genreMusic;
            }
        }
        throw new IllegalArgumentException("Any genre is found, please try again.");
    }
}
