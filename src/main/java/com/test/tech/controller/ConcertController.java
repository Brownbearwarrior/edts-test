package com.test.tech.controller;

import com.test.tech.entity.constant.DummyObject;
import com.test.tech.entity.dao.UserAccount;
import com.test.tech.entity.dto.request.ConcertPage;
import com.test.tech.entity.dto.request.ConcertRequest;
import com.test.tech.entity.dto.response.DataResponse;
import com.test.tech.service.internal.ConcertInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/concert")
@RequiredArgsConstructor
public class ConcertController {
    private final ConcertInternalService internalService;

    @PostMapping("/create")
    public ResponseEntity<DataResponse<Object>> createConcert(@RequestBody ConcertRequest request){
        //dummy header for username
        UserAccount user = DummyObject.dummyUser();

        return internalService.createConcert(user, request);
    }

    @GetMapping("/find/{concertCode}")
    public ResponseEntity<DataResponse<Object>> findDetailConcert(@PathVariable String concertCode){
        return internalService.findDetailConcert(concertCode);
    }

    @GetMapping
    public ResponseEntity<DataResponse<Object>> findPaginatedConcert(ConcertPage page){
        return internalService.findConcert(page);
    }

    @PutMapping("/update/{concertCode}")
    public ResponseEntity<DataResponse<Object>> updateConcert(@PathVariable String concertCode, @RequestBody ConcertRequest request){
        //dummy header for username
        UserAccount user = DummyObject.dummyUser();

        return internalService.update(concertCode, user, request);
    }

    @DeleteMapping("/delete/{concertCode}")
    public ResponseEntity<DataResponse<Object>> deleteConcert(@PathVariable String concertCode){
        //dummy header for username
        UserAccount user = DummyObject.dummyUser();

        return internalService.delete(concertCode, user);
    }
}
