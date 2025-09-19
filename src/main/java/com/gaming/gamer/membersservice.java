package com.gaming.gamer;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class membersservice {

    private static final Logger logger = LoggerFactory.getLogger(membersservice.class);

    private final membersrepository repo;

    @Autowired
    public membersservice(membersrepository repo) {
        this.repo = repo;
    }

    // CREATE
    public MemberDTO create(MemberDTO memberDTO) {
        logger.info("Creating member: {}", memberDTO.getName());

        members member = new members();
        member.setName(memberDTO.getName());
        member.setPhone(memberDTO.getPhoneNumber());
        member.setBalance(memberDTO.getBalance());

        validate(member);

        members saved = repo.save(member);
        return mapToDTO(saved);
    }

    // FIND ALL
    public List<MemberDTO> findAll() {
        logger.info("Fetching all members");
        return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // FIND BY ID
    public MemberDTO findById(String id) {
        members member = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found: " + id));
        return mapToDTO(member);
    }

    // UPDATE
    public MemberDTO update(String id, MemberDTO updatedDTO) {
        members old = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found: " + id));

        old.setName(updatedDTO.getName());
        old.setPhone(updatedDTO.getPhoneNumber());
        old.setBalance(updatedDTO.getBalance());

        members saved = repo.save(old);
        return mapToDTO(saved);
    }

    // DELETE
    public void delete(String id) {
        members member = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found: " + id));
        repo.delete(member);
    }

    // SEARCH BY PHONE
    public MemberDTO searchByPhone(String phone) {
    	
        members member = repo.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with phone: " + phone));
        return mapToDTO(member);
    }

    // VALIDATION
    private void validate(members member) {
        if (member.getBalance() != null && member.getBalance() < 0) {
            throw new BusinessException("Balance cannot be negative");
        }
    }

    // MAPPER: Entity → DTO
    private MemberDTO mapToDTO(members member) {
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setPhoneNumber(member.getPhone());
        dto.setBalance(member.getBalance());
        return dto;
    }
}
