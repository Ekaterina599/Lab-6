package component

import csstype.*
import csstype.Cursor.Companion.text
import emotion.react.css
import react.*
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.summary
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import react.router.dom.Link
import redux.AddStudent
import redux.markReducer
import redux.testState


val primer = createContext(Pair("Full", (arrayOf<Color>())))

val app = FC<Props>("App") {
    val (mode, setMode) = useState("Full")
    val (state, dispatch) = useReducer(markReducer, testState())
    val progress = listOf("excellent student", "good student", "loser student")
    var ListColor by useState { listOf(1, 2, 3, 4, 5).map { Color("#0000ff") }.toTypedArray() }
    CModePicker {
        _mode = mode
        _setMode = setMode
    }
    CColor {
        listcolor = ListColor
        setColor = { indx, clr ->
            ListColor = ListColor.mapIndexed { indexState, colorState ->
                if (indexState == indx) clr else colorState
            }.toTypedArray()
        }
    }
    primer.Provider(Pair(mode, ListColor)) {
            HashRouter {
                div {
                    css { display = Display.flex }
                    state.courses.forEach { course ->
                        val name = course.name
                        Link {
                            css { flex = Flex.minContent }
                            +name
                            to = "Course/$name"
                        }
                    }
                    progress.forEach{
                        val name = it
                        Link {
                            css { flex = Flex.minContent }
                            +name
                            to = "List/$name"
                        }
                    }
                    state.students.forEach {student->
                        val stud = student.id
                        Link{
                            css {
                                flex = Flex.minContent
                            }
                            +"${student.firstname} ${student.surname}"
                            to = "Student/$stud"
                        }
                    }
                }
                details {
                    summary { +"Add Student" }
                    CAddStudent {
                        add = { student -> dispatch(AddStudent(student)) }
                    }
                }
                Routes {
                    Route {
                        path = "Course/:name"
                        element = CCoursePicker.create {
                            this.state = state
                            this.dispatch = dispatch
                        }
                    }
                    Route{
                        path = "List/:name"
                        element = CGradesPicker.create {
                            this.courseState = state
                        }
                    }
                    Route{
                        path = "Student/:stud"
                        element = CStudentPicker.create{
                            this.state = state
                            this.dispatch = dispatch
                        }
                    }
                }
            }
        }
    }