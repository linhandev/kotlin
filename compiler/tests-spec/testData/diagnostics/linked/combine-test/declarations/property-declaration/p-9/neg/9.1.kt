// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: private setter cannot be used from a top-level function
 */

// TESTCASE NUMBER: 1
class Box {
    var x: Int = 0
        private set
}

fun case_1() {
    val b = Box()
    <!INVISIBLE_SETTER!>b.x<!> = 42
}
