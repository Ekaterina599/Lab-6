
package component

import csstype.*
import data.*
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr

external interface ListCourseProps : Props {
    var stud: Student
    var studId: StudentId
    var clickCourseId: (CourseId) -> Unit
    var course: List<Pair<Course, Grade>>
    var gradeSet: (CourseId, StudentId, Grade) -> Unit
}

val CCourseList = FC<ListCourseProps> { props ->
    h1 { CStudent { student = props.stud }}

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