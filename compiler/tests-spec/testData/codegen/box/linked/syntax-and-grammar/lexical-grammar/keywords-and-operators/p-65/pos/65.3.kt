// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 65 -> sentence 65
 * NUMBER: 3
 * DESCRIPTION: PACKAGE token in package declaration before class definition
 */
// TESTCASE NUMBER: 1

package test.spec.keyword.p65.clazz

class PackageClass65 {
    fun value(): String = "kw-65-65-3"
}

fun box(): String {
    val expected = "kw-65-65-3"
    val result = PackageClass65().value()
    if (result != expected) return "NOK"
    return "OK"
}
