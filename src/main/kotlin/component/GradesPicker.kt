package component

import data.Student
import kotlinx.js.Record
import kotlinx.js.get
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.router.useParams
import redux.State

external interface GradesPickerProps : Props {
    var courseState: State
}

val CGradesPicker = FC<GradesPickerProps>("GradesPicker") { props ->
    val params: Record<String, String> = useParams()
    val gradesName = params["name"]
    val grdList = props.courseState.students
    var count = 0
    val studList = mutableListOf<Student>()
    when (gradesName) {
        "excellent student" -> {
            grdList.mapIndexed { ind, stud ->
                props.courseState.courses.map {
                    it.grades.mapIndexed { indexG, grd ->
                        if (indexG == ind && grd == 5)
                            count += 1
                    }
                }
                if (count == 3) {
                    studList.add(stud)
                }
                count = 0
            }
        }
        "good student" -> {
            grdList.mapIndexed { ind, stud ->
                props.courseState.courses.map {
                    it.grades.mapIndexed { indexG, grd ->
                        if (indexG == ind && grd == 4)
                            count += 1
                        else if (indexG == ind && grd == 5)
                            count += 2
                        else if (indexG == ind && grd <= 3)
                            count -= 2
                    }
                }
                if (count in 3..5) {
                    studList.add(stud)
                }
                count = 0
            }
        }
        "loser student" -> {
            grdList.mapIndexed { ind, stud ->
                props.courseState.courses.map {
                    it.grades.mapIndexed { indexG, grd ->
                        if (indexG == ind && grd <= 2)
                            count += 1
                    }
                }
                if (count > 0) {
                    studList.add(stud)
                }
                count = 0
            }
        }
    }
    div {
        ol {
            studList.map {
                li {
                    CStudent{ student = it }
                }
            }
        }
    }
}
