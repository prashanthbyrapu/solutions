import app.solutions.core.service.references.ReferenceDocumentService;
import app.solutions.db.mongo.core.MongoStore;
import app.solutions.exceptions.DBConnectionFailureException;
import app.solutions.model.TestModelA;
import app.solutions.model.TestModelB;
import app.solutions.util.Utility;
import org.junit.BeforeClass;
import org.junit.Test;
import org.bson.types.ObjectId;

import java.util.Map;

/**
 * Created by niveditha on 19/4/14.
 */
public class TestReferenceDocumentServiceImpl extends  TestBase {




   @Test
   public void testUpdateReferenceFields() throws DBConnectionFailureException {

       String foreignKey = new ObjectId().toString();
       TestModelA modelA = new TestModelA();
       modelA.setIndexingNotAllowed("indexField");
       modelA.setReferenceField(foreignKey);

       MongoStore.getInstance().createDataStore("101").save(modelA);

       TestModelB modelB = new TestModelB();
       modelB.setId(foreignKey);
       modelB.setTextField("ModelBText");
       modelB.setField2("modelBField2");

       MongoStore.getInstance().createDataStore("101").save(modelB);

       ReferenceDocumentService refDocService = injector.getInstance(ReferenceDocumentService.class);

       Map<String,Object> result = refDocService.updateWithDocumentTexts(modelA);

       System.out.println(Utility.toJson(result));

   }

}
