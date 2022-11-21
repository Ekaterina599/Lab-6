package data

typealias CourseId = String
typealias Grade = Int
data class Course(
    val name: String,
    val students: Array<Student>,
    val marked: Array<Boolean> = Array(students.size) { false },
    val grades: Array<Grade> = Array(students.size){1}
) {
    val id: CourseId
        get() = name
}

val courseList =
    arrayOf("Math", "Phys", "Story").map { Course(it, studentList) }