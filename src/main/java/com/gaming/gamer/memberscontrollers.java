package com.gaming.gamer;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class memberscontrollers {

    private static final Logger logger = LoggerFactory.getLogger(memberscontrollers.class);

    @Autowired
    private membersservice service;

    // CREATE MEMBER
    @PostMapping
    public MemberSearchResponseDTO create(@RequestBody MemberDTO memberDTO) {
        logger.info("Controller: creating member {}", memberDTO.getName());
        return service.create(memberDTO);
    }

    // GET ALL MEMBERS
    @GetMapping
    public List<MemberSearchResponseDTO> findAll() {
        logger.info("Controller: fetching all members");
        return service.findAll();
    }

    // GET MEMBER BY ID
    @GetMapping("/{id}")
    public MemberSearchResponseDTO findById(@PathVariable String id) {
        logger.info("Controller: fetching member by Id {}", id);
        return service.findById(id);
    }

    // UPDATE MEMBER
    @PutMapping("/{id}")
    public MemberDTO update(@PathVariable String id, @RequestBody MemberDTO updatedDTO) {
        logger.info("Controller: updating member Id {}", id);
        return service.update(id, updatedDTO);
    }

    // DELETE MEMBER
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        logger.info("Controller: deleting member Id {}", id);
        service.delete(id);
    }

    // SEARCH MEMBER BY PHONE (returns profile + recharges + games + played history)
    @PostMapping("/search")
    public MemberSearchResponseDTO searchByPhone(@RequestBody Map<String, String> payload) {
        String phone = payload.get("phone");
        logger.info("Controller: searching member by phone {}", phone);
        return service.searchByPhone(phone);
    }
}
