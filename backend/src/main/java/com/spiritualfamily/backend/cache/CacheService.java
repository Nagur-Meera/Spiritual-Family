package com.spiritualfamily.backend.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Cacheable("bibleVerses")
    public String getDailyVerse() {

        return "Philippians 4:13";
    }
}