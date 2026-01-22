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
        var musicNameInputed = inputUser.nextLine().toLowerCase();
        musicRegistred.setMusicName(musicNameInputed);

        System.out.println("Please type the duration music (in seconds): ");
        var musicDurationInputed = inputUser.nextInt();
        if (musicDurationInputed < 60 || musicDurationInputed > 7200 ){
            throw new RuntimeException("This duration is out of patterns. Please try again or other music.");
        }
        musicRegistred.setMusicDuration(musicDurationInputed);
        inputUser.nextLine();

        System.out.println("Please type the artist/s music: ");
        var musicArtists = inputUser.nextLine().toLowerCase();
        try {
            var artistVerification = artistRepository.searchArtistByName(musicArtists);
            if (artistVerification.isPresent()){
                artistVerification.get().getArtistName().equalsIgnoreCase(musicArtists);
                musicRegistred.setArtist(artistVerification.get());
            }
        } catch (RuntimeException e){
            System.out.println("Artist don't exist in database. Please try again.");
            return;
        }

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
        }
    }

    public void searchMusics(){
        try {
            var musicsSearched = musicRepository.searchMusics();
            musicsSearched.forEach(m -> System.out.printf("\nMusic name: %s \nMusic duration (in seconds): %.2f\nArtists: %s \nMusic genre: %s\n",
                        m.getMusicName(), m.getMusicDuration(),m.getArtist() ,m.getGenreMusic()));
        } catch (RuntimeException e){
            System.out.println("The music don't register in the database.");
        }
    }

    public void searchMusicPerName(){
        try{
            System.out.println("Please type the music name: ");
            var musicInputed = inputUser.nextLine();
            var musicSearched = musicRepository.searchMusicPerName(musicInputed);
            if (musicSearched.get().getMusicName().equalsIgnoreCase(musicInputed)){
                musicSearched.stream().forEach(m ->
                        System.out.printf("\nMusic name: %s \nMusic duration (in seconds): %.2f \nArtists: %s \nMusic genre: %s\n",
                                m.getMusicName(), m.getMusicDuration(), m.getArtist(), m.getGenreMusic())
                );
            }
        } catch (RuntimeException e){
            System.out.println("The music don't register in the database.");
        }
    }

    public void searchMusicPerGenre(){
        try {
            System.out.println("Please type the music genre: ");
            var genreInputed = inputUser.nextLine();
            var musicSearched = musicRepository.searchMusicsPerGenre(genreInputed);
            System.out.println(musicSearched);
            musicSearched
                    .forEach(m ->
                        System.out.printf("\nMusic name: %s \nMusic duration (in seconds): %.2f \nArtists: %s \nMusic genre: %s\n",
                                m.getMusicName(), m.getMusicDuration(), m.getArtist(), m.getGenreMusic())
                    );
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
                    .forEach(m -> System.out.printf("\nMusic name: %s \nMusic duration (in seconds): %.2f \nArtists: %s \nMusic genre: %s\n",
                            m.getMusicName(), m.getMusicDuration(), m.getArtist(), m.getGenreMusic()));
        } catch (RuntimeException e){
            System.out.println("The music with this duration don't register in the database.");
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
                        System.out.printf("\nMusic name: %s \nMusic duration (in seconds): %.2f \nArtists: %s \nMusic genre: %s\n",
                                m.getMusicName(), m.getMusicDuration(), m.getArtist(), m.getGenreMusic());
                    });
        } catch (RuntimeException e){
            System.out.println("The music don't register in the database.");
        }
    }
}
