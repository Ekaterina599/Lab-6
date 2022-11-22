

package component

import csstype.*
import data.*
import emotion.react.css
import org.w3c.dom.HTMLSelectElement
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import react.dom.html.ReactHTML.summary
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr
import react.useRef
import redux.Action
import redux.AddStudent
import redux.AddStudentToCourse
import tools.minus

external interface ListCourseProps : Props {
    var stud: Student
    var studId: StudentId
    var course: List<Pair<Course, Grade>>
    var allCourses: Array<Course>
    var gradeSet: (CourseId, StudentId, Grade) -> Unit
    var addStudent: (String, StudentId) -> Unit
}

val CCourseList = FC<ListCourseProps> { props ->
    h1 { CStudent { student = props.stud } }
    val studentCourse = props.course.map { it.first }.toTypedArray()
    val candidatesCourse = props.allCourses - studentCourse
    val refCourse = useRef<HTMLSelectElement>()
    details {
        summary { +"Add Course" }
        select {
            ref = refCourse
            candidatesCourse.forEach { option { +it.id } }
        }
        button {
            +"Add"
            onClick = {
                refCourse.current?.value?.let { course ->
                    props.addStudent(course, props.studId)
                }
            }
        }
    }
    props.course.forEach { courses ->
        tr {
            td {
                css {
                    background = NamedColor.lightpink
                    padding = Padding(vertical = 10.px, horizontal = 10.px)
                    border = Border(width = 2.px, style = LineStyle.solid)
                }
                +courses.first.name
            }
            td {
                css {
                    padding = Padding(vertical = 10.px, horizontal = 10.px)
                    border = Border(width = 2.px, style = LineStyle.solid)
                }
                CGrade {
                    grade = courses.second
                    setGrade = { it -> props.gradeSet(courses.first.id, props.studId, it) }
                }
            }
        }
    }
}
