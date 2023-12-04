package by.nata.videohostingchannels.service.dto;

import java.io.Serializable;

public record AppUserResponseDto(Long id, String login,
                                 String name, String email) implements Serializable {
}
