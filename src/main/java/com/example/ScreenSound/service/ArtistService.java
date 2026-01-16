package com.example.ScreenSound.service;

import com.example.ScreenSound.model.Artist;
import com.example.ScreenSound.model.GenreMusic;
import com.example.ScreenSound.model.ArtistType;
import com.example.ScreenSound.repository.ArtistRepository;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Service
public class ArtistService {
    private Artist artist;
    private ArtistRepository artistRepository;
    private Scanner inputUser = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void registerArtist(){
        Artist artistRegister = new Artist();
        System.out.println("Please type the artist name: ");
        var artistName = inputUser.nextLine();
        artistRegister.setArtistName(artistName);

        System.out.println("Type the artist's birthday: (01/01/9999)");
        var artistBirthday = inputUser.nextLine();
        var birthdayFormated = formatter.parse(artistBirthday);
        LocalDate date = (LocalDate) birthdayFormated;
        artistRegister.setBirthday(date);

        System.out.println("Type the genre that this artist sings: ");
        var artistGenreSing = inputUser.nextLine();
        GenreMusic genreMusic = GenreMusic.valueOf(artistGenreSing);
        artistRegister.setGenreMusic(genreMusic);

        System.out.println("Type the type this artist: ");
        var artistType = inputUser.nextLine();
        ArtistType artistTypeFormat = ArtistType.valueOf(artistType);
        artistRegister.setTipoArtista(artistTypeFormat);

        try{
            artistRepository.save(artistRegister);
            System.out.println("artist registe with sucess!");
        } catch (RuntimeException e){
            System.out.println("It was not possible to save the artist in the database.");
        }
    }

    public void searchArtists(){
        var artists = artistRepository.searchArtists();
        artists.forEach(a -> System.out.printf("Artist name: %s " +
                "\nArtistBirthday: %tF " +
                "\nArtist musics: %s " +
                "\nSung genre: %s", a.getArtistName(), a.getBirthday(), a.getMusics(),a.getGenreMusic()));
    }

    public void searchArtistByName(){
        System.out.println("Type the artist name for search: ");
        var artistName = inputUser.nextLine();
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
        LocalDate birthday = LocalDate.parse(artistBirthday);
        birthday.format(formatter);
        try {
            var artist = artistRepository.searchArtistsByBirthday(birthday);
            System.out.println("Artist is found! Artist: " + artist);
        } catch (RuntimeException e){
            System.out.println("the artist with this birthday don't exist in database.");
        }
    }

    public void searchArtistsByMusic(){
        System.out.println("Type the music for search: ");
        var music = inputUser.nextLine();
        var artistsSearched = artistRepository.searchArtistsByMusic(music);
        artistsSearched.forEach(a -> System.out.printf("Artist name: %s" +
                "\nArtistBirthday: %tF \nSung genre: ", a.getArtistName(), a.getBirthday(), a.getGenreMusic()));
    }

    public void gerarBioDoArtista() {
        System.out.println("Please type the artist name: ");
        var artistName = inputUser.nextLine();
        OpenAiChatModel model = OpenAiChatModel.withApiKey("sk-proj-EDGVGvSaWUlRETGDJazSKJ2dYsqfIq912B7eLUUwrYk5Z3JG$5Z3JGsIDHGjrNR1q8MG3RbODa-YIqegT3BlbkFJFpPUSYrhfdhdlmMxfUvvm31OtHr1JqWcUdbHYO7$bHYO7YeeXa9K-xVJe6O--abfaKbfyYQ8O77wxDwA");
        String resposta = model.generate("Faça uma biografia curta do artista: " + artistName);
        System.out.println(resposta);
    }
}
