// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 183 -> sentence 183
 * PRIMARY LINKS: inheritance, overriding -> paragraph 183 -> sentence 183
 *                expressions, super-forms -> paragraph 183 -> sentence 183
 *                declarations, property-declaration -> paragraph 183 -> sentence 183
 * NUMBER: 1
 * DESCRIPTION: overridden property getter may call super; inherited methods then observe the composed getter result in a class declaration
 */

// TESTCASE NUMBER: 1
open class Marker {
    open val label: String get() = "B"
}

class Tagged : Marker() {
    override val label: String get() = super.label + "C"
}

// TESTCASE NUMBER: 2
open class Prefix {
    open val head: String get() = "P"
    fun wrapped(): String = "<$head>"
}

class Prefixed : Prefix() {
    override val head: String get() = super.head + "X"
}

// TESTCASE NUMBER: 3
open class MutableMark {
    open var mark: String = "M"
        get() = field
        set(value) {
            field = value
        }
}

class ExtendedMark : MutableMark() {
    override var mark: String
        get() = super.mark + "!"
        set(value) {
            super.mark = value
        }
}

fun box(): String {
    if (Tagged().label != "BC") return "NOK: tagged"
    if ((Tagged() as Marker).label != "BC") return "NOK: tagged-as-marker"
    if (Marker().label != "B") return "NOK: marker"

    if (Prefixed().wrapped() != "<PX>") return "NOK: prefixed-wrapped"
    if ((Prefixed() as Prefix).wrapped() != "<PX>") return "NOK: prefix-ref-wrapped"
    if (Prefix().wrapped() != "<P>") return "NOK: prefix-base"

    val em = ExtendedMark()
    if (em.mark != "M!") return "NOK: extended-init"
    em.mark = "Z"
    if (em.mark != "Z!") return "NOK: extended-after-set"
    return "OK"
}
