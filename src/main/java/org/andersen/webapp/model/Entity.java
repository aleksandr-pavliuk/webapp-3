package org.andersen.webapp.model;

public interface Entity<K> {

  K getId();

  void setId(K id);
}
