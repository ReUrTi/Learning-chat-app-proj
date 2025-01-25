package com.example.chat_app.service;

import com.example.chat_app.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockedUserService {

    @Autowired
    BlockRepository blockRepository;

    public boolean checkBlock(Long blockerId, Long blockedId){
        return blockRepository.existsByParams(blockerId, blockedId);
    }
}
