/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.cloudbus.cloudsim;


/**
 * Implements a model, according to which a Cloudlet generates
 * random resource utilization every time frame.
 * 
 * @author Anton Beloglazov
 * @since CloudSim Toolkit 2.0
 * @todo This class is the only one that stores the utilization history and
 * make use of the time attribute at the {@link #getUtilization(double) } method.
 * Check the other classes to implement the same behavior
 * (that can be placed in the super class)
 */
public class UtilizationModelDualPhase implements UtilizationModel {

	/** The scheduling interval. */
	private double schedulingInterval;

	/** The data (5 min * 288 = 24 hours). */
	private final double[] data = {7,6,5,7,7,5,7,5,7,7,6,5,7,7,6,5,5,5,5,5,5,7,7,
			6,6,6,6,6,6,6,7,6,7,7,7,7,6,5,5,6,6,6,5,7,7,7,5,5,6,7,5,6,7,6,7,5,7,5,
			7,5,6,7,5,7,6,6,5,7,7,5,6,5,5,7,5,6,6,5,7,5,7,6,7,6,6,6,6,7,7,6,7,7,6,
			7,6,6,5,7,5,5,5,7,7,7,5,5,7,6,7,6,7,5,5,6,7,7,7,5,6,6,7,7,6,5,5,7,6,6,
			5,7,7,6,5,6,6,7,5,5,6,5,5,5,7,7,5,6,7,7,5,6,7,6,5,5,7,6,7,5,7,7,6,7,7,
			6,7,5,5.5,7,7.86,8,8.36,8.98,9.43,10.3,10.71,10.86,11.97,12.13,13.17,
			13.84,14.55,14.74,14.92,15.23,15.93,16.27,16.76,17.05,17.23,18.32,19.09,
			19.44,20.06,20.54,20.91,21.85,22.85,23.82,24.19,25.15,25.63,26.56,27,
			27.28,27.97,29.06,29.62,29.74,30.66,31.01,31.47,31.94,32.89,33.83,34.12,
			35.03,35.86,36.26,37.06,37.85,37.98,38.78,39.4,39.52,40.32,41.21,41.88,
			42.33,42.94,43.48,44.16,44.71,44.9,45.29,45.5,46.24,47.25,47.98,48.85,
			48,48,49,49,47,48,49,50,51,51,51,51,51,51,53,53,51,51,51,51,53,53,52,53,
			51,53,51,52,52,53,51,53,53,52,53,53,51,53,52,52,52,51,52,51,51,52,53,52,
			51,52,52,51,51,51,53,51,52,52,52,52,52,53,53,51,53,51,51,51,52,53,51,52,
			51,52,52,52,51,52,53,52,53,53,53,51,51,52,53,52,53,52,52,52,51,52,53,51,
			53,53,53,52,51,52,52,51,52,51,51,53,53,53,53,52,52,52,52,51,53,52,51,51,
			52,53,52,53,51,53,53,51,51,53,53,53,51,51,52,53,51,53,53,53,51,53,53,51,52, 52};

	/**
	 * Instantiates a new utilization model stochastic.
	 */
	public UtilizationModelDualPhase(double schedulingInterval) {
		setSchedulingInterval(schedulingInterval);
	}
	
	/**
	 * Gets the scheduling interval.
	 * 
	 * @return the scheduling interval
	 */
	public double getSchedulingInterval() {
		return schedulingInterval;
	}
	
	/**
	 * Sets the scheduling interval.
	 * 
	 * @param schedulingInterval the new scheduling interval
	 */
	public void setSchedulingInterval(double schedulingInterval) {
		this.schedulingInterval = schedulingInterval;
	}


	@Override
	public double getUtilization(double time) {
		if (time % getSchedulingInterval() == 0) {
			return data[(int) time / (int) getSchedulingInterval()];
		}
		int time1 = (int) Math.floor(time / getSchedulingInterval());
		int time2 = (int) Math.ceil(time / getSchedulingInterval());
		double utilization1 = data[time1];
		double utilization2 = data[time2];
		double delta = (utilization2 - utilization1) / ((time2 - time1) * getSchedulingInterval());
		double utilization = utilization1 + delta * (time - time1 * getSchedulingInterval());
		return utilization;

	}
}