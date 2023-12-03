package by.nata.videohostingchannels.service;

import by.nata.videohostingchannels.database.model.AppUser;
import by.nata.videohostingchannels.database.model.Chanel;
import by.nata.videohostingchannels.database.repository.AppUserRepository;
import by.nata.videohostingchannels.database.repository.ChanelRepository;
import by.nata.videohostingchannels.service.dto.AppUserRequestDto;
import by.nata.videohostingchannels.service.dto.AppUserResponseDto;
import by.nata.videohostingchannels.service.mapper.AppUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository userRepository;
    private final ChanelRepository channelRepository;
    private final AppUserMapper appUserMapper;

    public AppUserResponseDto saveOrUpdateUser(AppUserRequestDto userDto) {
        return Optional.of(userDto)
                .map(appUserMapper::dtoToEntity)
                .map(userRepository::save)
                .map(appUserMapper::entityToDto)
                .orElseThrow(() -> new IllegalArgumentException("Failed to save or update user"));
    }

    public void subscribeToChannel(Long userId, Long channelId) {
        AppUser user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Chanel channel = channelRepository.findById(channelId).orElseThrow(EntityNotFoundException::new);

        List<Chanel> subscribedChannels = user.getSubscribedChannels();
        subscribedChannels.add(channel);

        user.setSubscribedChannels(subscribedChannels);
        userRepository.save(user);
    }

    public void unsubscribeFromChannel(Long userId, Long channelId) {
        AppUser user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Chanel channel = channelRepository.findById(channelId).orElseThrow(EntityNotFoundException::new);

        List<Chanel> subscribedChannels = user.getSubscribedChannels();
        subscribedChannels.remove(channel);

        user.setSubscribedChannels(subscribedChannels);
        userRepository.save(user);
    }

    public List<String> getUserSubscriptions(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<Chanel> subscribedChannels = user.getSubscribedChannels();

        return subscribedChannels.stream().map(Chanel::getName).toList();
    }
}
