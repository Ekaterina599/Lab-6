package component

import data.StudentId
import kotlinx.js.Record
import kotlinx.js.get
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.router.useParams
import redux.*
import tools.minus

external interface CoursePickerProps : Props {
    var state: State
    var dispatch: (action: Action) -> Unit
}

val CCoursePicker = FC<CoursePickerProps>("CoursePicker") { props ->
    val params: Record<String, String> = useParams()
    val courseName = params["name"]
    val course = props.state.courses.firstOrNull {
        it.name == courseName
    }
    if (course == null) {
        div {
            +"No course with name $courseName"
        }
    } else {
        CCourse {
            this.course = course
            mark = { studentId: StudentId ->
                props.dispatch(MarkStudent(course.id, studentId))
            }
            candidates = props.state.students - course.students
            addStudent = { studentId: StudentId ->
                props.dispatch(AddStudentToCourse(course.id, studentId))
            }
            grade = {studentId, it->
                props.dispatch(AddGrade(course.id, studentId, it))
            }
        }
    }
}