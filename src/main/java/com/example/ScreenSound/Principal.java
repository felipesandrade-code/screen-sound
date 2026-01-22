package com.example.ScreenSound;

import com.example.ScreenSound.service.ArtistService;
import com.example.ScreenSound.service.GeminiSearch;
import com.example.ScreenSound.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {
    private Scanner inputScanner = new Scanner(System.in);
    @Autowired
    private final ArtistService artistService;
    @Autowired
    private final MusicService musicService;
    private GeminiSearch geminiSearch;

    public Principal(ArtistService artistService, MusicService musicService) {
        this.artistService = artistService;
        this.musicService = musicService;
    }

    public void exibeMenu(){
        var menu = """
                *** Screen Sound Musicas ***
                
                1 - Register artists.
                2 - Register Musics.
                3 - List musics.
                4 - List artists
                5 - Search musics by artist.
                6 - Search musics by genre.
                7 - Search information about artist.
              
                0 - exit
                """;
        int optionInput = -1;

        while (optionInput != 0) {
            System.out.println(menu);
            optionInput = inputScanner.nextInt();
            switch (optionInput){
                case 1:
                    artistService.registerArtist();
                    break;
                case 2:
                    musicService.registerMusic();
                    break;
                case 3:
                    musicService.searchMusics();
                    break;
                case 4:
                    artistService.searchArtists();
                    break;
                case 5:
                    musicService.searchMusicByArtists();
                    break;
                case 6:
                    musicService.searchMusicPerGenre();
                    break;
                case 7:
                    artistService.buildBioArtist();
                    break;
                case 0:
                    System.out.println("Saindo....");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
