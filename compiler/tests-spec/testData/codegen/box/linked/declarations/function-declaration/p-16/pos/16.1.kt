// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: extension receiver is not passed as a regular argument at runtime
 */

// TESTCASE NUMBER: 1
fun String.lastChar(): Char = this[length - 1]

fun box(): String {
    val explicit = "abc".lastChar()
    val implicit = with("xyz") { lastChar() }
    return if (explicit == 'c' && implicit == 'z') "OK" else "NOK explicit=$explicit implicit=$implicit"
}
