package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;

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
        final Team team = gradeDataBase.getMyTeam();
        int count = team.getMembers().length;

        for (String person: team.getMembers()) {
            System.out.println(person);
            Grade grade = gradeDataBase.getGrade(person, course);
            sum += grade.getGrade();
        }

        if (count == 0) {
            return 0;
        }

        return sum / count;
    }
}
