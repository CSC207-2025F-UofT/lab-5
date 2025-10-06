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
        float sum = 0;
        int count = 0;
        final Team team = gradeDataBase.getMyTeam();

        for (String member : team.getMembers()) {

            Grade grade = gradeDataBase.getGrade(member, course);

            if (grade != null) {
                sum += grade.getGrade();
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
