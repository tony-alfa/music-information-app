package com.expd.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class PricingService {

    public int getPrice(int id){
       return ThreadLocalRandom.current().nextInt(1,10);
    }
}
