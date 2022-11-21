package redux

import data.Course
import react.Reducer
import tools.plus
import tools.replace

val markReducer: Reducer<State, Action> = { state, action ->
        when (action) {
            is MarkStudent -> State(
                Array(state.courses.size) {
                    val course = state.courses[it]
                    if (course.id == action.courseId)
                        course.updateCourse(action)
                    else
                        course
                },
                state.students,
            )

            is AddStudent -> State(
                state.courses,
                state.students + action.student, )
            is AddStudentToCourse -> {
                addStudentToCourse(state, action)
            }
            is AddGrade -> State(
                Array(state.courses.size) {
                    val course = state.courses[it]
                    if (course.id == action.courseId)
                        course.updateCourse(action)
                    else
                        course
                },
                state.students,
            )
            else -> state
        }
    }
private fun addStudentToCourse(state: State, action: AddStudentToCourse) =
    state.getStudentById(action.studentId)?.let { student ->
        state.getCourseById(action.courseId)?.let { course ->
            State(
                state.courses.replace(
                    course,
                    course.copy(
                        students = course.students + student,
                        marked = course.marked + false,
                        grades = course.grades + 1
                    ),
                ),
                state.students
            )
        }
    } ?: state
private fun Course.updateCourse(action: MarkStudent): Course {
    val markIndex = students.indexOfFirst { student ->
        student.id == action.studentId
    }
    return copy(
        marked = Array(marked.size) {
            val mark = marked[it]
            if (it == markIndex)
                !mark
            else
                mark
        }
    )
}
private fun Course.updateCourse(action: AddGrade): Course {
    val gradeIndex = students.indexOfFirst { student ->
        student.id == action.studentId
    }
    return copy(
        grades = Array(grades.size){
            val grade = grades[it]
            if (it == gradeIndex)
                action.grade
            else
                grade
        }
    )
}
