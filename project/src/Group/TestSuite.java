package Group;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LoadingTest.class, OrderTest.class, PickingRequestTest.class, QueueOfOrdersTest.class,
		QueueOfWorkersTest.class, SequencingTest.class, SKUReaderTest.class, TruckTest.class,
		WarehousePickingTest.class, WarehouseTest.class, WorkerTest.class })
public class TestSuite {

}
