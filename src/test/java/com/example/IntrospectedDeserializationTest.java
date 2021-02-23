package com.example;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.jackson.modules.BeanIntrospectionModule;
import org.junit.jupiter.api.Test;

public class IntrospectedDeserializationTest {
  @Introspected
  public static final class ListWrapper {
    final List<String> value;

    @JsonCreator
    public ListWrapper(List<String> value) {
      this.value = value;
    }
  }

  @Test
  public void deserializeViaReflection() throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    mapper.readValue("[\"good\"]", ListWrapper.class);
  }

  @Test
  public void deserializeViaBeanIntrospection() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new BeanIntrospectionModule());

    mapper.readValue("[\"bad\"]", ListWrapper.class);
  }
}
