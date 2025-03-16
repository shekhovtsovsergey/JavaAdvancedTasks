package ru.shekhovtsov.lesson4;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

// Реализация с WeakReference
class WeakFileCache extends AbstractFileCache {
    @Override
    protected Reference<String> createReference(String content) {
        return new WeakReference<>(content);
    }
}
