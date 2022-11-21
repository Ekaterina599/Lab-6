package component

import data.Course
import data.Grade
import data.Student
import data.StudentId
import org.w3c.dom.HTMLSelectElement
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import react.dom.html.ReactHTML.summary
import react.useRef

external interface CourseProps : Props {
    var course: Course
    var mark: (StudentId) -> Unit
    var candidates: Array<Student>
    var addStudent: (StudentId) -> Unit
    var grade: (StudentId, Grade)->Unit
}

val CCourse = FC<CourseProps>("course") { props ->
    val addStudentSelectRef = useRef<HTMLSelectElement>()
    div {
        h1 { +props.course.name }
        details {
            summary { +"Add Student"}
            select {
                ref = addStudentSelectRef
                props.candidates.forEach {
                    option { +it.id }
                }
            }
            button{
                +"Add"
                onClick = {
                    addStudentSelectRef.current?.value?.let {
                        props.addStudent(it)
                    }
                }
            }
        }
        CStudentList {
            list = props.course.students
            marked = props.course.marked
            gradesList = props.course.grades
            clickId = { it: StudentId -> props.mark(it) }
            setGradeList = {studentId, it -> props.grade(studentId, it) }
        }
    }
}