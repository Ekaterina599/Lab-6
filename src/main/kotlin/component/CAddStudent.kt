package component

import data.Student
import org.w3c.dom.HTMLInputElement
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.useRef

external interface AddStudentProps : Props {
    var add: (Student) -> Unit
}

val CAddStudent = FC<AddStudentProps>("AddStudent") { props ->
    val firstnameRef = useRef<HTMLInputElement>()
    val surnameRef = useRef<HTMLInputElement>()
    div {
        label { +"firstname" }
        input { ref = firstnameRef }
        label { +"surname" }
        input { ref = surnameRef}
        button {
            +"Add"
            onClick = {
                firstnameRef.current?.value?.let { firstname ->
                    surnameRef.current?.value?.let { surname ->
                        props.add(Student(firstname, surname))
                    }
                }
            }
        }
    }
}