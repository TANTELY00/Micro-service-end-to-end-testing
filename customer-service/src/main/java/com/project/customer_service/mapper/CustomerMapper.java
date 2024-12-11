package com.project.customer_service.mapper;

import com.project.customer_service.dtos.CustomerDto;
import com.project.customer_service.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public CustomerDto fromCustomer(Customer customer){
        return modelMapper.map(customer, CustomerDto.class); // source et destination
    }

    public Customer fromCustomerDto(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class); // source et destination
    }

    public List<CustomerDto> fromListCustomer(List<Customer> customers){
        return customers.stream().map(c -> modelMapper.map(c,CustomerDto.class)).collect(Collectors.toList());
    }

}
