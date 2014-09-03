package app.solutions.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niveditha on 5/1/14.
 */
public class ClassType extends ObjectType {


    // Number of students allowed in this class type
    private Integer maxNoOfStudentsAllowed;

    // new student is not allowed after total number reaches maxNoOfStudentsAllowed..if true error will be thrown
    private boolean notAllowedAfterMaxStudents;

    // Number of subjects
    private Integer noOfSubjectCourses;

    // Number of languages
    private Integer noOfLanguageCourses;

    // Courses configured for this class type.
    private List<String> courses = new ArrayList<String>();

    private List<String> sections = new ArrayList<String>();


    public Integer getNoOfLanguageCourses() {
        return noOfLanguageCourses;
    }

    public void setNoOfLanguageCourses(Integer noOfLanguageCourses) {
        this.noOfLanguageCourses = noOfLanguageCourses;
    }

    public Integer getNoOfSubjectCourses() {
        return noOfSubjectCourses;
    }

    public void setNoOfSubjectCourses(Integer noOfSubjectCourses) {
        this.noOfSubjectCourses = noOfSubjectCourses;
    }

    public boolean isNotAllowedAfterMaxStudents() {
        return notAllowedAfterMaxStudents;
    }

    public void setNotAllowedAfterMaxStudents(boolean notAllowedAfterMaxStudents) {
        this.notAllowedAfterMaxStudents = notAllowedAfterMaxStudents;
    }

    public Integer getMaxNoOfStudentsAllowed() {
        return maxNoOfStudentsAllowed;
    }

    public void setMaxNoOfStudentsAllowed(Integer maxNoOfStudentsAllowed) {
        this.maxNoOfStudentsAllowed = maxNoOfStudentsAllowed;
    }


    public List<String> getSections() {
        return sections;
    }

    public void setSections(List<String> sections) {
        this.sections = sections;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String getText() {
        return getTypeDescription();
    }
}