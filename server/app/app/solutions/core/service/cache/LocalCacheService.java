package app.solutions.core.service.cache;

import app.solutions.core.service.CacheService;
import app.solutions.exceptions.CollectionNotCached;
import app.solutions.exceptions.NotFoundInCache;
import com.google.inject.Singleton;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by niveditha on 12/4/14.
 */

@Singleton
public class LocalCacheService implements CacheService {

    private ConcurrentMap localCache = new ConcurrentHashMap();

    @Override
    public Object getByCacheKey(Object key) throws NotFoundInCache {

        if (!localCache.containsKey(key)) {
            throw new NotFoundInCache("Not ");
        }

        return localCache.get(key);
    }

    @Override
    public boolean isCached(String collectionName) {
        return false;
    }

    @Override
    public void cachePut(Object key, Object value) {
        localCache.putIfAbsent(key, value);
    }

    @Override
    public String generateCacheKey(String collectionName, String id, CacheType cacheType) {
        return collectionName+id;
    }
}
