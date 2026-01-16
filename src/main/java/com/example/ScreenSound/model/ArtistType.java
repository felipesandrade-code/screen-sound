package com.example.ScreenSound.model;

public enum ArtistType {
    SOLO,
    DUPLA,
    BANDA;


    public static ArtistType fromString(String string){
        for(ArtistType artistType : ArtistType.values()){
            if(artistType.name().equalsIgnoreCase(string.trim())){
                return artistType;
            }
        }
        throw new IllegalArgumentException("The type of artist don't exist, please try again.");
    }
}
