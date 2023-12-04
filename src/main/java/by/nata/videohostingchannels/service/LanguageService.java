package by.nata.videohostingchannels.service;

import by.nata.videohostingchannels.database.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    public boolean isLanguageExist(Long id) {
        return languageRepository.existsById(id);
    }

    public String getLanguageName(Long id) {
        return languageRepository.findLanguageNameById(id);
    }
}
