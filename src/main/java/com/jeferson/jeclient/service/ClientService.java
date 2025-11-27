package com.jeferson.jeclient.service;

import com.jeferson.jeclient.dto.ClientDTO;
import com.jeferson.jeclient.entities.Client;
import com.jeferson.jeclient.repositories.ClientRepository;
import com.jeferson.jeclient.service.exceptions.DatabaseException;
import com.jeferson.jeclient.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
//        Optional<Client> result = repository.findById(id);
//        Client client = result.get();
//        ClientDTO dto = new ClientDTO(client);
//        return dto;
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)// faz a paginação da lista de clientes e permite organizar clientes por pagina e ordenação por nome
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return  result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());

        entity = repository.save(entity);

        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){

        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        }catch (EntityNotFoundException e){// nome do erro quando id inexistente para atualizar
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
        try{// mesmo que tenha somente uma entidade, é importante esse tratamento para informar que o id não existe
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial.");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {// esse método é chamado dentro do update para usar esses campos
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
