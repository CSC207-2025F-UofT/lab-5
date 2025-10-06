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

        for (String member : members) {
            try {
                Grade[] memberGrades = gradeDataBase.getGrades(member);
                for (Grade grade : memberGrades) {
                    if (grade.getCourse().equals(course)) {
                        sum += grade.getGrade();
                        count++;
                        break;
                    }
                }
            } catch (Exception e) {

            }
        }

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
