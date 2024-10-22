package com.dms.datamodelmanagementserver.collectionBook.serviceImpl;

import com.dms.datamodelmanagementserver.collection.service.CollectionDownloadService;
import com.dms.datamodelmanagementserver.collectionBook.service.CollectionBookDownloadService;
import com.dms.datamodelmanagementserver.standardData.dto.StandardDataDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class CollectionBookDownloadServiceImpl implements CollectionBookDownloadService {
    @Override
    public String saveCollectionBookDataToExcel(List<StandardDataDTO> standardDataDTOList, HttpServletResponse response) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Sheet1");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"번호", "표준 분류", "구분", "논리명", "물리명",
                    "도메인 그룹", "도메인 유형", "도메인명", "논리데이터 타입", "길이", "표준 여부", "속성 분류어", "엔티티 분류어"};
            IntStream.range(0, headers.length)
                    .forEach(index -> headerRow.createCell(index).setCellValue(headers[index]));

            IntStream.range(0, standardDataDTOList.size())
                    .forEach(index -> {
                        Row dataRow = sheet.createRow(index + 1);
                        StandardDataDTO data = standardDataDTOList.get(index);

                        dataRow.createCell(0).setCellValue(++index);
                        dataRow.createCell(1).setCellValue(data.getStandardClassification());
                        dataRow.createCell(2).setCellValue(data.getDivision());
                        dataRow.createCell(3).setCellValue(data.getLogicalName());
                        dataRow.createCell(4).setCellValue(data.getPhysicalName());
                        dataRow.createCell(5).setCellValue(data.getDomainGroup());
                        dataRow.createCell(6).setCellValue(data.getDomainType());
                        dataRow.createCell(7).setCellValue(data.getDomainName());
                        dataRow.createCell(8).setCellValue(data.getLogicalDataType());
                        dataRow.createCell(9).setCellValue(data.getLength());
                        dataRow.createCell(10).setCellValue(data.getIsStandard());
                        dataRow.createCell(11).setCellValue(data.getAttributeClassifier());
                        dataRow.createCell(12).setCellValue(data.getEntityClassifier());
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
