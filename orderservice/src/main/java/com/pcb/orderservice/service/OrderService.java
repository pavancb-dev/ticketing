package com.pcb.orderservice.service;

import com.pcb.orderservice.client.InventoryServiceClient;
import com.pcb.orderservice.entity.Order;
import com.pcb.orderservice.event.BookingEvent;
import com.pcb.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient;

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent) {
        log.info("Received order event: {}", bookingEvent);
        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);
        inventoryServiceClient.updateInventory(order.getEventId(), order.getTicketCount());
        log.info("Inventory updated for event: {}, less tickets: {}", order.getEventId(), order.getTicketCount());
    }


    private Order createOrder(BookingEvent bookingEvent) {
        return Order.builder()
                .customerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }
}
