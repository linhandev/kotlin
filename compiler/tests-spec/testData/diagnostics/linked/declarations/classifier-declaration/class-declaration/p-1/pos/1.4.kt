// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: class declaration with named companion object CO
 */

// TESTCASE NUMBER: 1
class C {
    companion object CO {
        fun value(): Int = 7
    }
}

fun useNamedCompanion(): Int = C.CO.value()
