// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 14 -> sentence 14
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: when on enum implementing interface remains exhaustive by enum constants
 * HELPERS: checkType
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

fun case_1(m: Mode) {
    checkSubtype<String>(when (m) {
        Mode.ON -> m.text
        Mode.OFF -> m.text
    })
}
