package com.learn.jay.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerServiceTest {

    @Test
    void extractMessageID() {

        String id = "1001";
        String xmlMessage = String.format("""
                    <Order>
                        <OrderDetails>
                            <Id>%s</Id>
                            <Customer>Dynamic User</Customer>
                            <Amount>123.45</Amount>
                        </OrderDetails>
                    </Order>
                    """, id);

        String outputId = new ConsumerService(null).extractMessageID(xmlMessage, "OrderDetails/Id");

        assertEquals(id, outputId);
    }
}