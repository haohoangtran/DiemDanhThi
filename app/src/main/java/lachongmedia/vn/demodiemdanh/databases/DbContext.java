package lachongmedia.vn.demodiemdanh.databases;

import java.util.List;
import java.util.Vector;

import lachongmedia.vn.demodiemdanh.databases.model.Student;
import lachongmedia.vn.demodiemdanh.databases.model.Teacher;

/**
 * Created by tranh on 5/10/2017.
 */

public class DbContext {
    private List<Student> students;
    private Student studentNow;
    private List<Student> studentListCheck;
    private List<Teacher> teachers;

    public Student getStudentNow() {
        return studentNow;
    }

    public void setStudentNow(Student studentNow) {
        this.studentNow = studentNow;
    }

    private DbContext() {
        students = new Vector<>();
        studentListCheck = new Vector<>();
        teachers = new Vector<>();
        addDumdata();
    }

    public void addStudent(Student student) {
        if (!isInClass(student)) {
            studentListCheck.add(student);
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean isInClass(Student student) {
        for (int i = 0; i < studentListCheck.size(); i++) {
            if (studentListCheck.get(i).equals(student)) {
                return true;

            }
        }
        return false;
    }

    public List<Student> getStudentListCheck() {
        return studentListCheck;
    }

    public static DbContext instance = new DbContext();

    private void addDumdata() {
        students.add(
                new Student("846AC9470C4002E0", "Hoàng Trần Hảo", "http://static.thanhnien.com.vn/Uploaded/ngocthanh/2016_03_23/9x01_YGEO.jpg?width=1600&encoder=wic&subsampling=444", "Hoàng Trần Ba", "0123456789", "4A", "Lò Thị Hoa", "https://randomuser.me/api/portraits/lego/6.jpg")
        );
        students.add(
                new Student("2570C9470C4002E0", "Trần Hán Hiếu", "http://webdata.vcmedia.vn/webdata/100/ef6feb191212man36a68.jpg", "Trần Hán Hùng", "096523243", "3E", "Hoàng Văn Vũ", "https://randomuser.me/api/portraits/lego/7.jpg")
        );
        students.add(
                new Student("4D70C9470C4002E0", "Hoàng Văn Đại", "http://static.thanhnien.com.vn/Uploaded/ngocthanh/2016_03_23/9x01_YGEO.jpg?width=1600&encoder=wic&subsampling=444", "Hoàng Văn Đương", "090512521", "5C", "Nguyễn Đức Toàn", "https://randomuser.me/api/portraits/lego/8.jpg")
        );
        students.add(
                new Student("2D72C9470C4002E0", "Trịnh Văn Chiến", "https://dantricdn.com/k:b78705f365/2016/02/29/img-5248-1456718536903/chan-dung-hoa-si-duy-nhat-cua-viet-nam-duoc-apple-chon-dong-hanh.JPG", "Trịnh Chí Hiếu", "0164123123", "1A", "Hoàng Minh Vương", "https://randomuser.me/api/portraits/lego/9.jpg")
        );
        students.add(
                new Student("6B74C9470C4002E0", "Hoàng Vũ ", "http://media.tinmoi.vn/2016/05/12/anh-chan-dung-kim-jong-un-1.jpg", "Hoàng Vũ Giáng", "096523422", "6A", "Nguyễn Thị Lan", "https://randomuser.me/api/portraits/lego/10.jpg")
        );

        students.add(
                new Student("6B74Ca9470C4002E0", "Hoàng Chiến", "http://kenh14cdn.com/k:thumb_w/600/M2JlccccccccccccEmJzVu2ZOVx8FL/Image/2015/01/6-07855/chum-anh-chan-dung-toi-pham-hai-huoc-nhat-nam-2014.jpg", "Hoàng Vũ Giáng", "096523422", "6A", "Nguyễn Thị Lan", "https://randomuser.me/api/portraits/lego/10.jpg")
        );
        students.add(
                new Student("6B74C9470eCEQ2124", "Hoàng Cầu", "http://resources.cungmua.com/SKU/FullScreen/2635925256900754551.jpg", "Hoàng Vũ Giáng", "096523422", "6A", "Nguyễn Thị Lan", "https://randomuser.me/api/portraits/lego/10.jpg")
        );
        students.add(
                new Student("21421412412", "Hoàng Đậu", "http://petrotimes.vn/stores/news_dataimages/duyhung/112012/16/10/Xi_Jingping___Tp_Cn_Bnh___Chinas_vice_president__Reuters.jpg", "Hoàng Vũ Giáng", "096523422", "6A", "Nguyễn Thị Lan", "https://randomuser.me/api/portraits/lego/10.jpg")
        );
        students.add(
                new Student("6B74C9470C442AS002E0", "Hoàng Mai", "http://images.tintaynguyen.com/2016/06/1466049893-3886-ve.jpg", "Hoàng Vũ Giáng", "096523422", "6A", "Nguyễn Thị Lan", "https://randomuser.me/api/portraits/lego/10.jpg")
        );
        students.add(
                new Student("6B74C9470C4a2AS002E0", "Hoàng Mai", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREErQSohbAwVWCo3mWXd36YquyIjHZL_XkedSEcakEaeN_IEigIQ", "Hoàng Vũ Giáng", "096523422", "6A", "Nguyễn Thị Lan", "https://randomuser.me/api/portraits/lego/10.jpg")
        );
        students.add(
                new Student("6B74C9470C4a2AS002E0", "Hoàng Cừa", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS47IaU0c3Jb2_hYYXaSGJwSjaVcYV3StEcHUjheRb78HDcQy-qdQ", "Hoàng Vũ Giáng", "096523422", "6A", "Nguyễn Thị Lan", "https://randomuser.me/api/portraits/lego/10.jpg")
        );
        teachers.add(new Teacher("F5477242", "Hoàng Văn Quốc", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0yZgZQrc7KqBZiK51gG0ByUrdekiWyATpK1Kknj88p4lcLEWH"));
        teachers.add(new Teacher("F55D7642", "Nguyễn Thị Dịu", "http://petrotimes.vn/stores/news_dataimages/dinhhuongmaipt/072013/18/16/tumblr_mcg1whz9ws1qgetlto1_500.jpg"));

    }

    public Student findStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equalsIgnoreCase(id)) {
                return students.get(i);
            }
        }
        return null;
    }

    public Teacher findTeacher(String id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equalsIgnoreCase(id)) {
                return teachers.get(i);
            }
        }
        return null;
    }
    public int countStudentnow(){
        int count=0;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).isHereNow()) {
                count++;
            }
        }
        return count;
    }
    public List<Student> getStudentCheck(){
        List<Student> studentList=new Vector<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).isHereNow()) {
                studentList.add(students.get(i));
            }
        }
        return studentList;
    }
    public List<Student> getStudentUnCheck(){
        List<Student> studentList=new Vector<>();
        for (int i = 0; i < students.size(); i++) {
            if (!students.get(i).isHereNow()) {
                studentList.add(students.get(i));
            }
        }
        return studentList;
    }
    public void onNewTask(){
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).isHereNow()) {
                students.get(i).setHereNow(false);
            }
        }
    }
}
