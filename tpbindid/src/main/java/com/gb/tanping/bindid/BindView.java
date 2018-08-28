package com.gb.tanping.bindid;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Retention(RUNTIME) @Target(FIELD)
public @interface BindView {
  /**
   *  View ID to which the field will be bound.
   */
    int value();
}
