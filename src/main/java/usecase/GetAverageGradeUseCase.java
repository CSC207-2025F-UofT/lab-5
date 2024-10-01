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
        // Call the API to get all the grades for the course for all your team members
        String[] members = team.getMembers();

        // For each team member
        for (String member : members) {
            // Get the grades for the team member
            Grade[] userGrades = gradeDataBase.getGrades(member);
            // Iter over every grade
            for (Grade userGrade : userGrades) {
                // If the grade correspond to the course, we add into the sum and increase the counter
                if (userGrade.getCourse().equals(course)) {
                    sum += userGrade.getGrade();
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
