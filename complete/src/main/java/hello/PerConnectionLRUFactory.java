package hello;

import java.util.Set;

import com.mysql.cj.util.LRUCache;
import com.mysql.cj.ParseInfo;
import com.mysql.cj.CacheAdapter;
import com.mysql.cj.CacheAdapterFactory;

public class PerConnectionLRUFactory implements CacheAdapterFactory<String, ParseInfo> {

    public CacheAdapter<String, ParseInfo> getInstance(Object syncMutex, String url, int cacheMaxSize, int maxKeySize) {

        return new PerConnectionLRU(syncMutex, cacheMaxSize, maxKeySize);
    }

    class PerConnectionLRU implements CacheAdapter<String, ParseInfo> {
        private final int cacheSqlLimit;
        private final LRUCache<String, ParseInfo> cache;
        private final Object syncMutex;

        protected PerConnectionLRU(Object syncMutex, int cacheMaxSize, int maxKeySize) {
            final int cacheSize = cacheMaxSize;
            this.cacheSqlLimit = maxKeySize;
            this.cache = new LRUCache<>(cacheSize);
            this.syncMutex = syncMutex;
        }

        public ParseInfo get(String key) {
            if (key == null || key.length() > this.cacheSqlLimit) {
                return null;
            }

            synchronized (this.syncMutex) {
                return this.cache.get(key);
            }
        }

        public void put(String key, ParseInfo value) {
            if (key == null || key.length() > this.cacheSqlLimit) {
                return;
            }

            synchronized (this.syncMutex) {
                this.cache.put(key, value);
            }
        }

        public void invalidate(String key) {
            synchronized (this.syncMutex) {
                this.cache.remove(key);
            }
        }

        public void invalidateAll(Set<String> keys) {
            synchronized (this.syncMutex) {
                for (String key : keys) {
                    this.cache.remove(key);
                }
            }

        }

        public void invalidateAll() {
            synchronized (this.syncMutex) {
                this.cache.clear();
            }
        }
    }
}
