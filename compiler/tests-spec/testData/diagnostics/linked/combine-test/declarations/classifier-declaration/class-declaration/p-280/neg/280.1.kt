// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 280 -> sentence 280
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 280 -> sentence 280
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 280 -> sentence 280
 * NUMBER: 1
 * DESCRIPTION: a protected primary constructor is invisible outside the declaring class hierarchy (INVISIBLE_MEMBER); covers plain class, generic class, and call from an unrelated top-level class; contrasts with previous-point subclass success and with p-276 private constructor
 */

// TESTCASE NUMBER: 1
open class TokenBase protected constructor(val id: Int)

fun case1() {
    <!INVISIBLE_MEMBER!>TokenBase<!>(1)
}

// TESTCASE NUMBER: 2
open class CodeBase<T> protected constructor(val value: T)

fun case2() {
    <!INVISIBLE_MEMBER!>CodeBase<!>("x")
}

// TESTCASE NUMBER: 3
open class LabelBase protected constructor(val label: String)

class LabelClient {
    fun make() {
        <!INVISIBLE_MEMBER!>LabelBase<!>("x")
    }
}
