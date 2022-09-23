package com.hibret.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.hibret.soap.consumingwebservice.wsdl.AddEmployeeRequest;
import com.hibret.soap.consumingwebservice.wsdl.AddEmployeeResponse;
import com.hibret.soap.consumingwebservice.wsdl.DeleteEmployeeRequest;
import com.hibret.soap.consumingwebservice.wsdl.DeleteEmployeeResponse;
import com.hibret.soap.consumingwebservice.wsdl.EmployeeInfo;
import com.hibret.soap.consumingwebservice.wsdl.GetAllEmployeesRequest;
import com.hibret.soap.consumingwebservice.wsdl.GetAllEmployeesResponse;
import com.hibret.soap.consumingwebservice.wsdl.GetEmployeeByFirstNameRequest;
import com.hibret.soap.consumingwebservice.wsdl.GetEmployeeByFirstNameResponse;
import com.hibret.soap.consumingwebservice.wsdl.GetEmployeeByIdRequest;
import com.hibret.soap.consumingwebservice.wsdl.GetEmployeeByIdResponse;
import com.hibret.soap.consumingwebservice.wsdl.UpdateEmployeeRequest;
import com.hibret.soap.consumingwebservice.wsdl.UpdateEmployeeResponse;

public class EmployeeClient extends WebServiceGatewaySupport   {
	
	public GetEmployeeByIdResponse getEmployeeById(long EmployeeId) {
		GetEmployeeByIdRequest request = new GetEmployeeByIdRequest();
		request.setId(EmployeeId);
		GetEmployeeByIdResponse response = (GetEmployeeByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8181/ws/getEmployeeByIdRequest"));
		return response;
	}
	
	public GetEmployeeByFirstNameResponse getEmployeeByFirstName(String fname) {
		GetEmployeeByFirstNameRequest request = new GetEmployeeByFirstNameRequest();
		request.setFirstName(fname);
		GetEmployeeByFirstNameResponse response = (GetEmployeeByFirstNameResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8181/ws/getEmployeeByFirstNameRequest"));
		return response;
	}
	
	public GetAllEmployeesResponse getAllEmployees() {
		GetAllEmployeesRequest request = new GetAllEmployeesRequest();
		GetAllEmployeesResponse response = (GetAllEmployeesResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8181/ws/getAllEmployeesRequest"));
     	        return response;
	}	
	public AddEmployeeResponse addEmployee(String fname, String lname, String email) {
		AddEmployeeRequest request = new AddEmployeeRequest();
		request.setFirstName(fname);
		request.setLastName(lname);
		request.setEmailId(email);
		AddEmployeeResponse response = (AddEmployeeResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8181/ws/addEmployeeRequest"));
     	        return response;
	}	
	public UpdateEmployeeResponse updateEmployee(EmployeeInfo EmployeeInfo) {
		UpdateEmployeeRequest request = new UpdateEmployeeRequest();
		request.setEmployeeInfo(EmployeeInfo);
		UpdateEmployeeResponse response = (UpdateEmployeeResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8181/ws/updateEmployeeRequest"));
     	        return response;
	}	
	public DeleteEmployeeResponse deleteEmployee(long EmployeeId) {
		DeleteEmployeeRequest request = new DeleteEmployeeRequest();
		request.setId(EmployeeId);
		DeleteEmployeeResponse response = (DeleteEmployeeResponse) getWebServiceTemplate().marshalSendAndReceive(
				request, new SoapActionCallback("http://localhost:8181/ws/deleteEmployeeRequest"));
     	        return response;
	}	
}
