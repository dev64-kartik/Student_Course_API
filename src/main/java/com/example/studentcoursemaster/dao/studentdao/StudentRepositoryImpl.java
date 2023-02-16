package com.example.studentcoursemaster.dao.studentdao;

import com.example.studentcoursemaster.dto.StudentDTO;
import com.example.studentcoursemaster.entities.Student;
import com.example.studentcoursemaster.entities.Student_Course;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private List<AggregationOperation> AggregationTemplate() {

        List<AggregationOperation> agg = new ArrayList<>(Arrays.asList(
                Aggregation.lookup("Student_Courses", "_id", "studentId", "enrolledCourseId"),
                Aggregation.lookup("Courses", "enrolledCourseId.courseId", "_id", "enrolledCourses"),
                new ProjectionOperation().andExclude("enrolledCourseId")));

        return agg;

    }

    private boolean isStudentExists(Student student) {
        return mongoTemplate.exists(new Query(
                        Criteria.where("name").is(student.getName()).and("college").is(student.getCollege())),
                Student.class);
    }

    @Override
    public List<StudentDTO> fetchAllStudents() {

        List<AggregationOperation> aggl = AggregationTemplate();
        Aggregation agg = Aggregation.newAggregation(aggl);
        AggregationResults<StudentDTO> aggrt = mongoTemplate.aggregate(agg,
                Student.class, StudentDTO.class);
        return aggrt.getMappedResults();
    }

    @Override
    public StudentDTO fetchStudent(String id) {
        List<AggregationOperation> aggl = new ArrayList<>();
        aggl.add(Aggregation.match(new Criteria("_id").is((new ObjectId(id)))));
        aggl.addAll(AggregationTemplate());
        Aggregation agg = Aggregation.newAggregation(aggl);
        AggregationResults<StudentDTO> aggrt = mongoTemplate.aggregate(agg,
                Student.class, StudentDTO.class);
        return aggrt.iterator().next();

    }

    @Override
    public Student storeStudent(Student student) {
        if (isStudentExists(student)) {
            return null;
        } else {
            return mongoTemplate.insert(student);
        }
    }

    @Override
    public Student updateStudent(Student newStudent) {
        Query query = new Query(Criteria.where("studentId").is(newStudent.getStudentId()));
        return mongoTemplate.findAndReplace(query, newStudent, FindAndReplaceOptions.options().returnNew());

    }

    @Override
    public void deleteStudent(String id) {
        Query query = new Query(Criteria.where("studentId").is(id));
        mongoTemplate.remove(query, Student.class);
        mongoTemplate.remove(query, Student_Course.class);
    }

    @Override
    public boolean EnrollStudentInCourse(String studentId, String courseId) {
        Student_Course studentCourse = new Student_Course(new ObjectId(studentId), new ObjectId(courseId));
        if (mongoTemplate.exists(new Query(Criteria.where("studentId").is(new ObjectId(studentId)).and("courseId").is(new ObjectId(courseId))), Student_Course.class))
            return false;
        mongoTemplate.insert(studentCourse);
        return true;
    }

    @Override
    public void UnenrollStudentFromCourse(String studentId, String courseId) {
        Query query = new Query(Criteria.where("studentId").is(new ObjectId(studentId))
                .and("courseId").is(new ObjectId(courseId)));
        mongoTemplate.remove(query, Student_Course.class);
    }
}
