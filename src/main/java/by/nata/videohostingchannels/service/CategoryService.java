package by.nata.videohostingchannels.service;

import by.nata.videohostingchannels.database.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public boolean isCategoryExist(Long id) {
        return categoryRepository.existsById(id);
    }

    public String getCategoryName(Long id) {
        return categoryRepository.findCategoryNameById(id);
    }
}
