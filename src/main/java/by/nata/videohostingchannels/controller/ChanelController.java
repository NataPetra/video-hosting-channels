package by.nata.videohostingchannels.controller;

import by.nata.videohostingchannels.controller.exception.BadRequestException;
import by.nata.videohostingchannels.service.ChanelService;
import by.nata.videohostingchannels.service.ImageService;
import by.nata.videohostingchannels.service.dto.ChanelDetailsDto;
import by.nata.videohostingchannels.service.dto.ChanelRequestDto;
import by.nata.videohostingchannels.service.dto.ChanelResponseDto;
import by.nata.videohostingchannels.service.dto.ChanelSummaryDto;
import by.nata.videohostingchannels.service.validation.AppUserValidation;
import by.nata.videohostingchannels.service.validation.ChanelValidation;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/channels")
@RequiredArgsConstructor
@Validated
public class ChanelController {

    private final ChanelService chanelService;
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ChanelResponseDto> saveChanel(
            @RequestBody @Valid ChanelRequestDto chanelDto) {
        ChanelResponseDto savedChanel = chanelService.saveChanel(chanelDto);
        return new ResponseEntity<>(savedChanel, HttpStatus.CREATED);
    }

    @PostMapping(value = "/image")
    public ResponseEntity<Void> uploadImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam("uuid") String uuid) {
        try {
            imageService.storageImage(image.getInputStream(), uuid);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PutMapping("/{chanelId}")
    public ResponseEntity<ChanelResponseDto> updateChanel(
            @RequestBody @Valid ChanelRequestDto chanelDto,
            @PathVariable @ChanelValidation Long chanelId) {
        ChanelResponseDto savedChanel = chanelService.updateChanel(chanelDto, chanelId);
        return new ResponseEntity<>(savedChanel, HttpStatus.OK);
    }

    @PostMapping("/{chanelId}/subscribe/{userId}")
    public ResponseEntity<Void> subscribeUserToChanel(
            @PathVariable @AppUserValidation Long userId,
            @PathVariable @ChanelValidation Long chanelId) {
        chanelService.subscribeUserToChanel(userId, chanelId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{chanelId}/unsubscribe/{userId}")
    public ResponseEntity<Void> unsubscribeUserFromChanel(
            @PathVariable @AppUserValidation Long userId,
            @PathVariable @ChanelValidation Long chanelId) {
        chanelService.unsubscribeUserFromChanel(userId, chanelId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChanelSummaryDto>> findAllChannelsWithFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String category,
            @PageableDefault(size = 2) @Nullable Pageable pageable) {
        List<ChanelSummaryDto> channels = chanelService.findAllChannelsWithFilters(name, language, category, pageable);
        return ResponseEntity.ok(channels);
    }

    @GetMapping("/{chanelId}")
    public ResponseEntity<ChanelDetailsDto> getChanelDetailsById(
            @PathVariable @ChanelValidation Long chanelId) {
        ChanelDetailsDto chanelDetails = chanelService.getChanelDetailsById(chanelId);
        return ResponseEntity.ok(chanelDetails);
    }

    @GetMapping(value = "/image/{uuid}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(
            @PathVariable(name = "uuid") String uuid) {
        try {
            byte[] image = imageService.getImage(uuid);
            return new ResponseEntity<>(image, HttpStatus.OK);
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
