package com.example.ScreenSound;

import com.example.ScreenSound.service.ArtistService;
import com.example.ScreenSound.service.MusicService;

public class Principal {

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
        int opcao = -1;
        MusicService musicService = new MusicService();
        ArtistService artistService = new ArtistService();

        while (opcao != 0) {
            System.out.println(menu);
            switch (opcao){
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
