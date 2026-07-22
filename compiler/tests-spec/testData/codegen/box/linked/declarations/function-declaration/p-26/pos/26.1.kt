// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: inline extension receiver is effectively noinline and remains callable at runtime
 */

// TESTCASE NUMBER: 1
inline fun (() -> String).extensionNoInline(): String = this() + this.hashCode().toString()

fun box(): String {
    val result = { "OK" }.extensionNoInline().substring(0, 2)
    return if (result == "OK") "OK" else "NOK result=$result"
}
