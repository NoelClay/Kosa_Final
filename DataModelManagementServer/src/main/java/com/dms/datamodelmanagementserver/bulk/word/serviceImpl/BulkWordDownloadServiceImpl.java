package com.dms.datamodelmanagementserver.bulk.word.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.word.dto.WordExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.word.service.BulkWordDownloadService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class BulkWordDownloadServiceImpl implements BulkWordDownloadService {
    @Override
    public String saveWordDataToExcel(List<WordExcelDataDTO> wordExcelDataDTOList, HttpServletResponse response) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Sheet1");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"번호", "논리명", "물리명", "영문풀네임", "엔티티 분류어",
                    "속성 분류어", "동의어", "설명", "사유"};
            IntStream.range(0, headers.length)
                    .forEach(index -> headerRow.createCell(index).setCellValue(headers[index]));

            IntStream.range(0, wordExcelDataDTOList.size())
                    .forEach(index -> {
                    	Row dataRow = sheet.createRow(index + 1);
                        WordExcelDataDTO data = wordExcelDataDTOList.get(index);

                        dataRow.createCell(0).setCellValue(++index);
                        
                        if (StringUtils.hasText(data.getDicLogicalName())) {
                        	dataRow.createCell(1).setCellValue(data.getDicLogicalName());
                        }
                        
                        if (StringUtils.hasText(data.getDicPhysicalName())) {
                        	dataRow.createCell(2).setCellValue(data.getDicPhysicalName());
                        }
                        
                        if (StringUtils.hasText(data.getDicPhysicalFullName())) {
                        	dataRow.createCell(3).setCellValue(data.getDicPhysicalFullName());
                        }
                        
                        if (StringUtils.hasText(data.getEntitySuffix())) {
                        	dataRow.createCell(4).setCellValue(data.getEntitySuffix());
                        }
                        
                        if (StringUtils.hasText(data.getAttributeSuffix())) {
                        	dataRow.createCell(5).setCellValue(data.getAttributeSuffix());
                        }
                        
                        if (data.getSynonym().size() != 0) {
                        	dataRow.createCell(6).setCellValue(String.join(",", data.getSynonym()));
                        }
                        
                        if (StringUtils.hasText(data.getDicDescription())) {
                        	dataRow.createCell(7).setCellValue(data.getDicDescription());
                        }
                        
                        if (data.getReason().size() != 0) {
                        	dataRow.createCell(8).setCellValue(String.join("/", data.getReason()));
                        }
                        
                    });

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
