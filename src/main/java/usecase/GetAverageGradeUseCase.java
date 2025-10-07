package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;

        final Team team = gradeDataBase.getMyTeam();
        String[] members = team.getMembers();
        for (String member : members) {
            Grade[] user_grades = gradeDataBase.getGrades(member);
            for (Grade user_grade : user_grades) {
                if (user_grade.getCourse().equals(course)) {
                    sum += user_grade.getGrade();
                    count++;
                }
            }
        }

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
