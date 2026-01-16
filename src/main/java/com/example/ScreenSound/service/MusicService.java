package com.example.ScreenSound.service;

import com.example.ScreenSound.model.Artist;
import com.example.ScreenSound.model.GenreMusic;
import com.example.ScreenSound.model.Music;
import com.example.ScreenSound.repository.ArtistRepository;
import com.example.ScreenSound.repository.MusicRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


@Service
public class MusicService {
    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;
    private final Scanner inputUser = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MusicService(MusicRepository musicRepository, ArtistRepository artistRepository) {
        this.musicRepository = musicRepository;
        this.artistRepository = artistRepository;
    }

    @Transactional
    public void registerMusic(){
        Music musicRegistred = new Music();

        System.out.println("Please type the music name: ");
        var musicNameInputed = inputUser.nextLine();
        musicRegistred.setMusicName(musicNameInputed);

        System.out.println("Please type the duration music (in seconds): ");
        var musicDurationInputed = inputUser.nextInt();
        if (musicDurationInputed < 60 || musicDurationInputed > 7200 ){
            throw new RuntimeException("This duration is out of patterns. Please try again or other music.");
        }
        musicRegistred.setMusicDuration(musicDurationInputed);
        inputUser.nextLine();
        System.out.println("Please type the artist/s music: ");
        var musicArtists = inputUser.nextLine();
        var artistVerification = artistRepository.searchArtistByName(musicArtists);
        artistVerification.ifPresent(artistExist -> musicRegistred.setArtists(List.of(artistExist)));

        System.out.println("Please type the genre music: ");
        var musicGenre = inputUser.nextLine().toLowerCase();
        try{
            GenreMusic genreMusic = GenreMusic.fromString(musicGenre);
            musicRegistred.setGenreMusic(genreMusic);
        } catch (RuntimeException e){
            System.out.println("The genre don't exist, please try again.");
            return;
        }

        try {
            musicRepository.save(musicRegistred);
            System.out.println("Music registred with sucess!");
        } catch (RuntimeException e){
            System.out.println("Music was not registered in the database. Because: " + e.getMessage());
            return;
        }
    }

    public void searchMusics(){
        try {
            var musicsSearched = musicRepository.searchMusics();
            musicsSearched.forEach(m -> {
                String artists = m.getArtists().stream()
                                .map(Artist::getArtistName)
                                        .collect(Collectors.joining(", "));

                System.out.printf("Music name: %s \nMusic duration: %.2f \nArtists: %s \nMusic genre: %s\n",
                        m.getMusicName(), m.getMusicDuration(),artists ,m.getGenreMusic());
            });
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
                    .forEach(m -> {

                        var artists = m.getArtists().stream()
                                        .map(Artist::getArtistName)
                                                .collect(Collectors.joining(", "));

                        System.out.printf("Music name: %s \nMusic duration: %d \nArtists: %s \nMusic genre: %s\n",
                                m.getMusicName(), m.getMusicDuration(), artists, m.getGenreMusic());
                    });
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
                    .forEach(m -> {

                        var artists = m.getArtists().stream()
                                        .map(Artist::getArtistName)
                                                .collect(Collectors.joining(", "));

                        System.out.printf("Music name: %s \nMusic duration: %.2f \nArtists: %s \nMusic genre: %s\n",
                                m.getMusicName(), m.getMusicDuration(), artists, m.getGenreMusic());
                    });
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
                    .forEach(m -> System.out.printf("Music name: %s \nMusic duration: %d \nArtists: %s \nMusic genre: %s\n",
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

            if (musicSearched.isEmpty()){
                System.out.println("no music found for this artist.");
                return;
            }

            musicSearched.stream()
                    .forEach(m -> {
                        var artists = m.getArtists().stream()
                                        .map(Artist::getArtistName)
                                                .collect(Collectors.joining(", "));

                        System.out.printf("Music name: %s \nMusic duration: %.2f \nArtists: %s \nMusic genre: %s\n",
                                m.getMusicName(), m.getMusicDuration(), artists, m.getGenreMusic());
                    });
        } catch (RuntimeException e){
            System.out.println("The music don't register in the database.");
        }
    }
}
