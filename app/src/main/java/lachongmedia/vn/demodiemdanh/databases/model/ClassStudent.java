package lachongmedia.vn.demodiemdanh.databases.model;

import java.util.List;

/**
 * Created by tranh on 5/27/2017.
 */

public class ClassStudent {
    private String name;
    private List<Student> students;

    public ClassStudent(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }
}
