package in.ac.charusat.studentmgmtsystem.controller;

import in.ac.charusat.studentmgmtsystem.model.Student;
import in.ac.charusat.studentmgmtsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
//    List<Student> students = new ArrayList<>(
//            Arrays.asList(
//                    new Student(1, "Tom", "US"),
//                    new Student(2, "Harry", "Canada"),
//                    new Student(3, "Nick", "UK")
//            )
//    );

    // Mappings - URL endpoints
    // Get the list of all student
    @GetMapping("/Students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @RequestMapping(value = "/Student", method = RequestMethod.POST)
    public String getStudent(@RequestBody Student student){
        boolean StudentExist = studentRepository.existsById(student.getId());
        if(!StudentExist){
             studentRepository.save(student);
            return "record insert";
        }
        else{
            return "exist record";
        }
    }

    @DeleteMapping("/Student/{id}")
    public String deleteStudent(@PathVariable  Integer id){
        boolean StudentExist = studentRepository.existsById(id);
        if(!StudentExist) {
            studentRepository.deleteById(id);
            return "Record Delete";
        }else {
            return "record not exist";
        }
    }

    @PutMapping("/Student/{id}")
    public String updateStudent(@RequestBody Student student, @PathVariable Integer id ){
        Student student1 = studentRepository.findById(id).get();
        student1.setId(student1.getId());
        student1.setName(student1.getName());
        student1.setAddress(student1.getAddress());
        studentRepository.save(student);
        return "record update";
    }

    @GetMapping("/Students/{id}")
    public Student getAllStudents(@PathVariable Integer id) {
        return studentRepository.findById(id).get();
    }

    // Get the student information
//    @GetMapping("/student/{id}")
//    public Student getStudent(@PathVariable Integer id) {
//        return studentRepository.findById(id).get();
//    }
//
//    // Delete the student
//    @DeleteMapping("/student/{id}")
//    public List<Student> deleteStudent(@PathVariable Integer id) {
//        studentRepository.delete(studentRepository.findById(id).get());
//        return studentRepository.findAll();
//    }
//
//    // Add new student
//    @PostMapping("/student")
//    public List<Student> addStudent(@RequestBody Student student) {
//        studentRepository.save(student);
//        return studentRepository.findAll();
//    }
//
//    // Update the student information
//    @PutMapping("/student/{id}")
//    public List<Student> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
//        Student studentObj = studentRepository.findById(id).get();
//        studentObj.setName(student.getName());
//        studentObj.setAddress(student.getAddress());
//        studentRepository.save(studentObj);
//        return studentRepository.findAll();
//    }
//
}
