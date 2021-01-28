package com.example.device.repository;
 
import org.springframework.data.repository.CrudRepository;

import com.example.device.model.Device;  
   
public interface DeviceRepository extends CrudRepository<Device, String>   
{    
	Device findBySerialNumber(String serialNumber);
	Device findByMachineCode(String machineCode);
}   