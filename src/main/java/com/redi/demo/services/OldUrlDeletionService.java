package com.redi.demo.services;

import com.redi.demo.repository.ShortLinkRepository;
import com.redi.demo.repository.model.ShortLinkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class OldUrlDeletionService {
    @Autowired
    ShortLinkRepository shortLinkRepository;

    @Scheduled(fixedDelay = 10000)
    public void deleteShortUrls(){
        System.out.println("Scheduled task running");
        final Instant now = Instant.now();
        final Instant before = now.minus(Duration.ofDays(100));
        final Date dateBefore = Date.from(before);
        final List<ShortLinkEntity> links = shortLinkRepository
                .findByCreatedBefore(dateBefore);

        shortLinkRepository.deleteAll(links);
    }
}
