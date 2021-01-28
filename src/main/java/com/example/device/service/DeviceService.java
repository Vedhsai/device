package com.example.device.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;  
import com.example.device.model.Device;  
import com.example.device.repository.DeviceRepository;    
@Service    
public class DeviceService   
{    
@Autowired    
private DeviceRepository deviceRepository;  
public Device getAllDevicesBySerialNumber(String serialNumber) {
Device device=	deviceRepository.findBySerialNumber(serialNumber);
return device;
}
public Device getAllDevicesByMachineCode(String machineCode) {
Device device=	deviceRepository.findByMachineCode(machineCode);
return device;
}
public List<Device> getAllDevices()  
{    
List<Device> devices = new ArrayList<>();    
deviceRepository.findAll().forEach(devices::add);    
return devices;    
}    
public void addDevice(Device device)  
{    
deviceRepository.save(device);    
}    
}   