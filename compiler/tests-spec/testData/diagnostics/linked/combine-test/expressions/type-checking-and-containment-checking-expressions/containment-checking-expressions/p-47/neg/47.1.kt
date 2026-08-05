// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 47 -> sentence 47
 * PRIMARY LINKS: overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 47 -> sentence 47
 * NUMBER: 1
 * DESCRIPTION: in operator cannot resolve contains when element type does not match custom Tag parameter type
 */

// TESTCASE NUMBER: 1
class Tag

class Box {
    operator fun contains(t: Tag): Boolean = true
}

fun case1(x: Int) {
    val b: Boolean = <!TYPE_MISMATCH!>x<!> in Box()
}
