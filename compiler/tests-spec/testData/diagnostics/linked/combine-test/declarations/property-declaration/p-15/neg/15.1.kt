// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: private setter is visible to members but invisible outside the class
 */

// TESTCASE NUMBER: 1
class Box {
    var x: Int = 0
        private set

    fun setX(v: Int) {
        x = v
    }
}

fun case_1() {
    val b = Box()
    b.setX(1)
    <!INVISIBLE_SETTER!>b.x<!> = 2
}
