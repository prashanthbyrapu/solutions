import app.solutions.contexts.UserContext;
import app.solutions.contexts.provider.PlayUserContextProvider;
import app.solutions.core.module.CoreModule;
import app.solutions.core.module.interceptors.DebugLoggingInterceptor;
import app.solutions.core.service.CacheService;
import app.solutions.core.service.DocumentReadService;
import app.solutions.core.service.DocumentTextService;
import app.solutions.core.service.cache.LocalCacheService;
import app.solutions.core.service.impl.DocumentReadServiceImpl;
import app.solutions.core.service.impl.DocumentTextServiceImpl;
import app.solutions.core.service.metadata.CollectionMetadataService;
import app.solutions.core.service.metadata.ReflectionBasedCollectionMetadataService;
import app.solutions.core.service.references.ReferenceDocumentService;
import app.solutions.core.service.references.ReferenceDocumentServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import org.junit.BeforeClass;

/**
 * Created by niveditha on 18/4/14.
 */
public class TestBase {

    protected  static Injector injector;
    @BeforeClass
    public static void setUp(){
         injector = Guice.createInjector(new TestModule());
    }


    public static class TestModule extends AbstractModule {


        @Override
        protected void configure() {

         //   logger.info("INitialyzing the core module");

            bind(DocumentReadService.class).to(DocumentReadServiceImpl.class);

            // Bind User Context dependency
            bind(UserContext.class).toProvider(new TestUserContextProvider());

            // Meta Data service dependency
            bind(CollectionMetadataService.class).to(ReflectionBasedCollectionMetadataService.class);

            // Cache Service Dependency
            bind(CacheService.class).to(LocalCacheService.class);

            // Bind ForiengKey related service api.
            bind(ReferenceDocumentService.class).to(ReferenceDocumentServiceImpl.class);

            //Document Text Service
            bind(DocumentTextService.class).to(DocumentTextServiceImpl.class);

           // if( logger.isDebugEnabled())
             //   bindInterceptor(Matchers.any(), Matchers.any(), new DebugLoggingInterceptor());

           // logger.info("Done! Initialyzed Core Module");

        }
    }

}
