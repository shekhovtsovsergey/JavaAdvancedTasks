package ru.shekhovtsov.lesson4;

import java.lang.ref.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public abstract class AbstractFileCache {

    protected final Map<String, Reference<String>> cache = new ConcurrentHashMap<>();
    protected Path directory;
    protected abstract Reference<String> createReference(String content);

    public void setDirectory(String path) {
        this.directory = Paths.get(path);
    }

    public String getFileContent(String fileName) throws IOException {
        Reference<String> ref = cache.get(fileName);
        String content = (ref != null) ? ref.get() : null;

        if (content == null) {
            content = loadFileContent(fileName);
            cache.put(fileName, createReference(content));
        }
        return content;
    }

    protected String loadFileContent(String fileName) throws IOException {
        Path filePath = directory.resolve(fileName);
        return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
    }
}
