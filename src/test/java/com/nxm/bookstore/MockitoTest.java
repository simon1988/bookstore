package com.nxm.bookstore;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MockitoTest {

	@Test
	public void testMock(){
		List mockedList = mock(List.class);
		
		mockedList.add("one");
		mockedList.add("two");
		mockedList.add("two");
		
		when(mockedList.get(100)).thenReturn("100");
		Assert.assertEquals("100", mockedList.get(100));
		
		verify(mockedList).add("one");
		verify(mockedList, times(2)).add("two");
	}
}
