package by.nata.videohostingchannels.service.mapper;

import by.nata.videohostingchannels.database.model.AppUser;
import by.nata.videohostingchannels.service.dto.AppUserRequestDto;
import by.nata.videohostingchannels.service.dto.AppUserResponseDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface AppUserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subscribedChannels", ignore = true)
    AppUser dtoToEntity(AppUserRequestDto appUserRequestDto);

    AppUserResponseDto entityToDto(AppUser news);
}
