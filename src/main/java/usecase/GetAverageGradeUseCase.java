package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

import java.lang.reflect.Member;
import java.util.ArrayList;

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
        for (String member : team.getMembers()) {
            sum += gradeDataBase.getGrade(member, course).getGrade();
            count ++;
        }
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
