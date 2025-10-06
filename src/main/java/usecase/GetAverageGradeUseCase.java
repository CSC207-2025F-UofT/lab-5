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
     *
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;
        final Team team = gradeDataBase.getMyTeam();
        if (team == null || team.getMembers() == null || team.getMembers().length == 0) {
            return 0;
        }

        for (String members : team.getMembers()) {
            if (members == null) continue;
            Grade[] grades;
            grades = gradeDataBase.getGrades(members);
            if (grades == null) continue;
            for (Grade g : grades) {
                if (g != null && g.getCourse() != null
                        && g.getCourse().equalsIgnoreCase(course)) {
                    sum += g.getGrade(); // assuming getGrade() returns an int
                    count++;
                    // If there's at most one grade per (member, course), you could break here.
                    break;
                }

            }
        }
        return sum / count;
    }
}