package com.dms.datamodelmanagementserver.bulk.term.serviceImpl;

import com.dms.datamodelmanagementserver.bulk.term.dto.BulkTermExcelDataDTO;
import com.dms.datamodelmanagementserver.bulk.term.service.BulkTermDownloadService;
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
public class BulkTermDownloadServiceImpl implements BulkTermDownloadService {
    @Override
    public String saveTermDataToExcel(List<BulkTermExcelDataDTO> bulkTermExcelDataDTOList, HttpServletResponse response) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Sheet1");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"번호", "용어명", "용어설명", "도메인명", "도메인유형", "도메인그룹",
                    "논리 데이터타입", "길이", "소수점", "논리용어명", "물리용어명", "도메인", "상태"};
            IntStream.range(0, headers.length)
                    .forEach(index -> headerRow.createCell(index).setCellValue(headers[index]));

            IntStream.range(0, bulkTermExcelDataDTOList.size())
                    .forEach(index -> {
                        Row dataRow = sheet.createRow(index + 1);
                        BulkTermExcelDataDTO data = bulkTermExcelDataDTOList.get(index);

                        dataRow.createCell(0).setCellValue(++index);
                        
                        if (StringUtils.hasText(data.getTempDicLogicalName())) {
                        	dataRow.createCell(1).setCellValue(data.getTempDicLogicalName());
                        }
                        
                        if (StringUtils.hasText(data.getDicDescription())) {
                        	dataRow.createCell(2).setCellValue(data.getDicDescription());
                        }
                        
                        if (StringUtils.hasText(data.getKeyDomainName())) {
                        	dataRow.createCell(3).setCellValue(data.getKeyDomainName());
                        }
                        
                        if (StringUtils.hasText(data.getDomainTypeCode())) {
                        	dataRow.createCell(4).setCellValue(data.getDomainTypeCode());
                        }
                        
                        if (StringUtils.hasText(data.getDomainGroupName())) {
                        	dataRow.createCell(5).setCellValue(data.getDomainGroupName());
                        }
                        
                        if (StringUtils.hasText(data.getDataTypeCode())) {
                        	dataRow.createCell(6).setCellValue(data.getDataTypeCode());
                        }
                        
                        if (data.getDataLength() != null) {
                        	dataRow.createCell(7).setCellValue(data.getDataLength());
                        }
                        
                        if (data.getDataScale() != null) {
                        	dataRow.createCell(8).setCellValue(data.getDataScale());
                        }
        
                        if (StringUtils.hasText(data.getDicLogicalName())) {
                        	dataRow.createCell(9).setCellValue(data.getDicLogicalName());
                        }
                        
                        if (StringUtils.hasText(data.getDicPhysicalName())) {
                        	dataRow.createCell(10).setCellValue(data.getDicPhysicalName());
                        }
                        
                        if (StringUtils.hasText(data.getDomainName())) {
                        	dataRow.createCell(11).setCellValue(data.getDomainName());
                        }
                        
                        if (data.getReason().size() != 0) {
                        	dataRow.createCell(12).setCellValue(String.join("/", data.getReason()));
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
