package com.medicine.main.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import com.medicine.main.controller.MedicineController;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	MedicineController.class
})

@SpringBootTest
public class DemoBootApplicationTests {
	@Test
	public void contextLoads() {
	}
}