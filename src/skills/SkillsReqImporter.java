package skills;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class SkillsReqImporter {

    private final int HEADER_ROW_INDEX = 0;
    private final int MAX_ROWS = 100;
    private final String skillsFilePath = "../Plan.xlsx";
    private final String skillSheetName = "Job requirements";
    private int linkColumnIndex;
    private int levelColumnIndex;
    private int companyColumnIndex;
    private int placeColumnIndex;
    private int commentsColumnIndex;
    private int allOffersCount = 0;
    private int levelOffersCount = 0;

    private Workbook workbook;
    private Sheet skillSheet;
    private Row headerRow;
    private SkillsSet skills;
    private ReqLvl reqLvl;


    public SkillsReqImporter(SkillsSet skills){
        openFile(skillsFilePath);
        openSheet(skillSheetName);
        this.skills = skills;
        headerRow = skillSheet.getRow(HEADER_ROW_INDEX);
        findColumns();

        allOffersCount = Math.min(HEADER_ROW_INDEX + MAX_ROWS, skillSheet.getLastRowNum());
        System.out.println("All offers: " + allOffersCount);
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

    private void findColumns(){
        for (Cell cell: headerRow) {
            if (cellIsString(cell)){
                if (cell.getStringCellValue().equalsIgnoreCase("link"))
                    linkColumnIndex = cell.getColumnIndex();
                if (cell.getStringCellValue().equalsIgnoreCase("level"))
                    levelColumnIndex = cell.getColumnIndex();
                if (cell.getStringCellValue().equalsIgnoreCase("place"))
                    placeColumnIndex = cell.getColumnIndex();
                if (cell.getStringCellValue().equalsIgnoreCase("company"))
                    companyColumnIndex = cell.getColumnIndex();
                if (cell.getStringCellValue().equalsIgnoreCase("comments"))
                    commentsColumnIndex = cell.getColumnIndex();
            }
        }
    }

    public SkillsSet importSkillsReq(ReqLvl[] reqLvls){
        for (ReqLvl reqLvl: reqLvls) {
            skills = importSkillsReq(reqLvl);
        }
        return skills;
    }


    public SkillsSet importSkillsReq(ReqLvl reqLvl){
        this.reqLvl = reqLvl;
        levelOffersCount = 0;
        int rowStart = HEADER_ROW_INDEX + 1;

        for (int rowNum = rowStart; rowNum < allOffersCount + 1; rowNum++) {
            Row row = skillSheet.getRow(rowNum);
            if (row.getCell(levelColumnIndex).getStringCellValue().equalsIgnoreCase(reqLvl.toString())) {
                levelOffersCount++;
            }
        }

        for (int rowNum = rowStart; rowNum < allOffersCount + 1; rowNum++) {
            Row row = skillSheet.getRow(rowNum);
            if (row.getCell(levelColumnIndex).getStringCellValue().equalsIgnoreCase(reqLvl.toString())) {
                for (Cell cell : row) {
                    if (cellIsString(cell)) {
                        handleStringCell(cell);
                    }
                }
            }
        }
        System.out.print("Level: " + reqLvl.toString());
        System.out.println("\tOffers: " + levelOffersCount);

        addSuperSkillReqPoints(reqLvl);
        addSubSkillReqPoints(reqLvl);
        return skills;
    }

    private boolean cellIsString(Cell cell){
            if (cell == null)
                return false;
            else if (cell.getCellTypeEnum() != CellType.STRING)
                return false;
            else
                return true;
        }

    private void handleStringCell(Cell cell){
        if (cell.getColumnIndex() > commentsColumnIndex)
            skills = tryToSaveSkillReqFromCell(cell, skills);
    }

    private SkillsSet tryToSaveSkillReqFromCell(Cell cell, SkillsSet skills){
        String text = cell.getStringCellValue();
        Cell pointsCell = cell.getRow().getCell(cell.getColumnIndex() + 1);
        int points = (int)(pointsCell.getNumericCellValue());

        if (skillExists(text)){
            saveSkillReq(text, points);
        } else{
            System.out.println("Skill don't exists: " + text);
            System.out.println("Cell: col: " + cell.getColumnIndex() + " row: " + cell.getRowIndex());
        }
        return skills;
    }

    private boolean skillExists(String text){
        for (Skill skill: skills) {
            if (text.equalsIgnoreCase(skill.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean saveSkillReq(String text, int points){
        for (Skill skill: skills) {
            if (text.equalsIgnoreCase(skill.getName())) {
                //skill.addReqPoints(points, levelOffersCount);
                skill.addReqPoints(points, levelOffersCount, reqLvl);
                skill.addOccurrence();
                return true;
            }
        }
        return false;
    }

    private void addSubSkillReqPoints(ReqLvl reqLvl){
        for (int layer = 1; layer < Skill.LAYERS; layer++) {
            for (Skill skill : skills) {
                if (skill.getLayer() == layer) {
                    skill.addReqPointsBySuperSkill(reqLvl);
                }
            }
        }
    }

    private void addSuperSkillReqPoints(ReqLvl reqLvl){
        for (int layer = Skill.LAYERS; layer > -1; layer--) {
            for (Skill skill : skills) {
                if (skill.getLayer() == layer) {
                    skill.addReqPointsBySubSkills(reqLvl);
                }
            }
        }
    }
}
