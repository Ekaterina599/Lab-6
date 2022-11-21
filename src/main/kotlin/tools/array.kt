package tools

inline operator fun <reified T> Array<T>.plus(element: T) =
    Array(size + 1) {
        if (it == size)
            element
        else
            get(it)
    }

inline operator fun <reified T> Array<T>.minus(other: Array<T>) =
    (this.toList() - other.toSet()).toTypedArray()

inline fun <reified T> Array<T>.replace(oldElement: T, newElement: T) =
    Array(size) {
        val element = get(it)
        if (element == oldElement)
            newElement
        else
            element
    }
