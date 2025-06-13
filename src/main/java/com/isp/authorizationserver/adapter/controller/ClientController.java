package com.isp.authorizationserver.adapter.controller;

import com.isp.authorizationserver.adapter.dto.in.ClientRq;
import com.isp.authorizationserver.adapter.dto.out.ClientRp;
import com.isp.authorizationserver.domain.port.in.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientsService;

    public ClientController(ClientService clientsService) {
        this.clientsService = clientsService;
    }

    @PostMapping
    public ResponseEntity<ClientRp> register(@Valid @RequestBody ClientRq clientRq) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clientsService.createClient(clientRq));
    }
}
