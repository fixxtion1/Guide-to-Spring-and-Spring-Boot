package io.datajek.unittesting2;

public class StudentService {
    private StudentDao studentDao;

    /**
     * Constructor
     * @param studentDao
     */
    public StudentService(StudentDao studentDao) {
        super();
        this.studentDao = studentDao;
    }

    /**
     * Method to find the total of an array
     * @return sum of an array
     */
    int findTotal() {
        //fetch student marks from a database
        int[] array = studentDao.getMarks();
        int sum = 0;

        for (int value : array) {
            sum += value;
        }

        return sum;
    }
}
