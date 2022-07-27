package com.example.bikestores.service.business;

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = { BikestoresBusiness.class})
class BikestoresBusinessJunit4Tests {

	//	@Autowired
	//	private BikestoresBusiness bikestoresBusiness;
	//
	//	@MockBean
	//	private BikestoresEntity bikestoresEntity;
	//
	//	@MockBean
	//	private RestTemplate restTemplate;

	//	@Test
	//	void readCustomersTest() {
	//		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
	//		Mockito.when(bikestoresEntity.readCustomers()).thenReturn(customerDTOList);
	//		List<CustomerDTO> customerDTOListResponse = bikestoresBusiness.readCustomers();
	//		assertNotNull(customerDTOListResponse);
	//	}
	//
	//	@Test
	//	void readCustomersByIdTest() {	
	//		List<CustomerDTO> customerDTOList = buildCustomerDTOList();
	//		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(customerDTOList);
	//		List<CustomerDTO> customerDTOListResponse = bikestoresBusiness.readCustomersById("1570");
	//		assertNotNull(customerDTOListResponse);
	//		assertEquals(customerDTOListResponse,customerDTOListResponse);
	//	}
	//
	//	@Test(expected = CustomerNotFoundException.class)
	//	void readCustomersByIdEmptyTest() {	
	//		Mockito.when(bikestoresEntity.readCustomersById(Mockito.any())).thenReturn(new ArrayList<>());
	//		bikestoresBusiness.readCustomersById("1570");
	//	}
	//
	//	private List<CustomerDTO> buildCustomerDTOList() {
	//		List<CustomerDTO> customerDTOList = new ArrayList<>();
	//		CustomerDTO customerDTO = new CustomerDTO();
	//		customerDTOList.add(customerDTO);
	//		return customerDTOList;
	//	}
}
