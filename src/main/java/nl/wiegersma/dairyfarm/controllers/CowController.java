package nl.wiegersma.dairyfarm.controllers;
import nl.wiegersma.dairyfarm.dtos.CowRequestDto;
import nl.wiegersma.dairyfarm.dtos.CowResponseDto;
import nl.wiegersma.dairyfarm.models.Cow;
import nl.wiegersma.dairyfarm.services.CowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cow")
public class CowController {

    private final CowService cowService;

    public CowController(CowService cowService) {
        this.cowService = cowService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CowResponseDto> getCow(@PathVariable Long id){
        CowResponseDto cowResponseDto = cowService.getOneCow(id);
        return ResponseEntity.status(HttpStatus.OK).body(cowResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<CowResponseDto>> getAllCows(){
        List<CowResponseDto> cowResponseDto = cowService.getCows();
        return ResponseEntity.status(HttpStatus.OK).body(cowResponseDto);
    }

    @PostMapping
    public ResponseEntity<CowResponseDto> createCow(CowRequestDto cowRequestDto){
        CowResponseDto cowResponseDto = cowService.createCow(cowRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cowResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CowResponseDto> updateCow(Long id, CowRequestDto cowRequestDto){
        CowResponseDto cowResponseDto = cowService.updateCow(id, cowRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(cowResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCow(Long id){
        cowService.deleteCow(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
