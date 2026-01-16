package com.example.ScreenSound.service;

import com.example.ScreenSound.model.Artist;
import com.example.ScreenSound.model.GenreMusic;
import com.example.ScreenSound.model.Music;
import com.example.ScreenSound.repository.ArtistRepository;
import com.example.ScreenSound.repository.MusicRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicService {
    private MusicRepository musicRepository;
    private ArtistRepository artistRepository;
    private Scanner inputUser = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void registerMusic(){
        Music musicRegistred = new Music();
        Artist artist = new Artist();
        List<Artist> artistsRegistred = new ArrayList<>();

        System.out.println("Please type the music name: ");
        var musicNameInputed = inputUser.nextLine();
        musicRegistred.setMusicName(musicNameInputed);

        System.out.println("Please type the duration music: ");
        var musicDurationInputed = inputUser.nextInt();
        musicRegistred.setMusicDuration(musicDurationInputed);

        System.out.println("Please type the artist/s music: ");
        var musicArtists = inputUser.nextLine();
        var artistVerification = artistRepository.searchArtists().contains(artist.getArtistName().contains(musicArtists));
        if (artistVerification){
            artist.setArtistName(musicArtists);
            artistsRegistred.add(artist);
            musicRegistred.setArtists(artistsRegistred);
        }

        System.out.println("Please type the genre music: ");
        var musicGenre = inputUser.nextLine();
        GenreMusic genreMusic = GenreMusic.valueOf(musicGenre);
        musicRegistred.setGenreMusic(genreMusic);

        try {
            musicRepository.save(musicRegistred);
            System.out.println("Music registred with sucess!");
        } catch (RuntimeException e){
            System.out.println("Music was not registered in the database.");
        }
    }

    public void searchMusics(){
        try {
            var musicsSearched = musicRepository.searchMusics();
            musicsSearched.forEach(m -> System.out.printf("Music name: %s \nMusic duration: %d \nArtists: %s \nMusic genre: %s",
                    m.getMusicName(), m.getMusicDuration(), m.getArtists(), m.getGenreMusic()));
        } catch (RuntimeException e){
            System.out.println("The music don't register in the database.");
        }
    }

    public void searchMusicPerName(){
        try{
            System.out.println("Please type the music name: ");
            var musicInputed = inputUser.nextLine();
            var musicSearched = musicRepository.searchMusicPerName(musicInputed);
            musicSearched.stream()
                    .forEach(m -> System.out.printf("Music name: %s \nMusic duration: %d \nArtists: %s \nMusic genre: %s",
                            m.getMusicName(), m.getMusicDuration(), m.getArtists(), m.getGenreMusic()));
        } catch (RuntimeException e){
            System.out.println("The music don't register in the database.");
        }
    }

    public void searchMusicPerGenre(){
        try {
            System.out.println("Please type the music genre: ");
            var genreInputed = inputUser.nextLine();
            var musicSearched = musicRepository.searchMusicsPerGenre(genreInputed);
            musicSearched.stream()
                    .forEach(m -> System.out.printf("Music name: %s \nMusic duration: %d \nArtists: %s \nMusic genre: %s",
                            m.getMusicName(), m.getMusicDuration(), m.getArtists(), m.getGenreMusic()));
        } catch (RuntimeException e){
            System.out.println("The music don't register in the database.");
        }
    }

    public void searchMusicByDuration(){
        try {
            System.out.println("Please the duration the music: ");
            var durationMusic = inputUser.nextInt();
            var musicSearched = musicRepository.searchMusicByDuration(durationMusic);
            musicSearched.stream()
                    .forEach(m -> System.out.printf("Music name: %s \nMusic duration: %d \nArtists: %s \nMusic genre: %s",
                            m.getMusicName(), m.getMusicDuration(), m.getArtists(), m.getGenreMusic()));
        } catch (RuntimeException e){
            System.out.println("The music don't register in the database.");
        }
    }

    public void searchMusicByArtists(){
        try {
            System.out.println("Please type the artists sings this music: ");
            var artistInputed = inputUser.nextLine();
            var musicSearched = musicRepository.searchMusicByArtists(artistInputed);
            musicSearched.stream()
                    .forEach(m -> System.out.printf("Music name: %s \nMusic duration: %d \nArtists: %s \nMusic genre: %s",
                            m.getMusicName(), m.getMusicDuration(), m.getArtists(), m.getGenreMusic()));
        } catch (RuntimeException e){
            System.out.println("The music don't register in the database.");
        }
    }
}
