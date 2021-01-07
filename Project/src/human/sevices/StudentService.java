package human.sevices;

import fileServices.FileService;
import human.Student;
import menu.Menu;

public class StudentService {
    public void printStudent(Student studentObj) {
        System.out.println("----------------------------");
        System.out.println("ID is: " + studentObj.getId());
        System.out.println("Name is: " + studentObj.getName());
        System.out.println("SurName is: " + studentObj.getSurName());
        System.out.println("BirthDate is: " + studentObj.getBirthDate());
        System.out.println("Gender is: " + studentObj.getGender());
        System.out.println("Mark is: " + studentObj.getMark());
        System.out.println("----------------------------");
        System.out.println();
    }

    public void printStudents(Student[] students) {
        for (Student element : students) {
            printStudent(element);
        }
    }

    public Student getStudentById(int id, Student[] students) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void printStudentInfoById(int id, Student[] students) {
        if (getStudentById(id, students) != null) {
            printStudent(getStudentById(id, students));
            return;
        }
        System.out.println("There is no student with that id");
    }

    public void printStudentsFullName(Student[] students) {
        System.out.println("Students full names: ");
        for (Student element : students) {
            System.out.println(element.getName() + " " + element.getSurName());
        }
    }

    public void printAllMaleStudents(Student[] students) {
        Student[] maleStudents = new Student[students.length];
        int index = 0;
        for (Student element : students) {
            if (element.getGender() == 'm') {
                maleStudents[index] = element;
                index++;
            }
        }
        printStudents(correctArray(maleStudents, index));
    }

    public void printAllFemaleStudentsWhoHasMarkGreaterThenFiftyPointFour(Student[] students) {
        for (Student element : students) {
            if (element.getGender() == 'f' && element.getMark() > 50.4) {
                printStudent(element);
            }
        }
    }

    public void printStudentInformationHavingTheMinimalMark(Student[] students) {
        if (students.length > 0) {
            Student minimal = students[0];
            for (Student element : students) {
                if (element.getMark() < minimal.getMark()) {
                    minimal = element;
                }
            }
            printStudent(minimal);
        }
    }

    public void printBiggestMaleStudentInformation(Student[] students) {
        if (students.length > 0) {
            Student biggest = students[0];
            for (Student element : students) {
                if (element.getGender() == 'm') {
                    if (element.getBirthDate() < biggest.getBirthDate()) {
                        biggest = element;
                    }
                }
            }
            printStudent(biggest);
        }
    }

    public void printStudentsSortedByMark(Student[] students) {
        Student[] sortedStudentsByMark = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            sortedStudentsByMark[i] = students[i];
        }
        margeSortByMark(sortedStudentsByMark);
        printStudents(sortedStudentsByMark);
    }


    public void margeSortByMark(Student[] students) {
        if (students.length < 2) {
            return;
        }
        Student[] left = new Student[students.length / 2];
        Student[] right = new Student[students.length - left.length];

        for (int i = 0; i < left.length; i++) {
            left[i] = students[i];
        }
        for (int i = left.length; i < students.length; i++) {
            right[i - left.length] = students[i];
        }
        margeSortByMark(left);
        margeSortByMark(right);

        margeByMark(left, right, students);
    }

    public Student[] margeByMark(Student[] left, Student[] right, Student[] result) {
        int leftIndex = 0, rightIndex = 0, resultIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex].getMark() >= right[rightIndex].getMark()) {
                result[resultIndex++] = right[rightIndex++];
            } else {
                result[resultIndex++] = left[leftIndex++];
            }
        }

        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }

        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }

        return result;
    }

    public void printFemaleStudentsSortedByYear(Student[] students) {
        Student[] sortedFemaleStudentsByYear = new Student[students.length];
        int index = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getGender() == 'f') {
                sortedFemaleStudentsByYear[index++] = students[i];
            }
        }
        sortedFemaleStudentsByYear = correctArray(sortedFemaleStudentsByYear, index);
        margeSortByYear(sortedFemaleStudentsByYear);
        printStudents(sortedFemaleStudentsByYear);
    }

    public void margeSortByYear(Student[] students) {
        if (students.length < 2) {
            return;
        }
        Student[] left = new Student[students.length / 2];
        Student[] right = new Student[students.length - left.length];

        for (int i = 0; i < left.length; i++) {
            left[i] = students[i];
        }
        for (int i = left.length; i < students.length; i++) {
            right[i - left.length] = students[i];
        }
        margeSortByYear(left);
        margeSortByYear(right);

        margeByYear(left, right, students);
    }

    public Student[] margeByYear(Student[] left, Student[] right, Student[] result) {
        int leftIndex = 0, rightIndex = 0, resultIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex].getBirthDate() >= right[rightIndex].getBirthDate()) {
                result[resultIndex++] = right[rightIndex++];
            } else {
                result[resultIndex++] = left[leftIndex++];
            }
        }

        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }

        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }

        return result;
    }

    public Student[] correctArray(Student[] students, int size) {
        Student[] result = new Student[size];
        for (int i = 0; i < size; i++) {
            result[i] = students[i];
        }
        return result;
    }

    public boolean addStudent() {
        boolean isAdded = false;
        Student student = new Student();
        try {
            System.out.println("Please insert student ID. ID should be unique");
            student.setId(Menu.inputInConsoleForInt());
            if (getStudentById(student.getId(), FileService.getAllStudents()) == null) {
                System.out.println("Please insert student name");
                student.setName(Menu.inputInConsoleForString());
                System.out.println("Please insert student surname");
                student.setSurName(Menu.inputInConsoleForString());
                System.out.println("Please insert student birth year");
                student.setBirthDate(Menu.inputInConsoleForInt());
                System.out.println("Please insert student gender (f/m/female/male)");
                student.setGender(Menu.inputInConsoleForString());
                System.out.println("Please insert student mark");
                student.setMark(Menu.inputInConsoleForDouble());
                isAdded = FileService.writeStudent(student);
                FileService.addStudent(student);
            } else {
                System.out.println("There is already exist student in that id");
            }
        } catch (Exception e) {
            System.out.print("\033[0;91m" + "You insert wrong data.");
            System.out.println(" " + e.getMessage());
        }
        return isAdded;
    }

    public boolean removeStudentById(int id) {
        Student[] allStudents = FileService.getAllStudents();
        boolean isRemoved = false;
        for (int i = 0; i < allStudents.length; i++) {
            if (allStudents[i].getId() == id) {
                allStudents[i] = null;
                isRemoved = true;
                break;
            }
        }
        if (isRemoved) {
            FileService.setAllStudents(getArrayWithoutNullElements(allStudents));
            FileService.rewriteStudents(FileService.getAllStudents());
        }
        return isRemoved;
    }

    public Student[] getArrayWithoutNullElements(Student[] students) {
        Student[] array = new Student[students.length];
        int index = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                array[index++] = students[i];
            }
        }

        students = array;
        return correctArray(students, index);
    }
}
