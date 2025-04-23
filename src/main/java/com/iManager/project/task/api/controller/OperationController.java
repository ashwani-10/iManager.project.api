package com.iManager.project.task.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iManager.project.task.api.responseDTO.OperationResponseDTO;
import com.iManager.project.task.api.responseDTO.UserResponseDTO;
import com.iManager.project.task.api.util.DbApi;
import com.iManager.project.task.api.util.Mapper;
import com.iManager.project.task.api.util.TokenApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/operation")
public class OperationController {
    TokenApi tokenApi;
    DbApi dbApi;
    Mapper mapper;
    ObjectMapper objectMapper;

    public OperationController(TokenApi tokenApi, DbApi dbApi,
                            Mapper mapper, ObjectMapper objectMapper) {
        this.tokenApi = tokenApi;
        this.dbApi = dbApi;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/get")
    public ResponseEntity getOperations(@RequestHeader("Authorization") String authHeader){
        try {
            if (tokenApi.tokenVerify(authHeader).getStatusCode() == HttpStatus.OK) {
                try {
                    Object object = dbApi.getOperations();
                    List<OperationResponseDTO> responseDTOList = objectMapper.convertValue(object, new TypeReference<List<OperationResponseDTO>>() {});
                    return new ResponseEntity(responseDTOList, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity("Failed fetching operations", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity("Unauthorized access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Verify Token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
