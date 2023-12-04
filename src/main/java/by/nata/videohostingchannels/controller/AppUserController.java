package by.nata.videohostingchannels.controller;

import by.nata.videohostingchannels.service.AppUserService;
import by.nata.videohostingchannels.service.dto.AppUserRequestDto;
import by.nata.videohostingchannels.service.dto.AppUserResponseDto;
import by.nata.videohostingchannels.service.validation.AppUserValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<AppUserResponseDto> saveUser(@RequestBody @Valid AppUserRequestDto userDto) {
        AppUserResponseDto savedUser = appUserService.saveUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<AppUserResponseDto> updateUser(
            @RequestBody @Valid AppUserRequestDto userDto,
            @PathVariable @AppUserValidation Long userId) {
        AppUserResponseDto savedUser = appUserService.updateUser(userDto, userId);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}/subscriptions")
    public ResponseEntity<List<String>> getUserSubscriptions(
            @PathVariable @AppUserValidation Long userId) {
        List<String> subscriptions = appUserService.getUserSubscriptions(userId);
        return ResponseEntity.ok(subscriptions);
    }
}
