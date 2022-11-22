package component

import data.Student
import kotlinx.js.Record
import kotlinx.js.get
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import react.router.useParams
import react.useRef
import redux.*
import tools.minus


external interface StudentPickerProps : Props {
    var state: State
    var dispatch: (action: Action) -> Unit
}

val CStudentPicker = FC<StudentPickerProps>("StudentPicker") { props ->
    val params: Record<String, String> = useParams()
    val studentName = params["stud"]
    val student = props.state.students.firstOrNull { it.id == studentName }
    if (student == null) {
        div {
            // +"No course with name $studentName"
        }
    } else {
        CCourseList {
            stud = student
            studId = student.id
            course = props.state.getCourseList(student)
            allCourses = props.state.courses
            gradeSet = { courseId, studentId, it -> props.dispatch(AddGrade(courseId, studentId, it)) }
            addStudent = {course, studentId -> props.dispatch(AddStudentToCourse(course, studentId))}
        }
    }
}
