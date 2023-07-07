package com.incometax.employeetaxcalculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incometax.employeetaxcalculator.model.Employee;
import com.incometax.employeetaxcalculator.model.EmployeeTaxDetails;
import com.incometax.employeetaxcalculator.service.EmployeeTaxService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

/**
 * This class adds employee details and calculates tax details
 * @author Gnanadeep
 *
 */
@RestController
@RequestMapping("api")
public class EmployeeTaxController {


	@Autowired
	EmployeeTaxService empTaxServ;

	@RequestMapping(method = RequestMethod.POST, path = "/emp")
	public ResponseEntity<String> addEmployeeDetails(final @Valid @RequestBody Employee emp) {
		int empId = empTaxServ.addEmployee(emp);
		return ResponseEntity.ok("Employee Created with ID :" + empId);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/emp")
	public ResponseEntity<List<EmployeeTaxDetails>> employeeTaxCalculator(final @RequestParam("financialYear") @Pattern(regexp="\\d{4}") String financialYear) {
		List<EmployeeTaxDetails> employeeTaxDetailsList = empTaxServ.employeeTaxDetails(financialYear);
		return ResponseEntity.ok(employeeTaxDetailsList);
	}
	
	


}
