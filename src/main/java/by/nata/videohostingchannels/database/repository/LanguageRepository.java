package by.nata.videohostingchannels.database.repository;

import by.nata.videohostingchannels.database.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query("SELECT l.name FROM Language l WHERE l.id = :languageId")
    String findLanguageNameById(@Param("languageId") Long languageId);
}
