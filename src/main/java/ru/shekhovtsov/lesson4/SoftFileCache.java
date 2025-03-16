package ru.shekhovtsov.lesson4;


import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

// Реализация с SoftReference
class SoftFileCache extends AbstractFileCache {
    @Override
    protected Reference<String> createReference(String content) {
        return new SoftReference<>(content);
    }
}