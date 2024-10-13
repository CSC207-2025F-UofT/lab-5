package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

import java.lang.reflect.Member;

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
        // TODO Task 3b: Go to the MongoGradeDataBase class and implement getMyTeam.
        final Team team = gradeDataBase.getMyTeam();
        String[] members = team.getMembers();
        for (String member : members) {
            gradeDataBase.getGrade(member, course).getGrade();
            sum += gradeDataBase.getGrade(member, course).getGrade();
            count++;
        }
        // TODO Task 3a: Complete the logic of calculating the average course grade for
        //              your team members. Hint: the getGrades method might be useful.

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
