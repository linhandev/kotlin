// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, multiplicative-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 22 -> sentence 22
 *                type-system, built-in-integer-types -> paragraph 22 -> sentence 22
 *                declarations, function-declaration, extension-function-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: extension operator fun Long.times(W) enables reverse multiplication
 */

// TESTCASE NUMBER: 1
data class W(val v: Long)

operator fun Long.times(w: W): W = W(this * w.v)

fun test(): Long = (4L * W(5L)).v

fun box(): String {
    if (test() != 20L) return "NOK"
    if ((3L * W(7L)).v != 21L) return "NOK"
    if ((-2L * W(4L)).v != -8L) return "NOK"
    return "OK"
}
