// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: value class wraps underlying value at runtime
 */

// TESTCASE NUMBER: 1
@JvmInline
value class Password(val s: String)

fun box(): String {
    val p = Password("secret")
    return if (p.s == "secret") "OK" else "NOK"
}
