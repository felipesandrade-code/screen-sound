package com.example.ScreenSound.service;

import com.example.ScreenSound.model.Artist;
import com.example.ScreenSound.model.GenreMusic;
import com.example.ScreenSound.model.ArtistType;
import com.example.ScreenSound.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Service
public class ArtistService {
    @Autowired
    private final ArtistRepository artistRepository;
    private final Scanner inputUser = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final GeminiSearch geminiSearch;

    public ArtistService(ArtistRepository artistRepository, GeminiSearch geminiSearch) {
        this.artistRepository = artistRepository;
        this.geminiSearch = geminiSearch;
    }

    public void registerArtist(){
        Artist artistRegister = new Artist();
        System.out.println("Type the artist name: ");
        var artistName = inputUser.nextLine().toLowerCase();
        artistRegister.setArtistName(artistName);

        System.out.println("Type the artist's birthday: (01/01/9999)");
        var artistBirthday = inputUser.nextLine();
        try{
            LocalDate date = LocalDate.parse(artistBirthday, formatter);
            artistRegister.setBirthday(date);
        } catch (RuntimeException e){
            System.out.println("The date wrong type, please try again.");
            return;
        }

        System.out.println("Type the genre that this artist sings: ");
        var artistGenreSing = inputUser.nextLine().toLowerCase();
        try{
            GenreMusic genreMusic = GenreMusic.fromString(artistGenreSing);
            artistRegister.setGenreMusic(genreMusic);
        } catch (RuntimeException e ){
            System.out.println("Genre informed don't exist. Please try again.");
            return;
        }

        System.out.println("Type the type this artist: ");
        var artistType = inputUser.nextLine();
        try{
            ArtistType artistTypeFormat = ArtistType.fromString(artistType);
            artistRegister.setTipoArtista(artistTypeFormat);
        } catch (RuntimeException e){
            System.out.println("Artist type informed don't exist, please try again.");
            return;
        }

        try{
            saveArtist(artistRegister);
            System.out.println("artist registe with sucess!");
        } catch (RuntimeException e){
            System.out.println("It was not possible to save the artist in the database. Because: " + e.getMessage());
        }
    }

    public void searchArtists(){
        var artists = artistRepository.searchArtists();
        artists.forEach(a -> System.out.printf("\nArtist name: %s " +
                "\nArtistBirthday: %tF " +
                "\nArtist musics: %s " +
                "\nSung genre: %s\n", a.getArtistName(), a.getBirthday(), a.getMusics().toString(),a.getGenreMusic()));
    }

    public void searchArtistByName(){
        System.out.println("Type the artist name for search: ");
        var artistName = inputUser.nextLine().toLowerCase();
        var artistSearched = artistRepository.searchArtistByName(artistName);
        if (artistSearched.isPresent()){
            var artist = artistSearched.get();
            System.out.println("Artist searched! Artist: " + artist.getArtistName());
        } else {
            throw new RuntimeException("the artist don't exist in database.");
        }
    }

    public void searchArtistByBirthday(){
        System.out.println("Type the artist birthday for search: ");
        var artistBirthday = inputUser.nextLine();
        LocalDate birthday = LocalDate.parse(artistBirthday, formatter);
        try {
            var artist = artistRepository.searchArtistsByBirthday(birthday);
            System.out.println("Artist is found! Artist: " + artist);
        } catch (RuntimeException e){
            System.out.println("the artist with this birthday don't exist in database.");
        }
    }

    public void searchArtistsByMusic(){
        System.out.println("Type the music for search: ");
        var music = inputUser.nextLine().toLowerCase();
        var artistsSearched = artistRepository.searchArtistsByMusic(music);
        artistsSearched.forEach(a -> System.out.printf("\nArtist name: %s" +
                "\nArtistBirthday: %tF \nSung genre: ", a.getArtistName(), a.getBirthday(), a.getGenreMusic()));
    }

    public void buildBioArtist() {
        System.out.println("Please type the artist name: ");
        var artistName = inputUser.nextLine();
        try{
            var response = geminiSearch.obtainsInfo(artistName);
            System.out.println(response);
        } catch (RuntimeException e){
            System.out.println("Error consult gemini: " + e.getMessage());
            System.out.println("Please verify your connection or try again before");
        }
    }

    public void saveArtist(Artist artist){
        artistRepository.save(artist);
    }
}
