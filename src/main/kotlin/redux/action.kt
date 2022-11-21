package redux

import data.CourseId
import data.Grade
import data.Student
import data.StudentId

interface Action

class MarkStudent(
    val courseId: CourseId,
    val studentId: StudentId
) : Action

class AddStudentToCourse(
    val courseId: CourseId,
    val studentId: String
): Action

class AddStudent(
    val student: Student
): Action

class AddGrade(
    val courseId: CourseId ,
    val studentId: StudentId,
    val grade: Grade
): Action
