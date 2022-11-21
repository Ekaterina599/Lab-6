package component

import kotlinx.js.Record
import kotlinx.js.get
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.router.useParams
import redux.*


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
            clickCourseId = {courseId->
                props.dispatch(MarkStudent(courseId, student.id))
            }
            gradeSet = {courseId, studentId, it->
                props.dispatch(AddGrade(courseId, studentId, it))
            }
        }
    }
}