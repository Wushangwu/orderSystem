package com.example.order.messaging;

import com.example.order.domain.types.AuditMessage;

public interface AuditMessageProducer {
    void send(AuditMessage message);
}
