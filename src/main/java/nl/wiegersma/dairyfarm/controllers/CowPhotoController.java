package nl.wiegersma.dairyfarm.controllers;
import nl.wiegersma.dairyfarm.dtos.CowPhotoResponseDto;
import nl.wiegersma.dairyfarm.services.CowPhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;


@RestController
@RequestMapping("/cowphoto")
public class CowPhotoController {

    private final CowPhotoService cowPhotoService;

    public CowPhotoController(CowPhotoService cowPhotoService) {
        this.cowPhotoService = cowPhotoService;
    }

    @PutMapping("/uploadphoto/{cowId}/cow")
    public ResponseEntity<CowPhotoResponseDto> addCowPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long cowId) throws IOException {
         String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/cowphoto/download/")
                    .path(Objects.requireNonNull(file.getOriginalFilename()))
                    .toUriString();
        CowPhotoResponseDto cowPhoto = cowPhotoService.storePhoto(file, url, cowId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cowPhoto);
    }
}
