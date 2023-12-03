package by.nata.videohostingchannels.service.dto;

import java.io.Serializable;

public record ChanelDetailsDto(Long id, String name, String language,
                               String category, Integer subscribersCount,
                               String description, String time,
                               String authorName) implements Serializable {
}
