// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, companion-object -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: synchronized in companion object returns the block result
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box {
    companion object {
        val lock = Any()
        fun safe() = synchronized(lock) { 42 }
    }
}

fun case_1() {
    checkSubtype<Int>(Box.safe())
}
