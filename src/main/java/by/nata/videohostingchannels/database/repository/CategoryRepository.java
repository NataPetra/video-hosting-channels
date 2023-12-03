package by.nata.videohostingchannels.database.repository;

import by.nata.videohostingchannels.database.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c.name FROM Category c WHERE c.id = :categoryId")
    String findCategoryNameById(@Param("categoryId") Long categoryId);
}
