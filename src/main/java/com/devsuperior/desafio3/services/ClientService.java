package com.devsuperior.desafio3.services;

import com.devsuperior.desafio3.dto.ClientDTO;
import com.devsuperior.desafio3.entities.Client;
import com.devsuperior.desafio3.repositories.ClientRepository;
import com.devsuperior.desafio3.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id)
    {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable page)
    {
        Page<Client> clients = clientRepository.findAll(page);
        return clients.map(ClientDTO::new);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto)
    {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = clientRepository.save(entity);

        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto)
    {
        try {
            Client entity = clientRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = clientRepository.save(entity);

            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id)
    {
        if(!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        clientRepository.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity)
    {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}
