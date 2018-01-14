import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class SkillsReqImporter {

    private final int HEADER_ROW_INDEX = 1;
    private final int MAX_ROWS = 100;
    private final String skillsFilePath = "../Plan.xlsx";
    private final String skillSheetName = "Job requirements";
    private int offersCount = 0;

    private Workbook workbook;
    private Sheet skillSheet;
    Row headerRow;
    Set<Skill> skills;

    public SkillsReqImporter(Set<Skill> skills){
        openFile(skillsFilePath);
        openSheet(skillSheetName);
        this.skills = skills;
        headerRow = skillSheet.getRow(HEADER_ROW_INDEX);
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


    public Set<Skill> importSkillsReq(){
        int rowStart = HEADER_ROW_INDEX + 1;
        int[] pageBreak = skillSheet.getRowBreaks();
        int rowEnd = Math.min(HEADER_ROW_INDEX + MAX_ROWS, pageBreak[0] + 1);

        for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
            Row row = skillSheet.getRow(rowNum);
            for (Cell cell : row) {
                if (countOffersAndCheckIfCellIsText(cell)) {
                    skills = tryToSaveSkillReqFromCell(cell, skills);
                }
            }

        }
        return skills;
    }

        private boolean countOffersAndCheckIfCellIsText(Cell cell){
            if (cell == null)
                return false;
            else if (cell.getCellTypeEnum() != CellType.STRING)
                return false;
            else if (cell.getStringCellValue().contains("http")) {
                offersCount ++;
                return false;
            }else
                return true;
        }

        private  Set<Skill> tryToSaveSkillReqFromCell(Cell cell, Set<Skill> skills){
            String text = cell.getStringCellValue();
            Cell rankCell = headerRow.getCell(cell.getColumnIndex());
            int rank = (int)(rankCell.getNumericCellValue());

            if (!saveSkillsReq(text, rank)){
                System.out.println("Skill don't exists: " + text);
                System.out.println("Cell: col: " + cell.getColumnIndex() + " row: " + cell.getRowIndex());
            }
            return skills;
        }

            private boolean saveSkillsReq(String text, int rank){
                for (Skill skill: skills) {
                    if (text.equalsIgnoreCase(skill.getName())) {
                            skill.addReqPointsByRank(rank, offersCount);
                            skill.addOccurence();
                        return true;
                    }
                }
                return false;
            }
}
