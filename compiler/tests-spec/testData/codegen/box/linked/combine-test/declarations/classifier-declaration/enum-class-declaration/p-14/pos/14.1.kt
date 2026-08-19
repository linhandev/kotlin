// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 14 -> sentence 14
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: when on enum implementing interface remains exhaustive by enum constants
 */

// TESTCASE NUMBER: 1
interface Label {
    val text: String
}

enum class Mode : Label {
    ON {
        override val text = "on"
    },
    OFF {
        override val text = "off"
    }
}

fun test(m: Mode): String = when (m) {
    Mode.ON -> m.text
    Mode.OFF -> m.text
}

fun box(): String {
    if (test(Mode.ON) != "on") return "NOK"
    if (test(Mode.OFF) != "off") return "NOK"
    return "OK"
}
