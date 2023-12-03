package by.nata.videohostingchannels.service;

import by.nata.videohostingchannels.database.model.AppUser;
import by.nata.videohostingchannels.database.model.Chanel;
import by.nata.videohostingchannels.database.repository.ChanelRepository;
import by.nata.videohostingchannels.database.specification.ChanelSpecifications;
import by.nata.videohostingchannels.service.dto.ChanelDetailsDto;
import by.nata.videohostingchannels.service.dto.ChanelRequestDto;
import by.nata.videohostingchannels.service.dto.ChanelResponseDto;
import by.nata.videohostingchannels.service.dto.ChanelSummaryDto;
import by.nata.videohostingchannels.service.mapper.ChanelMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChanelService {

    private final ChanelRepository chanelRepository;
    private final ChanelMapper chanelMapper;
    private final AppUserService appUserService;

    public ChanelResponseDto createOrUpdateChanel(ChanelRequestDto chanelDto) {
        Chanel chanelEntity = chanelMapper.dtoToEntity(chanelDto);
        Chanel savedChanel = chanelRepository.save(chanelEntity);
        return chanelMapper.entityToDto(savedChanel);
    }

    public void subscribeUserToChanel(Long userId, Long chanelId) {
        AppUser user = appUserService.getAppUserById(userId);
        Chanel chanel = getChanelById(chanelId);
        List<AppUser> subscribers = chanel.getSubscribers();
        subscribers.add(user);
        chanel.setSubscribers(subscribers);
        chanelRepository.save(chanel);
    }

    public void unsubscribeUserFromChanel(Long userId, Long chanelId) {
        AppUser user = appUserService.getAppUserById(userId);
        Chanel chanel = getChanelById(chanelId);
        List<AppUser> subscribers = chanel.getSubscribers();
        subscribers.remove(user);
        chanel.setSubscribers(subscribers);
        chanelRepository.save(chanel);
    }

    public Page<ChanelSummaryDto> findAllChannelsWithFilters(
            String name, String language, String category, Pageable pageable) {
        List<Chanel> channels = chanelRepository.findAll(ChanelSpecifications.withFilters(name, language, category));

        List<ChanelSummaryDto> chanelSummaryDtoList = channels.stream()
                .map(chanelMapper::entityToSummaryDto)
                .toList();

        return new PageImpl<>(chanelSummaryDtoList, pageable, channels.size());
    }

    public ChanelDetailsDto getChanelDetailsById(Long chanelId) {
        Chanel chanel = getChanelById(chanelId);
        return chanelMapper.entityToDetailsDto(chanel);
    }

    private Chanel getChanelById(Long chanelId) {
        return chanelRepository.findById(chanelId)
                .orElseThrow(() -> new EntityNotFoundException("Chanel not found with id: " + chanelId));
    }
}
