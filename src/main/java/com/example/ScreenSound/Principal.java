package com.example.ScreenSound;

import com.example.ScreenSound.repository.ArtistRepository;
import com.example.ScreenSound.repository.MusicRepository;
import com.example.ScreenSound.service.ArtistService;
import com.example.ScreenSound.service.GeminiSearch;
import com.example.ScreenSound.service.MusicService;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {
    private Scanner inputScanner = new Scanner(System.in);
    private final ArtistService artistService;
    private final MusicService musicService;
    private GeminiSearch geminiSearch;

    public Principal(ArtistService artistService, MusicService musicService) {
        this.artistService = artistService;
        this.musicService = musicService;
    }

    public void exibeMenu(){
        var menu = """
                *** Screen Sound Musicas ***
                
                1 - Cadastrar artistas. 
                2 - Cadastrar Músicas.
                3 - Listar músicas.
                4 - Buscar músicas por artistas. 
                5 - Pesquisar dados sobre o artista.
                
                0 - sair  
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
                    musicService.searchMusicByArtists();
                    break;
                case 5:
                    artistService.gerarBioDoArtista();
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
