package com.example.device.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.device.exception.DeviceException;
import com.example.device.model.Device;
import com.example.device.service.DeviceService;

@RestController
class DeviceController {

	@Autowired    
	private DeviceService deviceService;     
	@RequestMapping("/")    
	public List<Device> getAllDevice()  
	{    
	return deviceService.getAllDevices();    
	}    
	@RequestMapping("/serial/{serialNumber}")    
	public ResponseEntity<String> getAllDevicesBySerialNumber(@PathVariable("serialNumber") String serialNumber)  
	{    
	 Device device =deviceService.getAllDevicesBySerialNumber(serialNumber);    
	 if(device==null) {
		 throw new DeviceException("serial.number.not.found"," ER004" , "The serial number does not match our records.");
	 }else
	 return ResponseEntity.ok("Device is valid"); 
	}  
	@RequestMapping("/machine/{machineCode}")    
	public ResponseEntity<String> getAllDevicesByMachineCode(@PathVariable("machineCode") String machineCode)  
	{    
	 Device device = deviceService.getAllDevicesByMachineCode(machineCode);
	 if(device==null) {
		 throw new DeviceException("machine.code.not.found"," ER002" , "The machine code does not match our records.");
	 }else
	return ResponseEntity.ok("Device is valid"); 
	}  
	@RequestMapping(value="/add-device", method=RequestMethod.POST)    
	ResponseEntity<String> addDevice(@Valid @RequestBody Device device)  
	{    
	deviceService.addDevice(device);
	return ResponseEntity.ok("Device is valid");    
	} 
	
}