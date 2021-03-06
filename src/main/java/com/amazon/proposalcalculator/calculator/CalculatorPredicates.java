package com.amazon.proposalcalculator.calculator;

import java.util.function.Predicate;

import com.amazon.proposalcalculator.bean.DataTransferInput;
import com.amazon.proposalcalculator.bean.InstanceInput;
import com.amazon.proposalcalculator.bean.Price;
import com.amazon.proposalcalculator.bean.S3Price;
import com.amazon.proposalcalculator.enums.ProductFamily;
import com.amazon.proposalcalculator.enums.ProductName;
import com.amazon.proposalcalculator.enums.S3StorageClass;
import com.amazon.proposalcalculator.enums.SAPInstanceType;
import com.amazon.proposalcalculator.enums.Tenancy;
import com.amazon.proposalcalculator.enums.VolumeType;
import com.amazon.proposalcalculator.utils.Constants;

/**
 * Created by ravanini on 20/01/17.
 */
public class CalculatorPredicates {

	private static final int HANA_MIN_MEMORY = 61;

	public static Predicate<Price> sapProductionCertifiedInstances(InstanceInput server) {
		return p -> (p.getInstanceType().toLowerCase().startsWith("m4.")
				|| p.getInstanceType().toLowerCase().startsWith("m5.")
				|| p.getInstanceType().toLowerCase().startsWith("c4.")
				|| p.getInstanceType().toLowerCase().startsWith("c5.")
				|| p.getInstanceType().toLowerCase().startsWith("r4.")
				|| p.getInstanceType().toLowerCase().startsWith("r5.")
				|| p.getInstanceType().toLowerCase().startsWith("x1.")
				|| p.getInstanceType().toLowerCase().startsWith("x1e.")
				|| p.getInstanceType().toLowerCase().startsWith("u-")
				|| p.getInstanceType().toLowerCase().startsWith(Constants.STAND_BY_INSTANCE_TYPE));
	}

