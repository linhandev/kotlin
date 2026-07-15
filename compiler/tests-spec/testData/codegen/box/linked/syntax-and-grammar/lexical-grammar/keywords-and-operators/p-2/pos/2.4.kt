// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: DOT token used for chained member access (obj.a.b.c)
 */
// TESTCASE NUMBER: 1

class A(val value: String)
class B(val a: A)
class C(val b: B)

fun box(): String {
    val expected = "dot-chain-2-4"
    val c = C(B(A(expected)))
    if (c.b.a.value != expected) return "NOK"
    return "OK"
}
