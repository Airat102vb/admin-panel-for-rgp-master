package com.example.demo.service;

import com.example.demo.repository.AuditRepository;
import com.example.demo.repository.entity.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Autowired
    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public void log(Audit audit) {
        auditRepository.save(audit);
    }
}
