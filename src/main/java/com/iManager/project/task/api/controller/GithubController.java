package com.iManager.project.task.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iManager.project.task.api.responseDTO.PullResponseDTO;
import com.iManager.project.task.api.responseDTO.UserResponseDTO;
import com.iManager.project.task.api.util.DbApi;
import com.iManager.project.task.api.util.Mapper;
import com.iManager.project.task.api.util.TokenApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/github")
public class GithubController {
    TokenApi tokenApi;
    DbApi dbApi;
    Mapper mapper;
    ObjectMapper objectMapper;

    public GithubController(TokenApi tokenApi, DbApi dbApi,
                             Mapper mapper, ObjectMapper objectMapper) {
        this.tokenApi = tokenApi;
        this.dbApi = dbApi;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/repos")
    public ResponseEntity getRepos(@RequestHeader("Authorization") String authHeader){
        try {
            ResponseEntity response = tokenApi.tokenVerify(authHeader);
            if (response.getStatusCode() == HttpStatus.OK) {
                try {
                    Object object = dbApi.getGithubRepos((String) response.getBody());
                    List<String> responseDTOList = objectMapper.convertValue(object, new TypeReference<List<String>>() {});
                    return new ResponseEntity(responseDTOList, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity("Failed fetching github repos", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity("Unauthorized access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Verify Token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/pr/{ticketId}")
    public ResponseEntity getGithubPr(@RequestHeader("Authorization") String authHeader,
                                      @PathVariable String ticketId){
        try {
            ResponseEntity response = tokenApi.tokenVerify(authHeader);
            if (response.getStatusCode() == HttpStatus.OK) {
                try {
                    Object object = dbApi.getGithubPr(ticketId);
                    List<PullResponseDTO> responseDTOList = objectMapper.convertValue(object, new TypeReference<List<PullResponseDTO>>() {});
                    Collections.sort(responseDTOList,(p1,p2) -> Math.toIntExact((p1.getId() - p2.getId())));
                    return new ResponseEntity(responseDTOList, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity("Failed fetching github repos", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity("Unauthorized access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Verify Token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
