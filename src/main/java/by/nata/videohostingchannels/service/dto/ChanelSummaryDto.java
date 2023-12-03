package by.nata.videohostingchannels.service.dto;

import java.io.Serializable;

public record ChanelSummaryDto(Long id, String name, String language,
                               String category, Integer subscribersCount) implements Serializable {
}