	public static Predicate<Price> hanaProductionCertifiedInstances(InstanceInput server) {
		boolean isCluster = server.getInstances() > 1;
		if (!isCluster
				&& SAPInstanceType.HANA_OLTP.equals(SAPInstanceType.getSAPInstanceType(server.getSapInstanceType()))) {
			return p -> (p.getMemory() >= HANA_MIN_MEMORY && (p.getInstanceType().toLowerCase().startsWith("r3.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r4.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r4.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.12xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.24xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.metal")
					|| p.getInstanceType().toLowerCase().startsWith("x1.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1.32xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1e.32xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("u-")));
		} else if (!isCluster
				&& SAPInstanceType.HANA_OLAP.equals(SAPInstanceType.getSAPInstanceType(server.getSapInstanceType()))) {
			return p -> (p.getMemory() >= HANA_MIN_MEMORY && (p.getInstanceType().toLowerCase().startsWith("r3.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r4.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r4.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.12xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.24xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.metal")
					|| p.getInstanceType().toLowerCase().startsWith("x1.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1.32xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1e.32xlarge")));
			// || p.getInstanceType().toLowerCase().startsWith("u-")));
		} else if (!isCluster
				&& SAPInstanceType.HANA_B1.equals(SAPInstanceType.getSAPInstanceType(server.getSapInstanceType()))) {
			return p -> (p.getMemory() >= HANA_MIN_MEMORY && (p.getInstanceType().toLowerCase().startsWith("c3.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("m4.10xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("m4.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r3.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.2xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.4xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.12xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.24xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1.16xlarge")));
		} else if (isCluster
				&& SAPInstanceType.HANA_OLAP.equals(SAPInstanceType.getSAPInstanceType(server.getSapInstanceType()))) {
			return p -> (p.getMemory() >= HANA_MIN_MEMORY && (p.getInstanceType().toLowerCase().startsWith("r3.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.24xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1.32xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1e.32xlarge")));
		} else {
			return p -> (p.getMemory() >= HANA_MIN_MEMORY && (p.getInstanceType().toLowerCase().startsWith("r3.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r4.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r4.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1.32xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1e.32xlarge")));
			// || p.getInstanceType().toLowerCase().startsWith("u-")));
		}
	}

	public static Predicate<Price> hanaDevQaInstances(InstanceInput server) {
		boolean isCluster = server.getInstances() > 1;
		if (isCluster
				&& SAPInstanceType.HANA_OLAP.equals(SAPInstanceType.getSAPInstanceType(server.getSapInstanceType()))) {
			return p -> (p.getMemory() >= HANA_MIN_MEMORY && (p.getInstanceType().toLowerCase().startsWith("r3.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.24xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1.32xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1e.32xlarge")));
		} else if (!isCluster
				&& SAPInstanceType.HANA_OLAP.equals(SAPInstanceType.getSAPInstanceType(server.getSapInstanceType()))) {
			return p -> (p.getMemory() >= HANA_MIN_MEMORY && (p.getInstanceType().toLowerCase().startsWith("r3.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r4.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r4.8xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.12xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.24xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("r5.metal")
					|| p.getInstanceType().toLowerCase().startsWith("u-6tb1.metal")
					|| p.getInstanceType().toLowerCase().startsWith("x1.16xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1.32xlarge")
					|| p.getInstanceType().toLowerCase().startsWith("x1e.32xlarge")));
		} else {
			return p -> (p.getMemory() >= HANA_MIN_MEMORY && (p.getInstanceType().toLowerCase().startsWith("m4.")
					|| p.getInstanceType().toLowerCase().startsWith("m5.")
					|| p.getInstanceType().toLowerCase().startsWith("c4.")
					|| p.getInstanceType().toLowerCase().startsWith("c5.")
					|| p.getInstanceType().toLowerCase().startsWith("r4.")
					|| p.getInstanceType().toLowerCase().startsWith("r5.")
					|| p.getInstanceType().toLowerCase().startsWith("x1.")
					|| p.getInstanceType().toLowerCase().startsWith("x1e.")
					|| p.getInstanceType().toLowerCase().startsWith("u-")));
		}
	}

	public static Predicate<Price> newGeneration(InstanceInput server) {
		return p -> ("no".equals(server.getOnlyCurrentGenerationInstances().toLowerCase()))
				|| ("yes".equals(server.getOnlyCurrentGenerationInstances().toLowerCase())
						&& (p.getCurrentGeneration() == null || "yes".equals(p.getCurrentGeneration().toLowerCase())));
	}

	public static Predicate<Price> burstable(InstanceInput server) {
		return p -> ("yes".equals(server.getUseBurstablePerformance().toLowerCase())
				|| (("no".equals(server.getUseBurstablePerformance().toLowerCase())
						&& !p.getInstanceType().toLowerCase().startsWith("t"))));
	}

	public static Predicate<Price> ec2(InstanceInput server) {
		return p -> (p.getInstanceType() != null && !p.getInstanceType().toLowerCase().startsWith("a1"))
				&& p.getProductFamily().startsWith("Compute") || p.getProductFamily().startsWith("Dedicated");
	}

	public static Predicate<Price> ec2NotAMD(InstanceInput server) {
		return p -> !p.getInstanceType().contains("a.");
	}

	public static Predicate<Price> region(InstanceInput server) {
		return p -> p.getLocation() != null && p.getLocation().equals(server.getRegion());
	}

	public static Predicate<Price> capacityStatus(InstanceInput server) {
		return p -> "used".equals(p.getCapacityStatus().toLowerCase());
	}

	public static Predicate<S3Price> s3(InstanceInput input) {
		return p -> p.getLocation() != null && input.getRegion().toLowerCase().startsWith(p.getLocation().toLowerCase())
				&& ProductFamily.Storage.toString().equals(p.getProductFamily())
				&& ProductName.AmazonS3.toString().equals(p.getServiceCode())
				&& S3StorageClass.General_Purpose.getColumnName().equals(p.getStorageClass())
				&& Constants.S3EndRange.equals(p.getEndingRange());
	}

	public static Predicate<Price> dataTransferOutPrice(String fromLocation) {
		return p -> p.getFromLocation() != null
				&& fromLocation.toLowerCase().startsWith(p.getFromLocation().toLowerCase())
				&& "External".toLowerCase().startsWith(p.getToLocation().toLowerCase())
				&& "AWS Outbound".toLowerCase().startsWith(p.getTransferType().toLowerCase())
				&& ProductFamily.Data_Transfer.getName().toString().equals(p.getProductFamily());
	}

	public static Predicate<Price> region(DataTransferInput dataTransfer) {
		return p -> p.getFromLocation() != null
				&& p.getFromLocation().toLowerCase().startsWith(dataTransfer.getRegion().toLowerCase());
	}

	public static Predicate<Price> cpu(InstanceInput server) {
		return p -> server.getCpu() == 0 || p.getvCPU() >= server.getCpu() * ((1 - server.getCpuTolerance()));
	}

	public static Predicate<Price> saps(InstanceInput server) {
		return p -> server.getSaps() == 0 || p.getSaps() >= server.getSaps() * ((1 - server.getCpuTolerance()));
	}

	public static Predicate<Price> memory(InstanceInput server) {
		return p -> server.getMemory() == 0
				|| p.getMemory() >= server.getMemory() * ((1 - server.getMemoryTolerance()));
	}

	public static Predicate<Price> preInstalledSw(InstanceInput server) {
		return p -> p.getPreInstalledSw() != null && p.getPreInstalledSw().equalsIgnoreCase(server.getPreInstalledSw());
	}

	public static Predicate<Price> termType(InstanceInput server) {
		return p -> (p.getTermType() != null && p.getTermType().equals(server.getTermType()));
	}

	public static Predicate<Price> leaseContractLength(InstanceInput server) {
		return p -> server.getTermType().equals("OnDemand")
				|| (server.getTermType().equals("Reserved") && p.getLeaseContractLength() != null
						&& p.getLeaseContractLength().trim().equalsIgnoreCase(server.getLeaseContractLength().trim()));
	}

	public static Predicate<Price> purchaseOption(InstanceInput server) {
		return p -> server.getTermType().equals("OnDemand") || (server.getTermType().equals("Reserved")
				&& p.getPurchaseOption() != null && p.getPurchaseOption().equalsIgnoreCase(server.getPurchaseOption()));
	}

	public static Predicate<Price> offeringClass(InstanceInput server) {
		return p -> server.getTermType().equals("OnDemand") || (server.getTermType().equals("Reserved")
				&& p.getOfferingClass() != null && p.getOfferingClass().equalsIgnoreCase(server.getOfferingClass()));
	}

	public static Predicate<Price> operatingSystem(InstanceInput server) {
		return p -> p.getOperatingSystem() != null && p.getOperatingSystem().equals(server.getOperatingSystem());
	}

	public static Predicate<Price> licenceModel(InstanceInput server) {
		return p -> p.getLicenseModel() != null && p.getLicenseModel().equals("No License required");
	}

	public static Predicate<Price> tenancy(InstanceInput server) {
		return p -> (Tenancy.Shared.toString().equals(p.getTenancy())
				|| Tenancy.Reserved.toString().equals(p.getTenancy()));
	}

	public static Predicate<Price> volumeType(InstanceInput input) {
		return p -> p.getVolumeType() != null && p.getVolumeType().equalsIgnoreCase(input.getVolumeType());
	}

	public static Predicate<Price> st1() {
		return p -> p.getVolumeType() != null
				&& p.getVolumeType().equalsIgnoreCase(VolumeType.Throughput_Optimized_HDD.getColumnName());
	}

	public static Predicate<Price> group(String group) {
		return p -> p.getGroup() != null && p.getGroup().equalsIgnoreCase(group);
	}

	public static Predicate<Price> snapshot() {
		return p -> p.getProductFamily() != null && p.getProductFamily().equalsIgnoreCase("Storage Snapshot");
	}

	public static Predicate<Price> dataTransferOut(DataTransferInput dataTransfer) {
		return p -> p.getProductFamily() != null && p.getProductFamily().equalsIgnoreCase("Data Transfer")
				&& p.getTransferType() != null && p.getTransferType().equalsIgnoreCase("AWS Outbound")
				&& p.getStartingRangeAsLong() < dataTransfer.getDataTransferOut();
	}

}
