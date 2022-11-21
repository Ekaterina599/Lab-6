package redux

import data.*

class State(
    val courses: Array<Course>,
    val students: Array<Student>,
) {
    fun getStudentById(studentId: StudentId) =
        students.firstOrNull { it.id == studentId }
    fun getCourseById(courseId: CourseId) =
        courses.firstOrNull { it.id == courseId }
    fun getCourseList(student: Student): List<Pair<Course, Grade>> =
        courses
            .filter { it.students.contains(student) }
            .map{ it to it.grades[it.students.indexOf(student)] }

}

fun testState() =
    State(
        courseList.map { it }.toTypedArray(),
        studentList.map { it }.toTypedArray(),
    )