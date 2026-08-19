// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 19 -> sentence 19
 *                declarations, classifier-declaration, class-declaration -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: nested enum constants can be matched with qualified names
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Outer {
    enum class Mode { ON, OFF }
}

fun case_1(m: Outer.Mode) {
    checkSubtype<String>(when (m) {
        Outer.Mode.ON -> "on"
        Outer.Mode.OFF -> "off"
    })
}
