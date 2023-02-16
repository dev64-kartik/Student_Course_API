package com.example.studentcoursemaster.dao.coursedao;

import com.example.studentcoursemaster.dto.CourseDTO;
import com.example.studentcoursemaster.entities.Course;
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
public class CourseRepositoryImpl implements CourseRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    private List<AggregationOperation> AggregationTemplate() {

        List<AggregationOperation> agg = new ArrayList<>(Arrays.asList(
                Aggregation.lookup("Student_Courses", "_id", "courseId", "enrolledStudentId"),
                Aggregation.lookup("Students", "enrolledStudentId.studentId", "_id", "enrolledStudents"),
                new ProjectionOperation().andExclude("enrolledStudentId")
        ));

        return agg;

    }

    private boolean isCourseExists(Course course) {
        return mongoTemplate.exists(new Query(
                        Criteria.where("name").is(course.getName()).and("fees").is(course.getFees()).and("educator").is(course.getEducator())),
                Course.class);
    }

    @Override
    public List<CourseDTO> fetchAllCourses() {

        List<AggregationOperation> aggl = AggregationTemplate();
        Aggregation agg = Aggregation.newAggregation(aggl);
        AggregationResults<CourseDTO> aggrt = mongoTemplate.aggregate(agg,
                Course.class, CourseDTO.class);
        return aggrt.getMappedResults();
    }

    @Override
    public CourseDTO fetchCourse(String id) {

        List<AggregationOperation> aggl = new ArrayList<>();
        aggl.add(Aggregation.match(new Criteria("_id").is((new ObjectId(id)))));
        aggl.addAll(AggregationTemplate());
        Aggregation agg = Aggregation.newAggregation(aggl);
        AggregationResults<CourseDTO> aggrt = mongoTemplate.aggregate(agg,
                Course.class, CourseDTO.class);
        return aggrt.iterator().next();
    }

    @Override
    public Course storeCourse(Course course) {
        if (isCourseExists(course)) {
            return null;
        } else {
            return mongoTemplate.insert(course);
        }
    }

    @Override
    public Course updateCourse(Course newCourse) {
        Query query = new Query(Criteria.where("_id").is(newCourse.getCourseId()));
        return mongoTemplate.findAndReplace(query, newCourse, FindAndReplaceOptions.options().returnNew());
    }

    @Override
    public void deleteCourse(String id) {
        Query query = new Query(Criteria.where("courseId").is(id));
        mongoTemplate.remove(query, Course.class);
        mongoTemplate.remove(query, Student_Course.class);
    }

}
