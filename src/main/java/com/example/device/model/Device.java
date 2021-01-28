package com.example.device.model;  
import javax.persistence.Entity;  
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;  

@Entity    
public class Device   
{   
@Id    
private int id;    
@Pattern(regexp="[a-zA-Z0-9]+-[a-zA-Z0-9]+",message="ER003")  
private String serialNumber; 
@NotBlank(message = "ER001")
private String machineCode;  
private String deviceName; 
  
public Device()  
{  
}    
public int getId()   
{    
return id;    
}    
public void setId(int id)   
{    
this.id = id;    
}    

public String getSerialNumber() {
	return serialNumber;
}
public void setSerialNumber(String serialNumber) {
	this.serialNumber = serialNumber;
}
public String getMachineCode() {
	return machineCode;
}
public void setMachineCode(String machineCode) {
	this.machineCode = machineCode;
}
public String getDeviceName() {
	return deviceName;
}
public void setDeviceName(String deviceName) {
	this.deviceName = deviceName;
}    
}    