package com.amazon.proposalcalculator.utils;

import java.util.Map;
import java.util.TreeMap;

public class SAPS {

	private Map<String, Integer> sapsMap = null;
	private static SAPS instance;

	private SAPS() {

	}

	public Integer getSAPS(String instanceType) {
		Object result = sapsMap.get(instanceType.toLowerCase());
		if (result != null)
			return (Integer) result;
		return 0;
	}

	public static SAPS getInstance() {
		if (instance == null) {
			instance = new SAPS();
			instance.sapsMap = new TreeMap<String, Integer>();
			instance.sapsMap.put("m4.16xlarge", 75770);
			instance.sapsMap.put("m4.10xlarge", 47320);
			instance.sapsMap.put("m4.4xlarge", 18928);
			instance.sapsMap.put("m4.2xlarge", 9464);
			instance.sapsMap.put("m4.xlarge", 4732);
			instance.sapsMap.put("m4.large", 2366);
			instance.sapsMap.put("c4.8xlarge", 37950);
			instance.sapsMap.put("c4.4xlarge", 19030);
			instance.sapsMap.put("c4.2xlarge", 9515);
			instance.sapsMap.put("c4.xlarge", 4758);
			instance.sapsMap.put("c4.large ", 2379);
			instance.sapsMap.put("c3.8xlarge", 31830);
			instance.sapsMap.put("c3.4xlarge", 15915);
			instance.sapsMap.put("c3.2xlarge", 7957);
			instance.sapsMap.put("c3.xlarge", 3978);
			instance.sapsMap.put("c3.large", 1989);
			instance.sapsMap.put("r4.16xlarge", 76400);
			instance.sapsMap.put("r4.8xlarge", 38200);
			instance.sapsMap.put("r4.4xlarge", 19100);
			instance.sapsMap.put("r4.2xlarge", 9550);
			instance.sapsMap.put("r4.xlarge", 4775);
			instance.sapsMap.put("r4.large", 2387);
			instance.sapsMap.put("r3.8xlarge", 31920);
			instance.sapsMap.put("r3.4xlarge", 15960);
			instance.sapsMap.put("r3.2xlarge", 7980);
			instance.sapsMap.put("r3.xlarge", 3990);
			instance.sapsMap.put("r3.large", 1995);
			instance.sapsMap.put("x1.32xlarge", 131500);
			instance.sapsMap.put("x1.16xlarge", 65750);
			
			//Previous generation instances
			instance.sapsMap.put("cc2.8xlarge", 30430); //90330 as a database server for 3-tier config
			instance.sapsMap.put("cr1.8xlarge", 30430);
			instance.sapsMap.put("m2.4xlarge", 7400);
			instance.sapsMap.put("m2.2xlarge", 3700);
		}
		return instance;
	}

}