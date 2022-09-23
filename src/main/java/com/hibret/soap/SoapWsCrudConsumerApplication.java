package com.hibret.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hibret.soap.consumingwebservice.wsdl.AddEmployeeResponse;
import com.hibret.soap.consumingwebservice.wsdl.DeleteEmployeeResponse;
import com.hibret.soap.consumingwebservice.wsdl.EmployeeInfo;
import com.hibret.soap.consumingwebservice.wsdl.GetAllEmployeesResponse;
import com.hibret.soap.consumingwebservice.wsdl.GetEmployeeByFirstNameResponse;
import com.hibret.soap.consumingwebservice.wsdl.GetEmployeeByIdResponse;
import com.hibret.soap.consumingwebservice.wsdl.ServiceStatus;
import com.hibret.soap.consumingwebservice.wsdl.UpdateEmployeeResponse;

@SpringBootApplication
@CrossOrigin("*")
@RestController
public class SoapWsCrudConsumerApplication {

	@Autowired
	EmployeeClient Client;
	public static void main(String[] args) {
		SpringApplication.run(SoapWsCrudConsumerApplication.class, args);
	}
	
	@GetMapping("/employees/{id}")
	public EmployeeInfo getEmployeeById(@PathVariable("id") long id) {
	GetEmployeeByIdResponse employeeByIdResponse = Client.getEmployeeById(id);
	EmployeeInfo EmployeeInfo = employeeByIdResponse.getEmployeeInfo();
	return EmployeeInfo;
	}
	
	@GetMapping("/employees/filter")
	public List<EmployeeInfo> getEmployeeByFirstName(@RequestParam(name = "first-name") String fname) {
		GetEmployeeByFirstNameResponse employeeByFirstNameResponse = Client.getEmployeeByFirstName(fname);
	List<EmployeeInfo> EmployeeInfo = employeeByFirstNameResponse.getEmployeeInfo();
	return EmployeeInfo;
	}
	
	@GetMapping("/employees")
	public List<EmployeeInfo> viewEmployees() {
		System.out.println("--- Get all Employees ---");
		GetAllEmployeesResponse allEmployeesResponse = Client.getAllEmployees();
		List<EmployeeInfo> EmployeeInfo = allEmployeesResponse.getEmployeeInfo();
    return EmployeeInfo;
}
	
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED) 
	public EmployeeInfo createEmployee(@RequestBody EmployeeInfo employee)
	{ 
		System.out.println("--- Add Employee ---");
		AddEmployeeResponse addEmployeeResponse = Client.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getEmailId());
		EmployeeInfo employeeInfo = addEmployeeResponse.getEmployeeInfo();
		ServiceStatus serviceStatus = addEmployeeResponse.getServiceStatus();
		return employeeInfo;
		}
	

	@PutMapping("/employees/{id}") 
	public UpdateEmployeeResponse updateEmployee(@PathVariable("id") long employeeId,@RequestBody EmployeeInfo employee)
	{ 
		System.out.println("--- Update Employee ---");
		GetEmployeeByIdResponse employeeByIdResponse = Client.getEmployeeById(employeeId);
		EmployeeInfo employeeInfo = employeeByIdResponse.getEmployeeInfo();
		employeeInfo.setFirstName(employee.getFirstName());
		employeeInfo.setLastName(employee.getLastName());
		employeeInfo.setEmailId(employee.getEmailId());

		UpdateEmployeeResponse updateEmployeeResponse = Client.updateEmployee(employeeInfo);
		ServiceStatus serviceStatus = updateEmployeeResponse.getServiceStatus(); 
	    
		return updateEmployeeResponse;
	}

	@DeleteMapping("/employees/{id}")
	public DeleteEmployeeResponse deleteEmployee(@PathVariable("id") long employeeId){
		System.out.println("--- Delete Employee ---");
		DeleteEmployeeResponse deleteEmployeeResponse = Client.deleteEmployee(employeeId);
		ServiceStatus serviceStatus = deleteEmployeeResponse.getServiceStatus();		
        return deleteEmployeeResponse;

	}
}

	

	
