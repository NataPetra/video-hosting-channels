package by.nata.videohostingchannels.service;

import by.nata.videohostingchannels.database.model.AppUser;
import by.nata.videohostingchannels.database.model.Chanel;
import by.nata.videohostingchannels.database.repository.AppUserRepository;
import by.nata.videohostingchannels.service.dto.AppUserRequestDto;
import by.nata.videohostingchannels.service.dto.AppUserResponseDto;
import by.nata.videohostingchannels.service.mapper.AppUserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository userRepository;
    private final AppUserMapper appUserMapper;

    public AppUserResponseDto saveOrUpdateUser(AppUserRequestDto userDto) {
        return Optional.of(userDto)
                .map(appUserMapper::dtoToEntity)
                .map(userRepository::save)
                .map(appUserMapper::entityToDto)
                .orElseThrow(() -> new IllegalArgumentException("Failed to save or update user"));
    }

    public List<String> getUserSubscriptions(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<Chanel> subscribedChannels = user.getSubscribedChannels();

        return subscribedChannels.stream().map(Chanel::getName).toList();
    }

    public AppUser getAppUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found with id: " + userId));
    }

    public boolean isAppUserExist(Long id) {
        return userRepository.existsById(id);
    }
}
