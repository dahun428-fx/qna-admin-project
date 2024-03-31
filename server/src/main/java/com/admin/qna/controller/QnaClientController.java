package com.admin.qna.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.qna.dto.CreateQnaDTO;
import com.admin.qna.dto.ReadQnaDTO;
import com.admin.qna.dto.UpdateQnaDTO;
import com.admin.qna.service.QnaService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class QnaClientController {

    private final QnaService qnaService;

    @GetMapping("/qna/{qnaId}")
    public ResponseEntity<ReadQnaDTO> find(@PathVariable("qnaId") long qnaId) {
        ReadQnaDTO found = qnaService.getOne(qnaId);
        log.info("/qna ( get {} ) : {}", qnaId, found);
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }

    @PostMapping("/qna")
    public ResponseEntity<ReadQnaDTO> add(@RequestBody CreateQnaDTO entity) {
        ReadQnaDTO saved = qnaService.save(entity);
        log.info("/qna ( post ) : {}", saved);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(saved);
    }

    @PutMapping("/qna/{qnaId}")
    public ResponseEntity<String> update(@PathVariable long qnaId, @RequestBody UpdateQnaDTO entity) {
        // qnaService.modify(entity);
        return ResponseEntity.status(HttpStatus.OK).body("Ok");
    }

}
