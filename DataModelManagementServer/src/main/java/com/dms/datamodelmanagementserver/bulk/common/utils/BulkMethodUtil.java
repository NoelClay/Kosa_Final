
package com.dms.datamodelmanagementserver.bulk.common.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class BulkMethodUtil {
	
    public static boolean isError(Cell cell) {
    	if (cell.getCellType() == CellType.ERROR) return true;
    	return false;
    }
	
    public static boolean isNotNullOfCell(Cell cell) {
    	if (cell != null && !(cell.getCellType() == CellType.BLANK)) {
    		return true;
    	}
		return false;
    }
    
    public static String checkStringCell(Cell cell) {
    	CellType cellType = cell.getCellType(); 	
    	switch(cellType) {
    	case NUMERIC : return String.valueOf((int) cell.getNumericCellValue()).trim();
    	case FORMULA : return checkStringFormulaCell(cell);
    	default : return cell.getStringCellValue().trim();
    	}
    }
    
    public static int checkIntCell(Cell cell) {
    	CellType cellType = cell.getCellType();
    	switch(cellType) {
    	case STRING : return Integer.parseInt(cell.getStringCellValue().trim());
    	case FORMULA : return checkIntFormulaCell(cell);
    	default : return (int)cell.getNumericCellValue();
    	}
    }
    
    private static String checkStringFormulaCell(Cell cell) {
        switch (cell.getCachedFormulaResultType()) {
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue()).trim();
            case STRING:
                return cell.getStringCellValue().trim();
            default:
                return String.valueOf(cell.getCellFormula()).trim();
        } 
    }
    
    private static int checkIntFormulaCell(Cell cell) {
        switch (cell.getCachedFormulaResultType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case STRING:
                return Integer.parseInt(cell.getStringCellValue().trim());
            default:
                return Integer.parseInt(cell.getCellFormula());
        } 
    }
}
