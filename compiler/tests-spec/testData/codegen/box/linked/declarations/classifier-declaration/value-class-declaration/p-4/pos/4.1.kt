// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: value class equals hashCode toString delegate to underlying property
 */

// TESTCASE NUMBER: 1
@JvmInline
value class Password(val s: String)

fun box(): String {
    val a = Password("secret")
    val b = Password("secret")
    val c = Password("other")
    if (a != b) return "equals failed"
    if (a == c) return "not equals failed"
    if (a.hashCode() != b.hashCode()) return "hashCode failed"
    if (a.toString() != b.toString()) return "toString failed"
    return "OK"
}
