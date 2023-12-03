package by.nata.videohostingchannels.database.repository;

import by.nata.videohostingchannels.database.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
