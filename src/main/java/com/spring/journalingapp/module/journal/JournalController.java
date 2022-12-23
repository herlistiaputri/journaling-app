package com.spring.journalingapp.module.journal;

import com.spring.journalingapp.core.BaseResponse;
import com.spring.journalingapp.module.journal.model.JournalRequest;
import com.spring.journalingapp.module.journal.model.JournalResponse;
import com.spring.journalingapp.module.journal.service.JournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }
    @PostMapping
    @RolesAllowed({"user"})
    public ResponseEntity<?> createNewPost(@RequestBody JournalRequest request) throws IOException {
        JournalResponse responseDto = journalService.createNewPost(request);

        BaseResponse<JournalResponse> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/all")
    @RolesAllowed({"user"})
    public ResponseEntity<?> getAllPost() {
        List<JournalResponse> responseDto = journalService.getAllPosts();

        BaseResponse<List<JournalResponse>> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @RolesAllowed({"user"})
    public ResponseEntity<?> getPostById(@PathVariable String id) {
        JournalResponse responseDto = journalService.getPost(id);

        BaseResponse<JournalResponse> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @RolesAllowed({"user", "superadmin"})
    public ResponseEntity<?> updatePost(@PathVariable String id, @RequestBody JournalRequest request) throws IOException {
        JournalResponse responseDto = journalService.updatePost(id, request);

        BaseResponse<JournalResponse> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
