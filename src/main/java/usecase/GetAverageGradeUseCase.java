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
        float sum = 0f;
        int count = 0;

        final Team team = gradeDataBase.getMyTeam();
        if (team == null || team.getMembers() == null || team.getMembers().length == 0) {
            throw new IllegalStateException();
        }

        for (String username : team.getMembers()) {
            if (username == null || username.isBlank()) continue;
            Grade[] grades;
            try {
                grades = gradeDataBase.getGrades(username);
            } catch (RuntimeException ex) {
                continue;
            }
            if (grades == null) continue;

            for (Grade g : grades) {
                if (g == null) continue;
                if (course.equals(g.getCourse())) {
                    sum += g.getGrade();
                    count++;
                    break;
                }
            }
        }

        if (count == 0) {
            throw new IllegalStateException();
        }
        return sum / count;
    }

}
