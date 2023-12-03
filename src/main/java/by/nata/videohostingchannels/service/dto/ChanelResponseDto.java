package by.nata.videohostingchannels.service.dto;

import java.io.Serializable;

public record ChanelResponseDto(Long id, String name,
                                String description, String authorName,
                                String time, String languageName,
                                String image, String categoryName) implements Serializable {
}
