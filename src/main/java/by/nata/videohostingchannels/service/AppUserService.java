package by.nata.videohostingchannels.service;

import by.nata.videohostingchannels.controller.exception.UserAlreadyExistException;
import by.nata.videohostingchannels.database.model.AppUser;
import by.nata.videohostingchannels.database.model.Chanel;
import by.nata.videohostingchannels.database.repository.AppUserRepository;
import by.nata.videohostingchannels.service.dto.AppUserRequestDto;
import by.nata.videohostingchannels.service.dto.AppUserResponseDto;
import by.nata.videohostingchannels.service.mapper.AppUserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository userRepository;
    private final AppUserMapper appUserMapper;

    @Transactional
    public AppUserResponseDto saveUser(AppUserRequestDto userDto) {
        if (Objects.nonNull(findByLogin(userDto.login()))) {
            throw new UserAlreadyExistException(String.format("User with login %s already exist", userDto.login()));
        }
        return Optional.of(userDto)
                .map(appUserMapper::dtoToEntity)
                .map(userRepository::save)
                .map(appUserMapper::entityToDto)
                .orElseThrow(() -> new IllegalArgumentException("Failed to save user"));
    }

    @Transactional
    public AppUserResponseDto updateUser(AppUserRequestDto userDto, Long id) {
        AppUser existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        existingUser.setLogin(userDto.login());
        existingUser.setName(userDto.name());
        existingUser.setEmail(userDto.email());

        AppUser updatedUser = userRepository.save(existingUser);
        return appUserMapper.entityToDto(updatedUser);
    }

    @Transactional(readOnly = true)
    public List<String> getUserSubscriptions(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<Chanel> subscribedChannels = user.getSubscribedChannels();

        return subscribedChannels.stream().map(Chanel::getName).toList();
    }

    @Transactional(readOnly = true)
    public AppUser getAppUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("AppUser not found with id: " + userId));
    }

    @Transactional
    public boolean isAppUserExist(Long id) {
        return userRepository.existsById(id);
    }

    public AppUser findByLogin(String login) {
        return userRepository.findAppUserByLogin(login);
    }
}
