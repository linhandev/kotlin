// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 260 -> sentence 260
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 260 -> sentence 260
 *                inheritance, inheriting -> paragraph 260 -> sentence 260
 * NUMBER: 1
 * DESCRIPTION: precise types when a default-visibility (public) class appears in public API signatures
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Pub

fun makePub(): Pub = Pub()

fun case1() {
    val p = makePub()
    p checkType { check<Pub>() }
    checkSubtype<Pub>(p)
}

// TESTCASE NUMBER: 2
class Token(val code: Int)

fun makeToken(): Token = Token(7)

class TokenClient {
    fun wrap(): Token = Token(7)
}

fun case2() {
    val t = makeToken()
    t checkType { check<Token>() }
    t.code checkType { check<Int>() }
    TokenClient().wrap() checkType { check<Token>() }
}

// TESTCASE NUMBER: 3
open class Base

class Derived : Base()

fun asBase(): Base = Derived()

fun case3() {
    val d = Derived()
    d checkType { check<Derived>() }
    checkSubtype<Base>(d)
    asBase() checkType { check<Base>() }
}
