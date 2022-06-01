package com.redi.demo.repository;

import com.redi.demo.repository.model.ShortLinkEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ShortLinkRepository extends CrudRepository<ShortLinkEntity, String> {
  ShortLinkEntity findByKey(final String key);
  List<ShortLinkEntity> findByCreatedBefore(final Date key);
}
