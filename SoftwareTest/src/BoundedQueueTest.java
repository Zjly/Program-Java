import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * 测试含有三个特征
 * 特征一: 元素个数    测试值分为四个区块: 2, 1, 0, -1              分别用A1-A4表示   基本区块为A1
 * 特征二: 入队元素    测试值分为两个区块: object, null             分别用B1-B2表示   基本区块为B1
 * 特征三: 队列状态    测试值分为两个区块: 未满, 已满                分别用C1-C2表示   基本区块为C1
 */
public class BoundedQueueTest {
	private BoundedQueue boundedQueue;
	private Object o = new Object();

	// 测试A1B1C1——[2, object, 未满]
	@Test
	public void testA1B1C1() {
		boundedQueue = new BoundedQueue(2);
		boundedQueue.enQueue(o);
		assertSame(boundedQueue.deQueue(), o);
	}

	// 测试A1B1C2——[2, object, 已满]
	@Test(expected = IllegalStateException.class)
	public void testA1B1C2() {
		boundedQueue = new BoundedQueue(2);
		boundedQueue.enQueue(new Object());
		boundedQueue.enQueue(new Object());
		boundedQueue.enQueue(o);
	}

	// 测试A1B2C1——[2, null, 未满]
	@Test(expected = NullPointerException.class)
	public void testA1B2C1() {
		boundedQueue = new BoundedQueue(2);
		boundedQueue.enQueue(null);
	}

	// 测试A2B1C1——[1, object, 未满]
	@Test
	public void testA2B1C1() {
		boundedQueue = new BoundedQueue(1);
		boundedQueue.enQueue(o);
		assertSame(boundedQueue.deQueue(), o);
	}

	// 测试A3B1C1——[0, object, 未满]
	@Test(expected = IllegalStateException.class)
	public void testA3B1C1() {
		boundedQueue = new BoundedQueue(0);
		boundedQueue.enQueue(o);
	}

	// 测试A4B1C1——[-1, object, 未满]
	@Test(expected = IllegalArgumentException.class)
	public void testA4B1C1() {
		boundedQueue = new BoundedQueue(-1);
		boundedQueue.enQueue(o);
	}
}