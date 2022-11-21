package component

import data.Student
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.useContext

external interface StudentProps : Props {
    var student: Student
}

val CStudent = FC<StudentProps>("Student") { props ->
    div {
        props.student.let {
            if (useContext(primer).first == "Short")
                +"${it.firstname[0]}. ${it.surname}"
            else
                +"${it.firstname} ${it.surname}"
        }
    }
}