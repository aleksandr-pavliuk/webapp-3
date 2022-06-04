package org.andersen.webapp.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
abstract public class LongEntity implements Entity<Long> {

  protected Long id;

}
