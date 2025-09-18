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

    // CREATE MEMBER
    public MemberSearchResponseDTO create(MemberDTO memberDTO) {
        logger.info("Request received to create member: {}", memberDTO.getName());

        members member = new members();
        member.setName(memberDTO.getName());
        member.setPhone(memberDTO.getPhone());
        member.setBalance(memberDTO.getBalance());

        validate(member);

        members saved = repo.save(member);
        logger.info("✅ New member created: {} (Id: {})", saved.getName(), saved.getId());

        return mapToDTO(saved);
    }

    // FIND ALL MEMBERS
    public List<MemberSearchResponseDTO> findAll() {
        logger.info("Fetching all members");
        return repo.findAll().stream()
                   .map(this::mapToDTO)
                   .collect(Collectors.toList());
    }

    // FIND MEMBER BY ID
    public MemberSearchResponseDTO findById(String id) {
        logger.info("Searching member by Id: {}", id);
        members member = repo.findById(id)
                             .orElseThrow(() -> {
                                 logger.error("❌ Member not found: {}", id);
                                 return new ResourceNotFoundException("Member not found: " + id);
                             });
        return mapToDTO(member);
    }

    // UPDATE MEMBER
    public MemberSearchResponseDTO update(String id, MemberDTO updatedDTO) {
        logger.info("Updating member with Id: {}", id);
        members old = repo.findById(id)
                          .orElseThrow(() -> {
                              logger.error("❌ Member not found: {}", id);
                              return new ResourceNotFoundException("Member not found: " + id);
                          });

        old.setName(updatedDTO.getName());
        old.setPhone(updatedDTO.getPhone());
        old.setBalance(updatedDTO.getBalance());

        members saved = repo.save(old);
        logger.info("✅ Member updated: {} (Id: {})", saved.getName(), saved.getId());
        return mapToDTO(saved);
    }

    // DELETE MEMBER
    public void delete(String id) {
        logger.info("Deleting member with Id: {}", id);
        members member = repo.findById(id)
                             .orElseThrow(() -> {
                                 logger.error("❌ Member not found: {}", id);
                                 return new ResourceNotFoundException("Member not found: " + id);
                             });

        repo.delete(member);
        logger.info("✅ Member deleted: {} (Id: {})", member.getName(), member.getId());
    }

    // SEARCH MEMBER BY PHONE
    public MemberSearchResponseDTO searchByPhone(String phone) {
        logger.info("Searching member by phone: {}", phone);
        members member = repo.findByPhone(phone)
                             .orElseThrow(() -> {
                                 logger.error("❌ Member not found with phone: {}", phone);
                                 return new ResourceNotFoundException("Member not found with phone: " + phone);
                             });
        logger.info("✅ Member found: {} (Phone: {})", member.getName(), member.getPhone());
        return mapToDTO(member);
    }

    // VALIDATION
    private void validate(members member) {
        if (member.getBalance() != null && member.getBalance() < 0) {
            logger.error("❌ Balance cannot be negative for member: {}", member.getName());
            throw new BusinessException("Balance cannot be negative");
        }
    }

    // HELPER: MAP ENTITY → DTO
    private MemberSearchResponseDTO mapToDTO(members member) {
        MemberSearchResponseDTO dto = new MemberSearchResponseDTO();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setPhone(member.getPhone());
        dto.setBalance(member.getBalance());
        return dto;
    }
}
