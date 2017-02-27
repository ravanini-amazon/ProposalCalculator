package com.amazon.proposalcalculator.calculator;

import com.amazon.proposalcalculator.bean.InstanceInput;
import com.amazon.proposalcalculator.enums.*;
import com.amazon.proposalcalculator.exception.PricingCalculatorException;

/**
 * Created by ravanini on 18/02/17.
 */
public class ValidateCommonInputs {


    static final String DESCRIPTION_IS_A_MANDATORY_FIELD = "Description is a mandatory field.";
    static final String REGION_IS_A_MANDATORY_FIELD = "Region is a mandatory field.";
    static final String WHEN_RESERVED_PURCHASE_OPTION_IS_MANDATORY = "When TermType = Reserved, Purchase Option is a mandatory field.";
    static final String OPERATING_SYSTEM_IS_MANDATORY = "Operating System is a mandatory field.";
    static final String WHEN_RESERVED_LEASE_CONTRACT_LENGTH_IS_MANDATORY = "When TermType = Reserved, Lease Contract Length is a mandatory field.";

    static void validate(InstanceInput input){

        validateDescription(input.getDescription());

        validateRegion(input.getRegion());

        input.setOperatingSystem(fillOperatingSystem(input.getOperatingSystem()));

//        input.setCpu(fillCPU(input.getCpu()));

        input.setCpuTolerance(fillCpuTolerance(input.getCpuTolerance()));

//        input.setMemory(fillMemory(input.getMemory()));

        input.setMemoryTolerance(fillMemoryTolerance(input.getMemoryTolerance()));

        input.setMonthlyUtilization(fillMonthlyUtilization(input.getMonthlyUtilization()));

//        input.setStorage(fillStorage(input.getStorage()));

        input.setVolumeType(fillVolumeType(input.getVolumeType()));

        input.setIops(fillIops(input.getIops()));

//        input.setSnapshot(fillSnapshot(input.getSnapshot()));

//        input.setS3Backup(fillS3Backup(input.getS3Backup()));

        input.setTermType(fillTermType(input.getTermType()));

        validateLeaseContractLength(input.getLeaseContractLength(), input.getTermType());

        validatePurchaseOption(input.getPurchaseOption(), input.getTermType());

        input.setOfferingClass(fillOfferingClass(input.getOfferingClass(), input.getTermType()));

        input.setTenancy(fillTenancy(input.getTenancy()));

        input.setPreInstalledSw(fillPreInstalledSoftware(input.getPreInstalledSw()));
    }

    private static String fillPreInstalledSoftware(String preInstalledSw) {
        return (preInstalledSw == null) ? PreInstalledSoftware.NA.name()
                : PreInstalledSoftware.getPreInstalledSoftware(preInstalledSw).name();
    }

    private static String fillTenancy(String tenancy) {

        return (tenancy == null) ? Tenancy.Shared.name()
                : Tenancy.getTenancy(tenancy).name();
    }

    private static String fillOperatingSystem(String operatingSystem) {

        if(operatingSystem == null){
            throw new PricingCalculatorException(OPERATING_SYSTEM_IS_MANDATORY);
        }
        return OperatingSystem.getOperatingSystem(operatingSystem).name();
    }

    private static String fillOfferingClass(String offeringClass, String termType) {
        if(TermType.valueOf(termType).equals(TermType.Reserved) && offeringClass == null){
            return OfferingClass.Standard.name();
        }
        return offeringClass;
    }

    private static void validateLeaseContractLength(String leaseContractLength, String termType) {
        if(TermType.valueOf(termType).equals(TermType.Reserved)){
            if (leaseContractLength == null){
                throw new PricingCalculatorException(WHEN_RESERVED_LEASE_CONTRACT_LENGTH_IS_MANDATORY);
            }
            LeaseContractLength.getLeaseContractLength(leaseContractLength).getColumnName();
        }
//        return "";
    }

    private static void validatePurchaseOption(String purchaseOption, String termType) {
        if(TermType.valueOf(termType).equals(TermType.Reserved)){
            if (purchaseOption == null){
                throw new PricingCalculatorException(WHEN_RESERVED_PURCHASE_OPTION_IS_MANDATORY);
            }
            PurchaseOption.getPurchaseOption(purchaseOption).getColumnName();
        }
//        return "";
    }

    private static String fillTermType(String termType) {
        return (termType == null) ? TermType.OnDemand.name() : TermType.getTermType(termType).name();
    }

//    private static Integer fillS3Backup(Integer s3Backup) {
//        return (s3Backup == null) ? Integer.valueOf("0") : s3Backup;
//    }

//    private static Integer fillSnapshot(Integer snapshot) {
//        return (snapshot == null) ? Integer.valueOf("0") : snapshot;
//    }

    private static Integer fillIops(Integer iops) {
        return (iops == null) ? Integer.valueOf("0") : iops;
    }

    private static Double fillMemoryTolerance(Double memoryTolerance) {
        return (memoryTolerance == null) ? Double.valueOf("0") : memoryTolerance;
    }

    private static Double fillCpuTolerance(Double cpuTolerance) {
        return (cpuTolerance == null) ? Double.valueOf("0") : cpuTolerance;
    }

    private static String fillVolumeType(String volumeType) {
        return volumeType != null ? VolumeType.getVolumeType(volumeType).getColumnName() : VolumeType.General_Purpose.getColumnName();
    }

//    private static Integer fillStorage(Integer storage) {
//        return (storage == null) ? Integer.valueOf("0") : storage;
//    }

    private static Integer fillMonthlyUtilization(Integer monthlyUtilization) {
        return (monthlyUtilization == null) ? Integer.valueOf("100") : monthlyUtilization;
    }

//    private static Double fillMemory(Double memory) {
//        return (memory == null) ? Integer.valueOf("0") : memory;
//    }

//    private static Integer fillCPU(Integer cpu) {
//        return (cpu == null) ? Integer.valueOf("0") : cpu;
//    }

    private static void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()){
            throw new PricingCalculatorException(DESCRIPTION_IS_A_MANDATORY_FIELD);
        }
    }

    private static void validateRegion(String region) {
        //TODO VALIDATE REGIONS reading FILE FROM S3 - GET IDEA FROM Singh, Harpreet <batrahs@amazon.com>

        if (region == null)
            throw new PricingCalculatorException(REGION_IS_A_MANDATORY_FIELD);
        Region.getRegion(region).getColumnName();
    }
}