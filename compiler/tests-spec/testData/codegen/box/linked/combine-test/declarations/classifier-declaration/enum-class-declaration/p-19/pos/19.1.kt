// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 19 -> sentence 19
 *                declarations, classifier-declaration, class-declaration -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: nested enum constants can be matched with qualified names
 */

// TESTCASE NUMBER: 1
class Outer {
    enum class Mode { ON, OFF }
}

fun test(m: Outer.Mode): String = when (m) {
    Outer.Mode.ON -> "on"
    Outer.Mode.OFF -> "off"
}

fun box(): String {
    if (test(Outer.Mode.ON) != "on") return "NOK"
    if (test(Outer.Mode.OFF) != "off") return "NOK"
    return "OK"
}
