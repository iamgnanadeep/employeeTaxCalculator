package com.incometax.employeetaxcalculator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.incometax.employeetaxcalculator.model.Employee;
import com.incometax.employeetaxcalculator.model.EmployeeTaxDetails;
import com.incometax.employeetaxcalculator.repository.EmployeeRepository;

/**
 * Implementation for Employee Details and Tax calculator
 * 
 * @author Gnanadeep
 *
 */
public class EmployeeTaxServiceImpl implements EmployeeTaxService {

	@Autowired
	EmployeeRepository repository;

	@Override
	public int addEmployee(final Employee emp) {

		return repository.save(emp).getId();
	}

	@Override
	public List<EmployeeTaxDetails> employeeTaxDetails(final String financialYear) {

		final List<EmployeeTaxDetails> employtaxDetailsList = new ArrayList<EmployeeTaxDetails>();

		final String nextFinancialYear = String.valueOf(Integer.parseInt(financialYear) + 1);
		final Optional<List<Employee>> employee = repository.getEmployeeData(nextFinancialYear + "-04-01", nextFinancialYear + "-03-31");
		if (employee.isPresent()) {
			List<Employee> empList = employee.get();
			for (Employee emp : empList) {
				EmployeeTaxDetails employTaxDetails = new EmployeeTaxDetails();
				employTaxDetails.setFirstName(emp.getFirstName());
				employTaxDetails.setEmployeeCode(emp.getId());
				employTaxDetails.setLastName(emp.getLastName());
				employTaxDetails.setYearlSalary(emp.getSal() * 12);
				employTaxDetails.setTax(taxApplicable(emp.getSal() * 12));
				employTaxDetails.setCess(cessApplied(emp.getSal() * 12));
				employtaxDetailsList.add(employTaxDetails);
			}

		}

		return employtaxDetailsList;
	}

	/**
	 * Method to calculate cess
	 * 
	 * @param income
	 * @return cess
	 */
	private Double cessApplied(final double income) {
		double cess = 0.0;

		if (income > 2500000) {
			cess = income * 0.02;
		}
		return cess;
	}

	/**
	 * Method to calculate taxes
	 * 
	 * @param income
	 * @return tax
	 */
	private double taxApplicable(final double income) {
		double tax = 0.0;
		if (income <= 250000) {
			tax = 0.0;
		} else if (income > 250000 && income <= 500000) {
			tax = (income - 250000) * 0.05;
		} else if (income > 500000 && income <= 100000) {
			tax = 12500 + (income - 500000) * 0.1;
		} else if (income > 1000000) {
			tax = (income - 1000000) * 0.2 + 62500;
		}
		return tax;
	}
	
	
	
}
