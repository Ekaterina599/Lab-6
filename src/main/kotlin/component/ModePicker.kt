package component

import org.w3c.dom.HTMLInputElement
import react.FC
import react.Props
import react.StateSetter
import react.dom.events.MouseEventHandler
import react.dom.html.InputType
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.span
import react.useRef

external interface ModePickerProps : Props {
    var _mode: String
    var _setMode: StateSetter<String>
}

val CModePicker = FC<ModePickerProps>("ModePicker") { props ->
    val modes = listOf("Full", "Short")
    val refs = modes.map {
        useRef<HTMLInputElement>()
    }
    val action: MouseEventHandler<*> = {
        refs
            .find { it.current?.checked ?: false }
            ?.current?.value
            ?.let {
                if (it != props._mode)
                    props._setMode(it)
            }
    }
    modes.mapIndexed { index, _mode ->
        span {
            input {
                type = InputType.radio
                value = _mode
                ref = refs[index]
                onClick = action
                name = "outputMode"
                if (props._mode == _mode)
                    defaultChecked = true
            }
            +_mode
        }
    }
}