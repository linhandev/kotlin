// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: enum in companion is accessed via ClassName.Companion.Enum.ENTRY
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        enum class Color { RED, GREEN }
    }
}

fun case_1() {
    checkSubtype<Box.Companion.Color>(Box.Companion.Color.RED)
}
