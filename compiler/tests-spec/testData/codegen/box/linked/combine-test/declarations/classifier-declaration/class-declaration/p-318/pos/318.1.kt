/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 318 -> sentence 318
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 318 -> sentence 318
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 318 -> sentence 318
 * NUMBER: 1
 * DESCRIPTION: nested enum class can be qualified with the outer class name
 */

// TESTCASE NUMBER: 1
class Outer {
    enum class Mode { ON, OFF }
}

fun test(): Boolean = Outer.Mode.ON == Outer.Mode.ON

fun box(): String {
    if (!test()) return "NOK: test"
    if (Outer.Mode.ON.name != "ON") return "NOK: on"
    if (Outer.Mode.OFF.name != "OFF") return "NOK: off"
    if (Outer.Mode.ON == Outer.Mode.OFF) return "NOK: diff"
    return "OK"
}
