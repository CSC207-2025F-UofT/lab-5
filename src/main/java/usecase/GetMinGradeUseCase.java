package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetMinGradeUseCase class.
 */
public final class GetMinGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetMinGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the lowest grade for a course across your team.
     * @param course The course.
     * @return The top grade.
     */
    public float getMinGrade(String course) {
        // Call the API to get the usernames of all your team members
        float min = 0;
        final Team team = gradeDataBase.getMyTeam();
        // Call the API to get all the grades for the course for all your team members
        for (String username : team.getMembers()) {
            // Call the API to get the grade for the course for the username
            final Grade[] grades = gradeDataBase.getGrades(username);
            for (Grade grade : grades) {

                if (grade.getCourse().equals(course)) {
                    // Sum all the grades
                    if (grade.getGrade() < min) {
                        min = grade.getGrade();
                    }
                }
            }
        }
        return min;
    }
}

