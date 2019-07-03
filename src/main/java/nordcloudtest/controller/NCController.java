package nordcloudtest.controller;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nordcloudtest.model.Device;
import nordcloudtest.model.Device.BestStation;
import nordcloudtest.model.DeviceLocation;
import nordcloudtest.model.Station;

@RestController
public class NCController {
	
	private ArrayList<Station> stations;
	private ArrayList<Device> devices;
	

	@RequestMapping("/")
	public String home() {

		prepareDefaultStations();
		prepareDefaultDevices();
		bestStation();
		String response = "";
		
		for (Device device : devices) {
			String string = "Best Staion for point "+device+" is ";
			for (BestStation bestStation : device.getBestStation()) {
				if(bestStation.power > 0)
					string += bestStation;
				else
					string = "No link station within reach for point"+device;
			}
			response +=string+"<br>";
		}
		return response;
	}

	public static void main(String[] args) {
		NCController nc = new NCController();
		nc.prepareDefaultStations();
		nc.prepareDefaultDevices();
		nc.bestStation();
		String response = "";
		for (Device device : nc.devices) {
			String string = "Best Staion for point "+device+" is ";
			for (BestStation bestStation : device.getBestStation()) {
				if(bestStation.power > 0)
					string += bestStation;
				else
					string = "No link station within reach for point"+device;
			}
			response +=string+"\n";
		}
		
		System.out.println(response);
	}
	
	private void prepareDefaultStations() {
		stations = new ArrayList<Station>();

		stations.add(new Station(0, 0, 10));
		stations.add(new Station(20, 20, 5));
		stations.add(new Station(10, 0, 12));
	}
	
	
	private void prepareDefaultDevices() {
		devices = new ArrayList<Device>();

		devices.add(new Device(0, 0));
		devices.add(new Device(100, 100));
		devices.add(new Device(15, 10));
		devices.add(new Device(18, 18));

	}
	
	
	
	
	public void bestStation() {
		for (Device device : devices) {
			for (Station station : stations) {
				double power = 0;
				double distance = distance(device.getDeviceLocation(), station);
				if(distance > station.getR()) {
					power=0;
				}
				else {
				 power = power(device.getDeviceLocation(),station);
				}
				device.setBestStation(station, power);
			}
		}
	}
	
	public double power(DeviceLocation deviceLocation, Station station) {
		double distance = distance(deviceLocation, station);
		if(distance>station.getR()) return 0;
		else return (double)Math.pow((station.getR()-distance),2); 
		
	}

	private double distance(DeviceLocation deviceLocation, Station station) {
		return Math.sqrt(Math.pow(Math.abs(deviceLocation.getX() - station.getX()), 2)
				+ Math.pow(Math.abs(deviceLocation.getY() - station.getY()), 2));
	}

}
