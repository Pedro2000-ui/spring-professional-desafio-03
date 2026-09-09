package com.devsuperior.desafio3.controllers;

import com.devsuperior.desafio3.dto.ClientDTO;
import com.devsuperior.desafio3.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ClientDTO findById(@PathVariable Long id)
    {
        ClientDTO dto = clientService.findById(id);
        return dto;
    }

    @GetMapping
    public Page<ClientDTO> findAll(Pageable page)
    {
        Page<ClientDTO> dto = clientService.findAll(page);
        return dto;
    }

    @PostMapping
    public ClientDTO insert(@RequestBody ClientDTO dto)
    {
        dto = clientService.insert(dto);
        return dto;
    }

    @PutMapping(value = "/{id}")
    public ClientDTO update(@PathVariable Long id, @RequestBody ClientDTO dto)
    {
        dto = clientService.update(id, dto);
        return dto;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id)
    {
        clientService.delete(id);
    }
}
