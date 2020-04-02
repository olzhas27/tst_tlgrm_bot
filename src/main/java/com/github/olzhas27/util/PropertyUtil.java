package com.github.olzhas27.util;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

import static java.util.Objects.isNull;

@Slf4j
public class PropertyUtil {
    public static Properties getProperties(String fileName) {
        val properties = getPropertiesFromFile(fileName);
        if (isNull(properties)) {
            return getPropertiesFromResource(fileName);
        } else {
            return properties;
        }
    }

    private static Properties getPropertiesFromFile(String fileName) {
        val path = Path.of(fileName).toAbsolutePath();
        if (!Files.isRegularFile(path)) {
            return null;
        }

        try (val is = Files.newInputStream(path, StandardOpenOption.READ)) {
            return loadPropertiesFrom(is);
        } catch (IOException e) {
            log.warn("couldn't load properties from {}", path, e);
            return null;
        }
    }

    private static Properties getPropertiesFromResource(String fileName) {
        try (val is = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            return loadPropertiesFrom(is);
        } catch (IOException e) {
            throw new UncheckedIOException("properties has not been loaded", e);
        }
    }

    private static Properties loadPropertiesFrom(InputStream is) throws IOException {
        val properties = new Properties();
        properties.load(is);
        return properties;
    }
}
