/*package com.medicine.main.test.controller;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.medicine.main.Main;
import com.medicine.main.controller.Controller;
import com.medicine.main.entity.Medicine;
import com.medicine.main.service.MedicineDaoService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Main.class)
@SpringBootTest
public class ControllerTest {
	@Autowired
	private WebApplicationContext wac;
	@MockBean
	private MedicineDaoService med;
	@InjectMocks
	private Controller cont;

	private MockMvc mockMvc;

	Medicine mMock=new Medicine();
	Medicine mMock1=new Medicine();
	String mockList,mockUpdate;

	List<Medicine> mockMedicineList = new ArrayList<Medicine>();
	List<Medicine> mockMedicineList1 = new ArrayList<Medicine>();
	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	Date e,m,e1;
	{
		try
		{
			e=df.parse("12-09-2019");
			m=df.parse("12-09-2018");
		

		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		mMock.setmId(1);
		mMock.setMedname("medname");
		mMock.setMrp(30);
		mMock.setQuantity(2);
		mMock.setExpiryDate(e);
		mMock.setManuDate(m);

		mMock1.setmId(30);
		mMock1.setMedname("");
		mMock1.setMrp(0);
		mMock1.setQuantity(0);
		mMock1.setExpiryDate(e);
		mMock1.setManuDate(m);

		mockMedicineList.add(mMock);


		mockList ="Medicine Record Inserted Successfully!";
		mockUpdate ="Medicine Record Updated Successfully!";
	}
	@Test
	public void retrieveDetailsForMedicine() throws Exception
	{

		Mockito.when(med.getAllMedicines()).thenReturn(mockMedicineList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicine/getAll").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="[{\"mId\":1,\"medname\":\"medname\",\"mrp\":30,\"quantity\":2,\"expiryDate\":\"11-09-2019\",\"manuDate\":\"11-09-2018\"}]";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());
		System.out.println("retrieveDetailsForMedicine successfully executed...");

	}
	@Test()
	public void retrieveDetailsForMedicineNull() throws Exception
	{

		Mockito.when(med.getAllMedicines()).thenReturn(mockMedicineList1);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicine/getAll").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="There is not any record";
		assertEquals(expected, result.getResponse().getContentAsString());
		System.out.println("retrieveDetailsForMedicine successfully executed...");

	}

	@Test
	public void getByID() throws Exception
	{

		Mockito.when(med.getMedicineById(Mockito.anyInt())).thenReturn(mMock);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicine/getId/1")
				.requestAttr("id", mMock.getmId())
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected="{\"mId\":1,\"medname\":\"medname\",\"mrp\":30,\"quantity\":2,\"expiryDate\":\"11-09-2019\",\"manuDate\":\"11-09-2018\"}";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected, result.getResponse().getContentAsString());
		System.out.println("getMedicineById successfully executed...");
	}
	@Test()
	public void getByIDNotPresent() throws Exception
	{
		Mockito.when(med.getMedicineById(Mockito.anyInt())).thenReturn(mMock);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicine/getId/30")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isConflict());

	}
	@Test
	public void createMedicine() throws Exception {
		Mockito.when(med.addData(Mockito.any())).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/medicine/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"medname\":\"medname\",\"mrp\":30,\"quantity\":2,\"expiryDate\":\"11-09-2019\",\"manuDate\":\"11-09-2018\"}")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected=mockList;
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected,result.getResponse().getContentAsString());
		System.out.println("createMedicineForUser successfully executed...");

	}
	@Test
	public void createMedicineNull() throws Exception {

		Mockito.when(med.addData(Mockito.any())).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/medicine/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content("")
				.accept(MediaType.APPLICATION_JSON);
		String expected=null;
		assertNull(expected);
		System.out.println("createMedicineForUserNUll successfully executed...");
	}
	@Test()
	public void createMedicineName() throws Exception {

		Mockito.when(med.addData(Mockito.any())).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/medicine/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"medname\":\"12344\"}")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="{\"message\":\"Please provide valid medicine name\"}";
		assertEquals(expected, result.getResponse().getContentAsString());

	}
	@Test()
	public void createMedicineMrp() throws Exception {

		Mockito.when(med.addData(Mockito.any())).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/medicine/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"mrp\":hello}")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="{mrp:3}";
		assertNotEquals(expected, result.getResponse().getContentAsString());


	}

	@Test
	public void createMedicineDate() throws Exception {

		Mockito.when(med.addData(Mockito.any())).thenReturn(mockList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/medicine/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"expiryDate\":\"11-09-2019\"}")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="{\"message\":null}";
		assertEquals(expected, result.getResponse().getContentAsString());



	}

	@Test
	public void updateMedicine()throws Exception
	{
		Mockito.when(med.updateMedicine(Mockito.any())).thenReturn(mockUpdate);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicine/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"mId\":1,\"medname\":\"medname\",\"mrp\":30,\"quantity\":2,\"expiryDate\":\"11-09-2019\",\"manuDate\":\"11-09-2018\"}")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected=mockUpdate;
		assertEquals(expected, result.getResponse().getContentAsString());
		System.out.println("updateMedicineForUser successfully executed...");
	}
	@Test
	public void updateMedicineNull()throws Exception
	{
		Mockito.when(med.updateMedicine(Mockito.any())).thenReturn(mockUpdate);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/medicine/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("")
				.accept(MediaType.APPLICATION_JSON);
		String expected=null;
		assertNull(expected);
		System.out.println("updateMedicineForUserNull successfully executed...");
	}
	@Test
	public void testDeleteAllMedicineForUser() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicine/delete/1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}
	@Test
	public void testDeleteAllMedicineForUserIdNotPresent() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicine/delete/30").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}
	@Test
	public void getAllUsers()throws Exception
	{

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicine/users").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected="{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}]";
		assertEquals(expected, result.getResponse().getContentAsString());
		System.out.println("getAllUsers successfully executed...");

	}


}*/