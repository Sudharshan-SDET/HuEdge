package org.Base;

import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {
	public static HashMap<String, Integer> map = new HashMap<String, Integer>();
	public  static Sheet sheet;

	public  void readExcel() {
		try {
			String path = System.getProperty("user.dir");
			String filePath = path + "\\test-data\\TestData.xlsx";
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook=new XSSFWorkbook(fis);
			//XSSFWorkbook workbook = new XSSFWorkbook(fis);
			 Sheet sheet2 = workbook.getSheet("Sheet2");
			int rows = sheet.getLastRowNum();
			for (int i = 0; i <= rows; i++) {
				i++;
				Row row = sheet.getRow(i);
				 Cell cell = row.getCell(0);
				String tesCase = cell.getStringCellValue();
				map.put(tesCase, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// To get data value of expected key for particular Test case
	public  String retrieve(String testName, String expLabel) {
		int testRow = map.get(testName);
		Row row = sheet.getRow(testRow);
		for (int i = 1; i < row.getLastCellNum(); i++) {
			Row prevrow = sheet.getRow(testRow - 1);
			Cell precell = prevrow.getCell(i);
			// XSSFCell cell = row.getCell(i);
			if (precell != null) {
				String actLabel = precell.getStringCellValue();
				if (expLabel.equals(actLabel)) {
					Row valueRow = sheet.getRow(testRow);
				Cell valueCell = valueRow.getCell(i);
					if (valueCell != null) {
						 org.apache.poi.ss.usermodel.CellType cellType = valueCell.getCellType();
						if (cellType == cellType.NUMERIC) {
							return String.valueOf(valueCell.getNumericCellValue());
						} else {
							return valueCell.getStringCellValue();
						}
					}
				}
			}
		}
		return null;
	}
}
