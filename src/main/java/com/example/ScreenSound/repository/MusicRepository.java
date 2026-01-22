package com.example.ScreenSound.repository;

import com.example.ScreenSound.model.Artist;
import com.example.ScreenSound.model.Music;
import dev.langchain4j.agent.tool.P;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    @Query("select m from Music m join fetch m.artist")
    List<Music> searchMusics();
    @Query("select m from Music m where m.musicName like :musicName")
    Optional<Music> searchMusicPerName(@Param("musicName") String musicName);
    @Query("select m from Music m where lower(m.genreMusic) like lower((concat('%', :genreMusic, '%')))")
    List<Music> searchMusicsPerGenre(@Param("genreMusic") String genreMusic);
    @Query("select m from Music m where m.musicDuration =:duration order by m.musicDuration desc ")
    List<Music> searchMusicByDuration(int duration);
    @Query("select m from Music m join fetch m.artist a where a.artistName ilike :artist")
    List<Music> searchMusicByArtists(@Param("artist") String artist);

}
