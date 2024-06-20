package com.devstack.lms.business.custom.impl;

import com.devstack.lms.business.custom.CourseBo;
import com.devstack.lms.dao.DaoFactory;
import com.devstack.lms.dao.custom.CourseDao;
import com.devstack.lms.dao.custom.impl.CourseDaoImpl;
import com.devstack.lms.dto.CourseDto;
import com.devstack.lms.entity.Course;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CourseBoImpl implements CourseBo {

    private final CourseDao courseDao = (CourseDao) DaoFactory.getDao(DaoFactory.DaoType.COURSE);

    @Override
    public boolean create(CourseDto dto) throws SQLException, ClassNotFoundException {
        return courseDao.create(toCourse(dto));
//                (
//                new Course(dto.getCourseId(), dto.getCourseName(), dto.getCourseFee())
//        );
    }

    @Override
    public boolean update(CourseDto dto) throws ClassNotFoundException, SQLException {
            return courseDao.update(toCourse(dto));
//        (
//                new Course(dto.getCourseId(), dto.getCourseName(), dto.getCourseFee())
//        );
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return courseDao.delete(id);
    }

    @Override
    public CourseDto find(String id) throws ClassNotFoundException, SQLException {
        Course course = courseDao.find(id);
        if (course != null) {
//            return new CourseDto(
//                    course.getCourseId(), course.getCourseName(), course.getCourseFee()
//            );
            return new CourseDto(course);
        }
        return null;
    }

    @Override
    public List<CourseDto> findAll() throws SQLException, ClassNotFoundException {
//        List<Course> courseList = courseDao.findAll();
//        List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
//        for(Course c : courseList) {
//            courseDtoList.add(
//                    new CourseDto(
//                            c.getCourseId(), c.getCourseName(), c.getCourseFee()));
//
//        }
//        return courseDtoList;

        return courseDao.findAll().stream().map(this::toCourseDto).collect(Collectors.toList());
    }

    private  CourseDto toCourseDto(Course c) {
        return new CourseDto(
                c.getCourseId(), c.getCourseName(), c.getCourseFee()
        );
    }

    private  Course toCourse(CourseDto c) {
        return new Course(
                c.getCourseId(), c.getCourseName(), c.getCourseFee()
        );
    }
}
