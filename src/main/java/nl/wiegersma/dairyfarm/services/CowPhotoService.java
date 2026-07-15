package nl.wiegersma.dairyfarm.services;
import jakarta.transaction.Transactional;
import nl.wiegersma.dairyfarm.dtos.CowPhotoResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.CowPhotoMapper;
import nl.wiegersma.dairyfarm.models.Cow;
import nl.wiegersma.dairyfarm.models.CowPhoto;
import nl.wiegersma.dairyfarm.repositories.CowPhotoRepository;
import nl.wiegersma.dairyfarm.repositories.CowRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CowPhotoService {

    private final CowPhotoRepository cowPhotoRepository;
    private final CowPhotoMapper cowPhotoMapper;
    private final CowRepository cowRepository;

    public CowPhotoService(CowPhotoRepository cowPhotoRepository, CowPhotoMapper cowPhotoMapper, CowRepository cowRepository) {
        this.cowPhotoRepository = cowPhotoRepository;
        this.cowPhotoMapper = cowPhotoMapper;
        this.cowRepository = cowRepository;
    }

    @Transactional
    public CowPhotoResponseDto storePhoto(MultipartFile file, String url, Long cowId) throws IOException {
        Cow cow = cowRepository.findById(cowId).orElseThrow(() -> new RecordNotFoundException("cant find cow with id " + cowId));
        CowPhoto cowPhoto = cow.getCowPhoto();
        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] bytes = file.getBytes();

        if (cowPhoto == null) {
            cowPhoto = new CowPhoto(originalFileName, url, contentType, bytes, cow);
            cowPhotoRepository.save(cowPhoto);
            return cowPhotoMapper.toDto(cowPhoto);
        } else {
            cowPhoto.setTitle(originalFileName);
            cowPhoto.setContents(bytes);
            cowPhoto.setUrl(url);
            cowPhoto.setContentType(contentType);
            cowPhoto.setCow(cowPhoto.getCow());
            CowPhoto saved = cowPhotoRepository.save(cowPhoto);
            return cowPhotoMapper.toDto(saved);
        }
    }
}
