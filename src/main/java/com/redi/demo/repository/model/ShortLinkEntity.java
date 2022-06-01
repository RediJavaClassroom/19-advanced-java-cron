package com.redi.demo.repository.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "short_links")
public class ShortLinkEntity {

  @Id
  private String key;
  private String originalUrl;

  @ManyToOne()
  @JoinColumn(name = "email")
  private User user;

  private Date created;
  private Date updated;
  protected ShortLinkEntity() {}

  public ShortLinkEntity(final String key, final String originalUrl) {
    this.key = key;
    this.originalUrl = originalUrl;
  }

  @PrePersist
  protected void onCreate() {
    created = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    updated = new Date();
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public Date getCreated() {
    return created;
  }

  public Date getUpdated() {
    return updated;
  }
}
