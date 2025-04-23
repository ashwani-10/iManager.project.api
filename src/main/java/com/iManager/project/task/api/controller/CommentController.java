package com.iManager.project.task.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iManager.project.task.api.requestDTO.CommentRequestDTO;
import com.iManager.project.task.api.requestDTO.RoleRequestDTO;
import com.iManager.project.task.api.responseDTO.CommentResponseDTO;
import com.iManager.project.task.api.responseDTO.RoleResponseDTO;
import com.iManager.project.task.api.util.DbApi;
import com.iManager.project.task.api.util.Mapper;
import com.iManager.project.task.api.util.TokenApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    TokenApi tokenApi;
    DbApi dbApi;
    Mapper mapper;
    ObjectMapper objectMapper;

    public CommentController(TokenApi tokenApi, DbApi dbApi,
                            Mapper mapper, ObjectMapper objectMapper) {
        this.tokenApi = tokenApi;
        this.dbApi = dbApi;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/create/{task_id}")
    public ResponseEntity createComment(@RequestHeader("Authorization") String authHeader,
                                     @PathVariable UUID task_id,
                                     @RequestBody CommentRequestDTO requestDTO) {
        try {
            ResponseEntity response = tokenApi.tokenVerify(authHeader);
            if (response.getStatusCode() == HttpStatus.OK) {
                try {
                    dbApi.createComment(task_id,requestDTO);
                    return new ResponseEntity("Comment created", HttpStatus.CREATED);
                } catch (Exception e) {
                    return new ResponseEntity("Failed creating comment", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity("Unauthorized access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Verify Token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{taskId}")
    public ResponseEntity getComments(@RequestHeader("Authorization") String authHeader,
                                   @PathVariable UUID taskId) {
        try {
            ResponseEntity response = tokenApi.tokenVerify(authHeader);
            if (response.getStatusCode() == HttpStatus.OK) {
                try {
                    Object object = dbApi.getComments(taskId);
                    List<CommentResponseDTO> responseDTO = objectMapper.convertValue(object, new TypeReference<List<CommentResponseDTO>>() {});
                    return new ResponseEntity(responseDTO, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity("Failed fetching comments", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity("Unauthorized access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Verify Token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
