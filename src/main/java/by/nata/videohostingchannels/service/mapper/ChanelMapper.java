package by.nata.videohostingchannels.service.mapper;

import by.nata.videohostingchannels.database.model.AppUser;
import by.nata.videohostingchannels.database.model.Chanel;
import by.nata.videohostingchannels.service.AppUserService;
import by.nata.videohostingchannels.service.CategoryService;
import by.nata.videohostingchannels.service.LanguageService;
import by.nata.videohostingchannels.service.dto.ChanelDetailsDto;
import by.nata.videohostingchannels.service.dto.ChanelRequestDto;
import by.nata.videohostingchannels.service.dto.ChanelResponseDto;
import by.nata.videohostingchannels.service.dto.ChanelSummaryDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public abstract class ChanelMapper {

    @Autowired
    protected AppUserService appUserService;
    @Autowired
    protected LanguageService languageService;
    @Autowired
    protected CategoryService categoryService;

    @Mapping(target = "id", source = "chanel.id")
    @Mapping(target = "name", source = "chanel.name")
    @Mapping(target = "description", source = "chanel.description")
    @Mapping(target = "authorName", expression = "java(chanel.getAuthor().getName())")
    @Mapping(target = "time", source = "chanel.time", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "languageName", source = "chanel.language")
    @Mapping(target = "image", source = "chanel.image")
    @Mapping(target = "categoryName", source = "chanel.category")
    public abstract ChanelResponseDto entityToDto(Chanel chanel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "chanelRequestDto.authorId", qualifiedByName = "mapAuthor")
    @Mapping(target = "subscribers", ignore = true)
    @Mapping(target = "time", ignore = true)
    @Mapping(target = "language", source = "chanelRequestDto.languageId", qualifiedByName = "mapLanguage")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "category", source = "chanelRequestDto.categoryId", qualifiedByName = "mapCategory")
    public abstract Chanel dtoToEntity(ChanelRequestDto chanelRequestDto);

    @Mapping(target = "id", source = "chanel.id")
    @Mapping(target = "name", source = "chanel.name")
    @Mapping(target = "language", source = "chanel.language")
    @Mapping(target = "category", source = "chanel.category")
    @Mapping(target = "subscribersCount", expression = "java(chanel.getSubscribers() != null ? chanel.getSubscribers().size() : 0)")
    public abstract ChanelSummaryDto entityToSummaryDto(Chanel chanel);

    @Mapping(target = "id", source = "chanel.id")
    @Mapping(target = "name", source = "chanel.name")
    @Mapping(target = "description", source = "chanel.description")
    @Mapping(target = "authorName", expression = "java(chanel.getAuthor().getName())")
    @Mapping(target = "time", source = "chanel.time", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "language", source = "chanel.language")
    @Mapping(target = "category", source = "chanel.category")
    @Mapping(target = "subscribersCount", expression = "java(chanel.getSubscribers() != null ? chanel.getSubscribers().size() : 0)")
    public abstract ChanelDetailsDto entityToDetailsDto(Chanel chanel);

    @Named("mapAuthor")
    protected AppUser mapAuthor(Long authorId) {
        return appUserService.getAppUserById(authorId);
    }

    @Named("mapLanguage")
    protected String mapLanguage(Long languageId) {
        return languageService.getLanguageName(languageId);
    }

    @Named("mapCategory")
    protected String mapCategory(Long categoryId) {
        return categoryService.getCategoryName(categoryId);
    }
}
