package com.kisti.osp.spyglass.service.impl.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kisti.osp.spyglass.service.impl.ScienceAppLocalServiceImpl;

public class ScienceAppLocalServiceImplTest {
	@BeforeClass
	public static void initialize(){
		System.out.println("Initialize general test environment.");
		Assert.assertTrue(true);
	}
	
	@Before
	public void setup(){
		System.out.println("Set up scienceAppLocalServiceImpl instance");
		Assert.assertTrue(true);
	}
	
	@Test
	public void addScienceApp(){
		System.out.println("Test addScienceApp....");
		Assert.assertTrue(true);
	}
	
//	private ScienceAppLocalServiceImpl scienceAppLocalServiceImpl = new ScienceAppLocalServiceImpl();
}
