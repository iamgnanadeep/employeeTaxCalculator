package com.incometax.employeetaxcalculator.service;

import java.util.List;

import com.incometax.employeetaxcalculator.model.Employee;
import com.incometax.employeetaxcalculator.model.EmployeeTaxDetails;

/**
 * Interface for Employee Details and Tax calculator
 * @author Gnanadeep
 *
 */
public interface EmployeeTaxService {

	public int addEmployee(final Employee emp);
	 
	public List<EmployeeTaxDetails> employeeTaxDetails(final String financialYear);
	
}
