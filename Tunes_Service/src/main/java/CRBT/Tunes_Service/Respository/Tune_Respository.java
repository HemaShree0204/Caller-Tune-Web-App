package CRBT.Tunes_Service.Respository;

import CRBT.Tunes_Service.Model.Tunes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface Tune_Respository extends JpaRepository<Tunes, Long> {
    List<Tunes> findByGenre(String genre);
    List<Tunes> findByLanguage(String language);
    Optional<Tunes> findByTitle(String title); // ✅ change here
}

