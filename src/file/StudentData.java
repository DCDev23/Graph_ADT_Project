package file;
/**
 * Class which implements all accessor and mutator methods for student data 
 * 
 * @author DA Chan
 * @version miniproject
 * 
 */
public class StudentData {
    private int index;
    private String academicResult;
    private int homeLanguage;
    private String highSchoolFees;
    private String firstGeneration;
    private int livingSpace;
    private int familyStructure;

    /**
     * Default constructor with no parameters 
     */
    public StudentData() {
         
    }

    /**
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index - the student index number
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return academicResult 
     */
    public String getAcademicResult() {
        return academicResult;
    }

    /**
     * @param academicResult - the academic result category of a student
     */
    public void setAcademicResult(String academicResult) {
        this.academicResult = academicResult;
    }

    /**
     * @return homeLanguage
     */
    public int getHomeLanguage() {
        return homeLanguage;
    }

    /**
     * @param homeLanguage - the home language of a student
     */
    public void setHomeLanguage(int homeLanguage) {
        this.homeLanguage = homeLanguage;
    }

    /**
     * @return highSchoolFees
     */
    public String getHighSchoolFees() {
        return highSchoolFees;
    }

    /**
     * @param highSchoolFees - the category of high school fees for a student
     */
    public void setHighSchoolFees(String highSchoolFees) {
        this.highSchoolFees = highSchoolFees;
    }

    /**
     * @return firstGeneration
     */
    public String getFirstGeneration() {
        return firstGeneration;
    }

    /**
     * @param firstGeneration - the status of a student being first generation
     */
    public void setFirstGeneration(String firstGeneration) {
        this.firstGeneration = firstGeneration;
    }

    /**
     * @return livingSpace
     */
    public int getLivingSpace() {
        return livingSpace;
    }

    /**
     * @param livingSpace - the impact of living space on student performance 
     */
    public void setLivingSpace(int livingSpace) {
        this.livingSpace = livingSpace;
    }

    /**
     * @return familyStructure
     */
    public int getFamilyStructure() {
		return familyStructure;
	}
 
    /**
     * @param familyStructure - the family structure of a student
     */
    public void setFamilyStructure(int familyStructure) {
		this.familyStructure = familyStructure;
	}
}

