package com.carrental.cache;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class SimpleCache {

    private static final SimpleCache INSTANCE = new SimpleCache();

    private final Map<String, CacheEntry> store = new ConcurrentHashMap<>();
    private final Duration ttl = Duration.ofMinutes(10); // можно без TTL, но так лучше

    private SimpleCache() {}

    public static SimpleCache getInstance() {
        return INSTANCE;
    }

    public <T> Optional<T> get(String key, Class<T> type) {
        CacheEntry entry = store.get(key);
        if (entry == null) return Optional.empty();

        if (entry.isExpired(ttl)) {
            store.remove(key);
            return Optional.empty();
        }
        return Optional.of(type.cast(entry.value()));
    }

    public void put(String key, Object value) {
        store.put(key, new CacheEntry(value, System.currentTimeMillis()));
    }

    public void evict(String key) {
        store.remove(key);
    }

    public void clear() {
        store.clear();
    }

    private record CacheEntry(Object value, long createdAtMillis) {
        boolean isExpired(Duration ttl) {
            return System.currentTimeMillis() - createdAtMillis > ttl.toMillis();
        }
    }
}
