package com.spring.journalingapp.module.pictures;

import com.spring.journalingapp.core.BaseResponse;
import com.spring.journalingapp.module.pictures.model.PictureResponse;
import com.spring.journalingapp.module.pictures.service.PictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pictures")
public class PicturesController {

    private final PictureService pictureService;

    public PicturesController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{journalId}")
    public ResponseEntity<?> getPictures(@PathVariable String journalId) {
        List<PictureResponse> responses = pictureService.getPictures(journalId);
        BaseResponse<List<PictureResponse>> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, responses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{pictureId}")
    public ResponseEntity<?> getPicture(@PathVariable String pictureId) {
        PictureResponse responseDto = pictureService.getPicture(pictureId);
        BaseResponse<PictureResponse> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
