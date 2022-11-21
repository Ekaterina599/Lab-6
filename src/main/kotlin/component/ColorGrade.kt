package component


import csstype.*
import emotion.react.css
import react.*
import react.dom.html.InputType
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label


external interface ColorGrade : Props {
    var listcolor: Array<Color>
    var setColor: (Int, Color) -> Unit
}

val CColor = FC<ColorGrade>("ChangeColor") { props ->
    listOf(1,2,3,4,5).mapIndexed { _index, grade ->
        label { +grade.toString() }
        input {
            type = InputType.color
            value = props.listcolor[_index].toString()
            onChange = { props.setColor(_index, Color(it.target.value)) }
        }
    }
}

