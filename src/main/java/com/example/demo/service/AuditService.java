package com.example.demo.service;

import com.example.demo.repository.entity.Audit;

public interface AuditService {

    void log(Audit audit);
}
