package br.com.terceiroperiodo.service;

import br.com.terceiroperiodo.client.PhotoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PhotoConsumerService {

    @Autowired(required = false)
    PhotoClient photoClient;

    public ResponseEntity<?> buscarPhotoPorId(Long id){
        try {
            log.info("buscarPhotoPorId() - photoId - <{}>", id.toString());
            Object response = photoClient.getPhotoById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e){
            log.error("buscarPhotoPorId() - [ERRO] - <{}>", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
