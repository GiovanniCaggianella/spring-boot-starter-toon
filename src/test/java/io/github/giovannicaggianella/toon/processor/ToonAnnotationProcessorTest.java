package io.github.giovannicaggianella.toon.processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.Test;

public class ToonAnnotationProcessorTest {

    private final ToonAnnotationProcessor processor = new ToonAnnotationProcessor();

    @Test
    public void encodeWithLengthMarkerAddsCountPrefix() {
        String toon = processor.encode(List.of("dev", "ops"), true);

        assertThat(toon).contains("[#2]");
    }

    @Test
    public void decodeConvertsPayloadToTargetClass() {
        String toon = "name: Ada\nage: 42";

        User result = processor.decode(toon, User.class);

        assertThat(result.getName()).isEqualTo("Ada");
        assertThat(result.getAge()).isEqualTo(42);
    }

    @Test
    public void decodeRejectsBlankPayloads() {
        assertThatThrownBy(() -> processor.decode("  \n", User.class))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static class User {
        private String name;
        private int age;

        public User() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
