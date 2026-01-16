package com.example.ScreenSound.repository;

import com.example.ScreenSound.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    @Query("select all(a) from Artist a")
    List<Artist> searchArtists();
    @Query("select a from Artist a where a.artistName = :name")
    Optional<Artist> searchArtistByName(@Param("name") String name);
    @Query("select a from Artist a where a.birthday = :date")
    List<Artist> searchArtistsByBirthday(@Param("date") LocalDate date);
    @Query("select a from Music m JOIN m.artists a where m.musicName ilike %:music%")
    List<Artist> searchArtistsByMusic(String music);

}
