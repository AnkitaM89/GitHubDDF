package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//constructor for reading excel path
	public ExcelFileUtil(String excelPath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelPath);
		wb = new XSSFWorkbook(fi);
	}
	//Count no. of rows in a Sheet
	public int rowCount(String sheetName)
	{
		
		return wb.getSheet(sheetName).getLastRowNum();
	}
	//Count no. of cells in a row
	public int cellCount(String sheetName)
	{
		return wb.getSheet(sheetName).getRow(0).getLastCellNum();
	}
	//Get cell data from Sheet
	public String getCellData(String sheetName,int row,int col)
	{
		String data="";
		if(wb.getSheet(sheetName).getRow(row).getCell(col).getCellType()== Cell.CELL_TYPE_NUMERIC)
		{
			//type cast from double to int
			int cellData = (int)wb.getSheet(sheetName).getRow(row).getCell(col).getNumericCellValue();
		    //type cast from int to String
			data = String.valueOf(cellData);
		}
		else
		{
			
			data = wb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}
	//Set cell data
	public void setCellData(String sheetName,int row,int col,String status,String writeExcel)throws Throwable
	{
		//Create cell and set value
		wb.getSheet(sheetName).getRow(row).createCell(col).setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			XSSFCellStyle style =wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			wb.getSheet(sheetName).getRow(row).getCell(col).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("fail"))
		{
			XSSFCellStyle style =wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			wb.getSheet(sheetName).getRow(row).getCell(col).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("blocked"))
		{
			XSSFCellStyle style =wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			wb.getSheet(sheetName).getRow(row).getCell(col).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
	}
	
	
	

}
