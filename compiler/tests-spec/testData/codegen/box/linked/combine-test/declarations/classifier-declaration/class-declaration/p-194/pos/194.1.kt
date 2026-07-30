// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 194 -> sentence 194
 * PRIMARY LINKS: inheritance, overriding -> paragraph 194 -> sentence 194
 *                inheritance, inheriting -> paragraph 194 -> sentence 194
 * NUMBER: 1
 * DESCRIPTION: a single override can satisfy identically named members from multiple interfaces in a class declaration
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

// TESTCASE NUMBER: 2
interface Left {
    fun tag(): String
}

interface Right {
    fun tag(): String
}

class Both : Left, Right {
    override fun tag(): String = "both"
    fun viaLeft(): String = (this as Left).tag()
    fun viaRight(): String = (this as Right).tag()
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

fun box(): String {
    val c = C()
    if (c.f() != 1) return "NOK: c-f"
    if ((c as A).f() != 1) return "NOK: as-A"
    if ((c as B).f() != 1) return "NOK: as-B"

    val both = Both()
    if (both.tag() != "both") return "NOK: both-tag"
    if (both.viaLeft() != "both") return "NOK: via-left"
    if (both.viaRight() != "both") return "NOK: via-right"

    val pq = PQ()
    if (pq.code != 9) return "NOK: pq-code"
    if ((pq as P).code != 9) return "NOK: as-P"
    if ((pq as Q).code != 9) return "NOK: as-Q"
    return "OK"
}
