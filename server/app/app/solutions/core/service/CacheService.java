package app.solutions.core.service;

import app.solutions.core.service.cache.CacheType;
import app.solutions.exceptions.CollectionNotCached;
import app.solutions.exceptions.NotFoundInCache;

/**
 * Created by niveditha on 12/4/14.
 */
public interface CacheService<K, V> {

    /**
     *  Get Data by Cache Key.
     * @param key
     * @return
     */
    public V getByCacheKey(K key) throws NotFoundInCache;


    /**
     *  Returns true if collection is cached.
     * @param collectionName
     * @return
     */
    public boolean isCached(String collectionName);


    /**
     *  update cache.
      * @param key
     * @param value
     */
    public void cachePut( K key, V value);


    /**
     *  Generate Cache Key
     * @param collectionName
     * @param id
     * @param cacheType
     * @return
     */
    public String generateCacheKey(String collectionName, String id, CacheType cacheType);



}
