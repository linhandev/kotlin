// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 276 -> sentence 276
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 276 -> sentence 276
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 276 -> sentence 276
 * NUMBER: 1
 * DESCRIPTION: a private primary constructor cannot be called from outside the declaring class (INVISIBLE_REFERENCE); covers plain class, generic class, and call from another top-level class; contrasts with next-point companion factory success and with p-85/p-109/p-110 companion-side positive uses
 */

// TESTCASE NUMBER: 1
class Vault private constructor(val v: Int)

fun case1() {
    <!INVISIBLE_REFERENCE!>Vault<!>(1)
}

// TESTCASE NUMBER: 2
class Box<T> private constructor(val value: T)

fun case2() {
    <!INVISIBLE_REFERENCE!>Box<!>("x")
}

// TESTCASE NUMBER: 3
class Token private constructor(val code: Int)

class TokenClient {
    fun make() {
        <!INVISIBLE_REFERENCE!>Token<!>(7)
    }
}
