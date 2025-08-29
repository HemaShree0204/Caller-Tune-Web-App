package CRBT.Tunes_Service.Respository;

import CRBT.Tunes_Service.Model.Tunes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface Tune_Respository extends JpaRepository<Tunes, Long> {
    List<Tunes> findByGenreIgnoreCase(String genre);
    List<Tunes> findByLanguageIgnoreCase(String language);
    List<Tunes> findByPrice(double price);
    Optional<Tunes> findByTitleIgnoreCase(String title); // ✅ change here
    Optional<Tunes> findByFileNameIgnoreCase(String fileName);
}

