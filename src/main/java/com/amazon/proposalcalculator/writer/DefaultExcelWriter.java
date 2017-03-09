package com.amazon.proposalcalculator.writer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.amazon.proposalcalculator.bean.InstanceOutput;
import com.amazon.proposalcalculator.bean.Quote;
import com.amazon.proposalcalculator.enums.QuoteName;
import com.amazon.proposalcalculator.utils.Constants;
import com.amazon.proposalcalculator.utils.SomeMath;
import com.ebay.xcelite.Xcelite;
import com.ebay.xcelite.sheet.XceliteSheet;
import com.ebay.xcelite.writer.SheetWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultExcelWriter {

	private final static Logger LOGGER = LogManager.getLogger();

	public void write(String outputFileName) {
		LOGGER.info("Writing output spreadsheet...");
		Xcelite xcelite = new Xcelite();
		
		
		XceliteSheet summarySheet = xcelite.createSheet("Summary");
		SheetWriter<Collection<Object>> simpleWriter = summarySheet.getSimpleWriter();
		List<Collection<Object>> data = new ArrayList<Collection<Object>>();
		
		List<Object> list1 = new ArrayList<Object>();
		list1.add("Payment");
		list1.add("1yr Upfront");
		list1.add("3yr Upfront");
		list1.add("Monthly");
		list1.add("3 Years Total");
		list1.add("Discount");
		data.add(list1);
		
		for (Quote quote : Constants.quotes) {
			List<Object> list2 = new ArrayList<Object>();
			list2.add(quote.getName());
			
			if ("1yr".equals(quote.getLeaseContractLength())) {
				list2.add(SomeMath.round(quote.getUpfront(), 2));
				list2.add(0);
			} else if ("3yr".equals(quote.getLeaseContractLength())) {
				list2.add(0);
				list2.add(SomeMath.round(quote.getUpfront(), 2));
			} else {
				list2.add(0);
				list2.add(0);
			}
			list2.add(SomeMath.round(quote.getMonthly(), 2));
			list2.add(SomeMath.round(quote.getThreeYearTotal(), 2));
			list2.add(SomeMath.round(quote.getDiscount(), 4));
			data.add(list2);
		}
		simpleWriter.write(data);   
		
		for (Quote quote : Constants.quotes) {
			XceliteSheet sheet = xcelite.createSheet(quote.getName());
			SheetWriter<InstanceOutput> writer = sheet.getBeanWriter(InstanceOutput.class);
			writer.write(quote.getOutput());

			if (QuoteName.YOUR_INPUT.getName().equalsIgnoreCase(quote.getName())) {
				for (InstanceOutput output : quote.getOutput()) {
					if (output.getErrorMessage() != null) {
						LOGGER.warn("Error message for " + output.getDescription() + ":" + output.getErrorMessage());
					}
				}
			}
			LOGGER.info(quote.getName() + "-> Valor: " + quote.getThreeYearTotal() + "-> Desconto: " + quote.getDiscount());
		}
		xcelite.write(new File(outputFileName));
	}

}
