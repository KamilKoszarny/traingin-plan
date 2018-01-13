import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SkillsImporter {

    private Workbook workbook;
    private Sheet skillSheet;
    private int headerRowIndex;
    Row headerRow;


    public SkillsImporter(String pathName, String skillSheetName, int headerRowIndex){
        openFile(pathName);
        openSheet(skillSheetName);
        this.headerRowIndex = headerRowIndex;
        headerRow = skillSheet.getRow(headerRowIndex);
    }

        private void openFile(String pathName){
            try {
                workbook = WorkbookFactory.create(new File(pathName));
            } catch (IOException | InvalidFormatException e1) {
                e1.printStackTrace();
            }
        }

        private void openSheet(String skillSheetName){
            skillSheet = workbook.getSheet(skillSheetName);
        }


    public List<Skill> importSkills(int skillsCount, int maxRows){
        List<Skill> skills = new ArrayList<>();
        int rowStart = headerRowIndex + 1;
        int[] pageBreak = skillSheet.getRowBreaks();
        int rowEnd = Math.min(headerRowIndex + maxRows, pageBreak[0] - 1);

        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++){
            Row row = skillSheet.getRow(rowNum);
            for (Cell cell: row){
                if (isSkillCell(cell, headerRowIndex)){
                    skills = saveSkillFromCell(cell, skills);
                }
            }
        }


        return skills;
    }

        private boolean isSkillCell(Cell cell, int headerRowIndex){
            if (cell == null)
                return false;
            else if (cell.getCellTypeEnum() != CellType.STRING)
                return false;
            else if (cell.getStringCellValue().contains("http"))
                return false;
            else
                return true;
        }

        private  List<Skill> saveSkillFromCell(Cell cell, List<Skill> skills){
            Skill skill = new Skill();
            skill.setName(cell.getStringCellValue());

            Cell rankCell = headerRow.getCell(cell.getColumnIndex());
            skill.setRank((int)(rankCell.getNumericCellValue()));

            skills.add(skill);

            return skills;
        }
}
