package nordcloudtest.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Device {
	private DeviceLocation deviceLocation;
	private ArrayList<BestStation> bestStations;
	private static DecimalFormat df;
	public Device(int x, int y) {
		deviceLocation = new DeviceLocation(x,y);
		bestStations = new ArrayList<BestStation>();
		bestStations.add(new BestStation(null, -1));
		df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.UP);
	}

	public DeviceLocation getDeviceLocation() {
		return deviceLocation;
	}

	public void setDeviceLocation(DeviceLocation deviceLocation) {
		this.deviceLocation = deviceLocation;
	}

	public ArrayList<BestStation> getBestStation() {
		return bestStations;
	}

	public void setBestStation(Station bestStation, double power) {

			for (BestStation existingBestStation : bestStations) {
				if(existingBestStation.power < power)
				{
					bestStations.remove(existingBestStation);
					bestStations.add(new BestStation(bestStation, power));
				}
			}

}

	


	@Override
	public String toString() {
		return "(" + deviceLocation + ")";
	}



	public class BestStation{
		public Station station;
		public double power=0;
		public BestStation(Station bestStation, double power2) {
			station = bestStation;
			this.power = power2;
		}
		
		
		public double getPower() {
			return power;
		}


		public void setPower(double power) {
			this.power = power;
		}


		@Override
		public String toString() {
			return station + ", with power=" + df.format(power);
		}
		
		
	}
	
	
}
