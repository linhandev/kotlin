// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 194 -> sentence 194
 * PRIMARY LINKS: inheritance, overriding -> paragraph 194 -> sentence 194
 *                inheritance, inheriting -> paragraph 194 -> sentence 194
 * NUMBER: 1
 * DESCRIPTION: type inference when a single override satisfies identically named members from multiple interfaces in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface A {
    fun f(): Int
}

interface B {
    fun f(): Int
}

class C : A, B {
    override fun f(): Int = 1
}

fun case1() {
    val c = C()
    c checkType { check<C>() }
    checkSubtype<A>(c)
    checkSubtype<B>(c)
    c.f() checkType { check<Int>() }

    val asA: A = c
    asA.f() checkType { check<Int>() }
    val asB: B = c
    asB.f() checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
interface Left {
    fun tag(): String
}

interface Right {
    fun tag(): String
}

class Both : Left, Right {
    override fun tag(): String = "both"
}

fun case2() {
    val both = Both()
    both checkType { check<Both>() }
    checkSubtype<Left>(both)
    checkSubtype<Right>(both)
    both.tag() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
interface P {
    val code: Int
}

interface Q {
    val code: Int
}

class PQ : P, Q {
    override val code: Int = 9
}

fun case3() {
    val pq = PQ()
    pq checkType { check<PQ>() }
    checkSubtype<P>(pq)
    checkSubtype<Q>(pq)
    pq.code checkType { check<Int>() }
}
